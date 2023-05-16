/**
 * File:     SystemProcess.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     08.04.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Defines system processes.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class SystemProcess {
	/**
	 * The process. Null if it is not running.
	 */
	private Process process = null;

	/**
	 * The working directory. If null, uses the working directory of the current
	 * Java process.
	 */
	private final Path directory;

	/**
	 * The operating system command.
	 */
	private final String command;

	/**
	 * The system process standard output.
	 */
	private StringBuffer standardOutput;

	/**
	 * The system process standard error.
	 */
	private StringBuffer standardError;

	/**
	 * The system job exit value. By convention, the value 0 indicates normal
	 * termination. -1 if the exit value is not set.
	 */
	private int exitValue;

	/**
	 * Creates a system process. The working directory of the current Java process
	 * is used and the .
	 * 
	 * @param command The operating system command.
	 * @throws IllegalArgumentException Throws if the command is not defined.
	 * @since 1.8
	 */
	public SystemProcess(String command) throws IllegalArgumentException {
		this(null, command);
	}

	/**
	 * Creates a system process.
	 * 
	 * @param directory The working directory. If null, uses the working directory
	 *                  of the current Java process.
	 * @param command   The operating system command.
	 * @throws IllegalArgumentException Throws if the command is not defined.
	 * @since 1.8
	 */
	public SystemProcess(Path directory, String command) throws IllegalArgumentException {
		super();

		if (command == null || command.isBlank())
			throw new IllegalArgumentException("SystemProcess: the command is mandatory.");

		this.directory = directory;
		this.command = command;
	}

	/**
	 * Returns the operating system command.
	 *
	 * @return The operating system command.
	 * @since 1.8
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Execute the system process with given arguments in foreground if it is not
	 * running.
	 * 
	 * @param arguments The arguments.
	 * @throws IOException Throws if an I/O exception of some sort has occurred or
	 *                     the process is already running.
	 * @since 1.8
	 */
	public void execute(String... arguments) throws IOException {
		execute(false, arguments);
	}

	/**
	 * Execute the system process with given arguments in foreground if it is not
	 * running.
	 * 
	 * @param arguments The arguments. Null if no arguments are required.
	 * @throws IOException Throws if an I/O exception of some sort has occurred or
	 *                     the process is already running.
	 * @since 1.8
	 */
	public void execute(List<String> arguments) throws IOException {
		execute(false, arguments);
	}

	/**
	 * Execute the system process with given arguments if it is not running.
	 * 
	 * @param isBackground True if only if the process will run in background, this
	 *                     means, in a new thread. Furthermore the standard output
	 *                     and the error of the system process are fetched while the
	 *                     process is running.
	 * @param arguments    The arguments.
	 * @throws IOException Throws if an I/O exception of some sort has occurred or
	 *                     the process is already running.
	 * @since 1.8
	 */
	public void execute(boolean isBackground, String... arguments) throws IOException {
		execute(isBackground, Arrays.asList(arguments));
	}

	/**
	 * Execute the system process with given arguments if it is not running.
	 * 
	 * @param isBackground True if only if the process will run in background, this
	 *                     means, in a new thread. Furthermore the standard output
	 *                     and the error of the system process are fetched while the
	 *                     process is running.
	 * @param arguments    The arguments. Null if no arguments are required.
	 * @throws IOException Throws if an I/O exception of some sort has occurred or
	 *                     the process is already running.
	 * @since 1.8
	 */
	public synchronized void execute(boolean isBackground, List<String> arguments) throws IOException {
		if (isRunning())
			throw new IOException("The system process is already running.");

		// Initialize
		exitValue = -1;

		standardOutput = new StringBuffer();
		standardError = new StringBuffer();

		// Build the command with arguments
		List<String> commandBuilder = new ArrayList<>();
		commandBuilder.add(command);

		if (arguments != null)
			for (String argument : arguments)
				if (argument != null)
					commandBuilder.add(argument);

		ProcessBuilder builder = new ProcessBuilder(commandBuilder);
		if (directory != null)
			builder.directory(directory.toFile());

		// Start the system process
		process = builder.start();

		if (isBackground) {
			// Handles the system process output and error streams
			Executors.newSingleThreadExecutor().submit(
					new InputStreamHandler(process.getInputStream(), (output) -> append(standardOutput, output)));

			Executors.newSingleThreadExecutor()
					.submit(new InputStreamHandler(process.getErrorStream(), (error) -> append(standardError, error)));

			// Start the system process in background
			new Thread(new Runnable() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run() {
					try {
						exitValue = process.waitFor();
					} catch (InterruptedException ie) {
						try {
							exitValue = process.exitValue();
						} catch (IllegalThreadStateException itse) {
							// can not recover system job exit value
						}
					}

					process = null;
				}
			}).start();
		} else {
			try {
				exitValue = process.waitFor();
			} catch (InterruptedException ie) {
				try {
					exitValue = process.exitValue();
				} catch (IllegalThreadStateException itse) {
					// can not recover system job exit value
				}
			}

			copy(process.getInputStream(), standardOutput);
			copy(process.getErrorStream(), standardError);

			process = null;
		}
	}

	/**
	 * Returns true if the system job is running.
	 * 
	 * @return True if the system job is running.
	 * @since 1.8
	 */
	public boolean isRunning() {
		return process != null;
	}

	/**
	 * Cancels the system job.
	 * 
	 * @since 1.8
	 */
	final public void cancel() {
		if (isRunning())
			process.destroy();
	}

	/**
	 * Returns the system process standard output.
	 *
	 * @return The system process standard output.
	 * @since 1.8
	 */
	public String getStandardOutput() {
		return standardOutput.toString();
	}

	/**
	 * Returns the system process standard error.
	 *
	 * @return The system process standard error.
	 * @since 1.8
	 */
	public String getStandardError() {
		return standardError.toString();
	}

	/**
	 * Returns the exit value. By convention, the value 0 indicates normal
	 * termination. -1 if the exit value is not set.
	 *
	 * @return The exit value.
	 * @since 1.8
	 */
	public int getExitValue() {
		return exitValue;
	}

	/**
	 * Copies the input stream content to the buffer.
	 * 
	 * @param inputStream The input stream.
	 * @param buffer      The buffer.
	 * @since 1.8
	 */
	private static void copy(InputStream inputStream, StringBuffer buffer) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String nextLine = null;
			while ((nextLine = reader.readLine()) != null)
				append(buffer, nextLine);
		} catch (IOException e) {
			// The stream was closed
		}
	}

	/**
	 * Append the content to the stream to the buffer.
	 * 
	 * @param buffer  The buffer to add the content.
	 * @param content The content. If null, do not update the stream buffer.
	 * @since 1.8
	 */
	private static void append(StringBuffer buffer, String content) {
		if (content != null)
			buffer.append(content + System.lineSeparator());
	}

	/**
	 * Defines handlers for input streams.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	private static class InputStreamHandler implements Runnable {
		/**
		 * The input stream to handle.
		 */
		private final InputStream inputStream;

		/**
		 * The consumer for input contents.
		 */
		private final Consumer<String> consumer;

		/**
		 * Creates a handler for an input stream.
		 * 
		 * @param inputStream The input stream to handle.
		 * @param consumer    The consumer for input contents.
		 * @since 1.8
		 */
		public InputStreamHandler(InputStream inputStream, Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
				String nextLine = null;
				while ((nextLine = reader.readLine()) != null)
					consumer.accept(nextLine);
			} catch (IOException e) {
				// InputStream is closed by terminating the underlying process
				// Terminate this Thread as well to avoid further usage
				Thread.currentThread().interrupt();
			}
		}
	}

}
