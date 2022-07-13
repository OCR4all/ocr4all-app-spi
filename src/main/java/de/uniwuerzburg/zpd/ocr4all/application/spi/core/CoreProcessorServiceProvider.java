/**
 * File:     CoreProcessorServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     31.05.2022
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Framework;

/**
 * Defines core processors for service providers. When implementing the required
 * method {@link ProcessServiceProvider.Processor#execute}, this class should be
 * initialized by calling the method {@link #initialize} at the beginning and
 * completed by calling the method {@link #complete}.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public abstract class CoreProcessorServiceProvider implements ProcessServiceProvider.Processor {
	/**
	 * True if the processor was canceled.
	 */
	private boolean isCanceled = false;

	/**
	 * The processor identifier.
	 */
	private String identifier;

	/**
	 * The callback interface for processor updates.
	 */
	private ProcessServiceProvider.Processor.Callback callback;

	/**
	 * The framework.
	 */
	private Framework framework;

	/**
	 * The processor standard output.
	 */
	private final StringBuffer standardOutput = new StringBuffer();

	/**
	 * The processor standard error.
	 */
	private final StringBuffer standardError = new StringBuffer();

	/**
	 * Initializes the execution of the processor.
	 * 
	 * @param identifier The processor identifier.
	 * @param callback   The callback method for processor updates. If null, no
	 *                   callback is performed.
	 * @param framework  The framework for the processor.
	 * @return True, if the processor can continue the execution, this means, it was
	 *         not canceled in the meantime.
	 * @since 1.8
	 */
	protected boolean initialize(String identifier, Callback callback, Framework framework) {
		this.identifier = identifier;
		this.callback = callback;
		this.framework = framework;

		callback.updatedProgress(0F);

		updatedStandardOutput("Start spi '" + identifier + "'.");

		return !isCanceled;
	}

	/**
	 * Completes the execution of the processor.
	 * 
	 * @return The process execution state completed.
	 * @since 1.8
	 */
	protected ProcessServiceProvider.Processor.State complete() {
		updatedStandardOutput(identifier + " completed.");

		callback.updatedProgress(1F);

		return ProcessServiceProvider.Processor.State.completed;
	}

	/**
	 * Callback method for updated standard output.
	 * 
	 * @param message The message.
	 * @since 1.8
	 */
	public void updatedStandardOutput(String message) {
		standardOutput.append(framework.formatLogMessage(message));

		callback.updatedStandardOutput(standardOutput.toString());
	}

	/**
	 * Callback method for updated standard error.
	 * 
	 * @param message The current message.
	 * @since 1.8
	 */
	public void updatedStandardError(String message) {
		standardError.append(framework.formatLogMessage(message));

		callback.updatedStandardError(standardError.toString());
	}

	/**
	 * Returns true if the processor was canceled.
	 * 
	 * @return True if the processor was canceled.
	 * @since 1.8
	 */
	public boolean isCanceled() {
		return isCanceled;
	}

	/**
	 * Returns the callback interface for processor updates.
	 * 
	 * @return The callback interface for processor updates.
	 * @since 1.8
	 */
	public ProcessServiceProvider.Processor.Callback getCallback() {
		return callback;
	}

	/**
	 * Returns the framework.
	 * 
	 * @return The framework.
	 * @since 1.8
	 */
	public Framework getFramework() {
		return framework;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.core.ProcessServiceProvider.
	 * Processor#cancel()
	 */
	@Override
	public void cancel() {
		isCanceled = true;
	}

}
