/**
 * File:     MetsUtils.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     14.09.2022
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Framework;

/**
 * Defines mets utilities.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class MetsUtils {
	/**
	 * The pattern describing mets the date and time format.
	 */
	public static final String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * The mets date format.
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

	/**
	 * Returns the current date formatted for mets.
	 * 
	 * @return The current date formatted for mets.
	 * @since 1.8
	 */
	public static String getFormattedDate() {
		return getFormattedDate(null);
	}

	/**
	 * Returns the date formatted for mets.
	 * 
	 * @param date The date. If null, use the current date.
	 * @return The date formatted for mets.
	 * @since 1.8
	 */
	public static String getFormattedDate(Date date) {
		return dateFormat.format(date == null ? new Date() : date);
	}

	/**
	 * Returns the respective date of the given mets formatted date.
	 * 
	 * @param formattedDate The mets formatted date.
	 * @return The date.
	 * @throws ParseException Throws if the beginning of the specified string cannot
	 *                        be parsed.
	 * @since 1.8
	 */
	public static Date getDate(String formattedDate) throws ParseException {
		return dateFormat.parse(formattedDate);
	}

	/**
	 * Returns the mets file group for given framework.
	 * 
	 * @param framework The framework.
	 * @return The mets file group. Null if framework is null.
	 * @since 1.8
	 */
	public static FileGroup getFileGroup(Framework framework) {
		return framework == null ? null : new FileGroup(framework);
	}

	/**
	 * Returns the mets file group for given framework.
	 * 
	 * @param framework The framework.
	 * @return The mets file group. Null if framework is null.
	 * @since 1.8
	 */
	public static TrackUtils getTrack(String fileGroupPrefix) {
		return fileGroupPrefix == null || fileGroupPrefix.isBlank() ? null : new TrackUtils(fileGroupPrefix);
	}

	/**
	 * FileGroup is an immutable class that defines mets file groups.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class FileGroup {
		/**
		 * The file group prefix.
		 */
		private final String fileGroupPrefix;

		/**
		 * The input file group.
		 */
		private final String input;

		/**
		 * The output file group.
		 */
		private final String output;

		/**
		 * Creates a mets file group.
		 * 
		 * @param framework The framework.
		 * @since 1.8
		 */
		protected FileGroup(Framework framework) {
			super();

			fileGroupPrefix = framework.getMetsGroup();

			String fileGroup = null;
			try {
				fileGroup = getFileGroup(framework.getTarget().getWorkflow().getSnapshotTrack());
			} catch (Exception e) {
				// Ignore value
			}
			input = fileGroup;

			fileGroup = null;
			try {
				fileGroup = getFileGroup(framework.getSnapshotTrack());
			} catch (Exception e) {
				// Ignore value
			}
			output = fileGroup;
		}

		/**
		 * Returns the file group.
		 * 
		 * @param snapshotTrack The snapshot track.
		 * @return The file group.
		 * @since 1.8
		 */
		private String getFileGroup(List<Integer> snapshotTrack) {
			StringBuffer path = new StringBuffer();

			for (int snapshotId : snapshotTrack)
				path.append("-" + snapshotId);

			return fileGroupPrefix + path.toString();
		}

		/**
		 * Returns the file group prefix.
		 *
		 * @return The file group prefix.
		 * @since 1.8
		 */
		public String getFileGroupPrefix() {
			return fileGroupPrefix;
		}

		/**
		 * Returns the input file group.
		 *
		 * @return The input file group.
		 * @since 1.8
		 */
		public String getInput() {
			return input;
		}

		/**
		 * Returns the output file group.
		 *
		 * @return The output file group.
		 * @since 1.8
		 */
		public String getOutput() {
			return output;
		}
	}

	/**
	 * Defines utilities to extract tracks from mets file groups.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class TrackUtils {
		/**
		 * The file group prefix.
		 */
		private final String fileGroupPrefix;

		/**
		 * Creates utilities to extract tracks from a mets file group.
		 * 
		 * @param fileGroupPrefix The file group prefix.
		 * @since 1.8
		 */
		protected TrackUtils(String fileGroupPrefix) {
			super();

			this.fileGroupPrefix = fileGroupPrefix.trim();
		}

		/**
		 * Returns the file group prefix.
		 *
		 * @return The file group prefix.
		 * @since 1.8
		 */
		public String getFileGroupPrefix() {
			return fileGroupPrefix;
		}

		/**
		 * Returns the track for given mets file group.
		 * 
		 * @param fileGroup The mets file group.
		 * @return The track.
		 * @since 1.8
		 */
		public List<Integer> getTrack(String fileGroup) {
			if (fileGroup == null || !fileGroup.startsWith(fileGroupPrefix))
				return null;
			else if (fileGroup.equals(fileGroupPrefix))
				return new ArrayList<>();
			else if (!fileGroup.startsWith(fileGroupPrefix + "-") || fileGroup.equals(fileGroupPrefix + "-"))
				return null;
			else {
				List<Integer> track = new ArrayList<>();
				for (String id : fileGroup.substring((fileGroupPrefix + "-").length()).split("-"))
					try {
						track.add(Integer.parseInt(id));
					} catch (Exception e) {
						return null;
					}

				return track;
			}
		}

	}

}
