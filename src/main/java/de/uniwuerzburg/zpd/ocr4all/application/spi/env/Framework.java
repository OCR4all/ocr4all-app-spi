/**
 * File:     Framework.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     29.01.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Framework is an immutable class that defines frameworks for service
 * providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class Framework {
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
	 * The target.
	 */
	private final Target target;

	/**
	 * The output directory.
	 */
	private final Path output;

	/**
	 * The snapshot track for the output directory. The track for a root snapshot is
	 * an empty list. Null if not available.
	 */
	private List<Integer> snapshotTrack;

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
	 * @param target          The target.
	 * @param output          The output directory.
	 * @param snapshotTrack   The snapshot track for the output directory. The track
	 *                        for a root snapshot is an empty list. Null if not
	 *                        available.
	 * @param temporary       The temporary directory.
	 * @since 1.8
	 */
	public Framework(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Target target, Path output, List<Integer> snapshotTrack, Path temporary) {
		super();

		this.operatingSystem = operatingSystem;
		this.uid = uid;
		this.gid = gid;
		this.application = application;
		this.user = user;
		this.target = target;
		this.output = output;
		this.snapshotTrack = snapshotTrack;
		this.temporary = temporary;
	}

	/**
	 * Returns the operating system.
	 *
	 * @return The operating system.
	 * @since 1.8
	 */
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Returns true if the effective system user ID is defined.
	 * 
	 * @return True if the effective system user ID is defined.
	 * @since 1.8
	 */
	public boolean isUID() {
		return uid >= 0;
	}

	/**
	 * Returns the effective system user ID.
	 *
	 * @return The effective system user ID. -1 if not defined.
	 * @since 1.8
	 */
	public int getUID() {
		return uid;
	}

	/**
	 * Returns true if the effective system group ID is defined.
	 * 
	 * @return True if the effective system group ID is defined.
	 * @since 1.8
	 */
	public boolean isGID() {
		return gid >= 0;
	}

	/**
	 * Returns the effective system group ID. -1 if not defined.
	 *
	 * @return The effective system group ID. -1 if not defined.
	 * @since 1.8
	 */
	public int getGID() {
		return gid;
	}

	/**
	 * Returns the application.
	 *
	 * @return The application.
	 * @since 1.8
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * Returns the formated message.
	 * 
	 * @param message The message to format.
	 * @return The formated message.
	 * @since 1.8
	 */
	public String formatLogMessage(String message) {
		return formatCurrentDate() + ": " + message + System.lineSeparator();
	}

	/**
	 * Formats the current date/time string.
	 * 
	 * @return The formatted time string.
	 * @since 1.8
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
	 * @since 1.8
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
	 * @since 1.8
	 */
	public boolean isUserSet() {
		return user != null;
	}

	/**
	 * Returns the user.
	 *
	 * @return The user.
	 * @since 1.8
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Returns the target.
	 *
	 * @return The target.
	 * @since 1.8
	 */
	public Target getTarget() {
		return target;
	}

	/**
	 * Returns the output directory.
	 *
	 * @return The output directory.
	 * @since 1.8
	 */
	public Path getOutput() {
		return output;
	}

	/**
	 * Returns the snapshot track for the output directory.
	 *
	 * @return The snapshot track for the output directory. The track for a root
	 *         snapshot is an empty list. Null if not available.
	 * @since 1.8
	 */
	public List<Integer> getSnapshotTrack() {
		return snapshotTrack;
	}

	/**
	 * Set the snapshotTrack.
	 *
	 * @param snapshotTrack The snapshotTrack to set.
	 * @since 1.8
	 */
	public void setSnapshotTrack(List<Integer> snapshotTrack) {
		this.snapshotTrack = snapshotTrack;
	}

	/**
	 * Returns the processor workspace path.
	 *
	 * @return The processor workspace path. Null if not available.
	 * @since 1.8
	 */
	public Path getProcessorWorkspace() {
		return target == null || target.getSandbox() == null ? null : target.getSandbox().getSnapshots();
	}

	/**
	 * Returns the mets path.
	 *
	 * @return The mets path. Null if not available.
	 * @since 1.8
	 */
	public Path getMets() {
		return getProcessorWorkspace() == null || target.getSandbox().getMets() == null
				|| target.getSandbox().getMets().getFile() == null ? null
						: Paths.get(getProcessorWorkspace().toString(), target.getSandbox().getMets().getFile());
	}

	/**
	 * Returns the mets group.
	 *
	 * @return The mets group. Null if not available.
	 * @since 1.8
	 */
	public String getMetsGroup() {
		return target == null || target.getSandbox() == null || target.getSandbox().getMets() == null ? null
				: target.getSandbox().getMets().getGroup();
	}

	/**
	 * Returns the relative output path of the processor workspace, this means, the
	 * sandbox snapshots root path.
	 *
	 * @return The relative output path of the processor workspace. Null if the
	 *         relative output path can not be specified.
	 * @since 1.8
	 */
	public Path getOutputRelativeProcessorWorkspace() {
		return target == null || target.getSandbox() == null ? null : target.getSandbox().getSnapshotsRelative(output);
	}

	/**
	 * Returns the temporary directory.
	 *
	 * @return The temporary directory.
	 * @since 1.8
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
	 * @since 1.8
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
		 * @since 1.8
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
		 * @since 1.8
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * Returns the name.
		 *
		 * @return The name.
		 * @since 1.8
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the date format.
		 * 
		 * @return The date format.
		 * @since 1.8
		 */
		public SimpleDateFormat getDateFormat() {
			return dateFormat;
		}

	}
}
