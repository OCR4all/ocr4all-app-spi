/**
 * File:     FrameworkCore.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FrameworkCore is an immutable class that defines framework cores for service
 * providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class FrameworkCore {
	/**
	 * Defines operating systems.
	 */
	public enum OperatingSystem {
		unix, mac, windows, notSupported;
	}

	/**
	 * The operating system.
	 */
	private final OperatingSystem operatingSystem;

	/**
	 * The effective system user ID. -1 if not defined.
	 */
	private final int uid;

	/**
	 * The effective system group ID. -1 if not defined.
	 */
	private final int gid;

	/**
	 * The application information.
	 */
	private final Application application;

	/**
	 * The user. Null if not defined.
	 */
	private final String user;

	/**
	 * The output directory.
	 */
	private final Path output;

	/**
	 * The temporary directory.
	 */
	private final Path temporary;

	/**
	 * Creates a framework for a service provider.
	 * 
	 * @param operatingSystem The operating system.
	 * @param uid             The effective system user ID. -1 if not defined.
	 * @param gid             The effective system group ID. -1 if not defined.
	 * @param application     The application.
	 * @param user            The user. Null if not defined.
	 * @param output          The output directory.
	 * @param temporary       The temporary directory.
	 * @since 17
	 */
	public FrameworkCore(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Path output, Path temporary) {
		super();

		this.operatingSystem = operatingSystem;
		this.uid = uid;
		this.gid = gid;
		this.application = application;
		this.user = user;
		this.output = output;
		this.temporary = temporary;
	}

	/**
	 * Returns the operating system.
	 *
	 * @return The operating system.
	 * @since 17
	 */
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Returns true if the effective system user ID is defined.
	 * 
	 * @return True if the effective system user ID is defined.
	 * @since 17
	 */
	public boolean isUID() {
		return uid >= 0;
	}

	/**
	 * Returns the effective system user ID.
	 *
	 * @return The effective system user ID. -1 if not defined.
	 * @since 17
	 */
	public int getUID() {
		return uid;
	}

	/**
	 * Returns true if the effective system group ID is defined.
	 * 
	 * @return True if the effective system group ID is defined.
	 * @since 17
	 */
	public boolean isGID() {
		return gid >= 0;
	}

	/**
	 * Returns the effective system group ID. -1 if not defined.
	 *
	 * @return The effective system group ID. -1 if not defined.
	 * @since 17
	 */
	public int getGID() {
		return gid;
	}

	/**
	 * Returns the application.
	 *
	 * @return The application.
	 * @since 17
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * Returns the formated message.
	 * 
	 * @param message The message to format.
	 * @return The formated message.
	 * @since 17
	 */
	public String formatLogMessage(String message) {
		return formatCurrentDate() + ": " + message + System.lineSeparator();
	}

	/**
	 * Formats the current date/time string.
	 * 
	 * @return The formatted time string.
	 * @since 17
	 */
	public String formatCurrentDate() {
		return format(null);
	}

	/**
	 * Formats a date into a date/time string.
	 * 
	 * @param date The date/time to be formatted into a time string. If null, uses
	 *             current date.
	 * @return The formatted time string.
	 * @since 17
	 */
	public String format(Date date) {
		if (date == null)
			date = new Date();

		return application == null || application.getDateFormat() == null ? date.toString()
				: application.getDateFormat().format(date);
	}

	/**
	 * Returns true if the user is set.
	 *
	 * @return True if the user is set.
	 * @since 17
	 */
	public boolean isUserSet() {
		return user != null;
	}

	/**
	 * Returns the user.
	 *
	 * @return The user.
	 * @since 17
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Returns the output directory.
	 *
	 * @return The output directory.
	 * @since 17
	 */
	public Path getOutput() {
		return output;
	}

	/**
	 * Returns the temporary directory.
	 *
	 * @return The temporary directory.
	 * @since 17
	 */
	public Path getTemporary() {
		return temporary;
	}

	/**
	 * Application is an immutable class that defines application information for
	 * service providers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public static class Application {
		/**
		 * The label.
		 */
		private final String label;

		/**
		 * The name.
		 */
		private final String name;

		/**
		 * The date format.
		 */
		private final SimpleDateFormat dateFormat;

		/**
		 * Creates an application information for service providers.
		 * 
		 * @param label      The label.
		 * @param name       The name.
		 * @param dateFormat The date format.
		 * @since 17
		 */
		public Application(String label, String name, SimpleDateFormat dateFormat) {
			super();
			this.label = label;
			this.name = name;
			this.dateFormat = dateFormat;
		}

		/**
		 * Returns the label.
		 *
		 * @return The label.
		 * @since 17
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * Returns the name.
		 *
		 * @return The name.
		 * @since 17
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the date format.
		 * 
		 * @return The date format.
		 * @since 17
		 */
		public SimpleDateFormat getDateFormat() {
			return dateFormat;
		}

	}
}
