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
	 * Defines roles.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public enum Role {
		creator, other
	}

	/**
	 * Defines notes.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public enum Note {
		inputFileGroup("input-file-grp"), outputFileGroup("output-file-grp"), parameter, pageId("page-id");

		/**
		 * The label.
		 */
		private final String label;

		/**
		 * Default constructor for a note.
		 * 
		 * @since 1.8
		 */
		private Note() {
			label = this.name();
		}

		/**
		 * Creates a note.
		 * 
		 * @param label The label.
		 * @since 1.8
		 */
		private Note(String label) {
			this.label = label;
		}

		/**
		 * Returns the note with given label.
		 * 
		 * @param label The label.
		 * @return The note with given label.
		 * @since 1.8
		 */
		public static Note getNote(String label) {
			if (label != null && !label.isBlank()) {
				label = label.trim().toLowerCase();

				for (Note note : Note.values())
					if (note.label.equals(label))
						return note;
			}

			return null;
		}
	}

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
	 * Returns the agent role.
	 * 
	 * @param role      The role.
	 * @param otherRole The other role.
	 * @return The agent role.
	 * @since 1.8
	 */
	public static String getAgentRole(String role, String otherRole) {
		if (Role.creator.name().equalsIgnoreCase(role))
			return "creator";
		else if (Role.other.name().equalsIgnoreCase(role))
			return otherRole;
		else
			return null;

	}

	/**
	 * Returns the mets file group for given framework.
	 * 
	 * @param framework The framework.
	 * @return The mets file group. Null if framework is null.
	 * @since 1.8
	 */
	public static FrameworkFileGroup getFileGroup(Framework framework) {
		return framework == null ? null : new FrameworkFileGroup(framework);
	}

	/**
	 * Returns the mets file group for given prefix.
	 * 
	 * @param prefix The group prefix.
	 * @return The mets file group.
	 * @since 1.8
	 */
	public static FileGroup getFileGroup(String prefix) {
		return new FileGroup(prefix);
	}

	/**
	 * Returns the mets page id prefix for given group prefix.
	 * 
	 * @param prefix The group prefix.
	 * @return The mets page id prefix.
	 * @since 1.8
	 */
	public static Page getPage(String prefix) {
		return new Page(prefix);
	}

	/**
	 * Defines utilities for mets file groups.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class FileGroup {
		/**
		 * The group prefix.
		 */
		private final String prefix;

		/**
		 * Creates utilities for a mets file group.
		 * 
		 * @param prefix The group prefix.
		 * @since 1.8
		 */
		protected FileGroup(String prefix) {
			super();

			this.prefix = prefix == null ? "" : prefix.trim();
		}

		/**
		 * Returns the group prefix.
		 *
		 * @return The group prefix.
		 * @since 1.8
		 */
		public String getPrefix() {
			return prefix;
		}

		/**
		 * Returns the mets file group for given track.
		 * 
		 * @param track The track.
		 * @return The mets file group. If the track or one of its elements is null,
		 *         null is returned.
		 * @since 1.8
		 */
		public String getFileGroup(List<Integer> track) {
			if (track == null)
				return null;
			else
				try {
					StringBuffer path = new StringBuffer();

					for (int snapshotId : track)
						path.append("-" + snapshotId);

					return prefix + path.toString();
				} catch (Exception e) {
					return null;
				}
		}

		/**
		 * Returns the track for given mets file group.
		 * 
		 * @param group The mets file group.
		 * @return The track.
		 * @since 1.8
		 */
		public List<Integer> getTrack(String group) {
			if (group == null || !group.startsWith(prefix))
				return null;
			else if (group.equals(prefix))
				return new ArrayList<>();
			else if (!group.startsWith(prefix + "-") || group.equals(prefix + "-"))
				return null;
			else {
				List<Integer> track = new ArrayList<>();
				for (String id : group.substring((prefix + "-").length()).split("-"))
					try {
						track.add(Integer.parseInt(id));
					} catch (Exception e) {
						return null;
					}

				return track;
			}
		}

	}

	/**
	 * Defines mets file groups for frameworks.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class FrameworkFileGroup extends FileGroup {
		/**
		 * The input file group.
		 */
		private final String input;

		/**
		 * The output file group.
		 */
		private final String output;

		/**
		 * Creates a mets file group for a framework.
		 * 
		 * @param framework The framework.
		 * @since 1.8
		 */
		protected FrameworkFileGroup(Framework framework) {
			super(framework.getMetsGroup());

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
	 * Defines utilities for mets pages.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Page {
		/**
		 * The id pattern.
		 */
		private static final String idPattern = "-id_";

		/**
		 * The group prefix.
		 */
		private final String prefix;

		/**
		 * Creates utilities for a mets page.
		 * 
		 * @param prefix The group prefix.
		 * @since 1.8
		 */
		protected Page(String prefix) {
			super();

			this.prefix = prefix == null ? "" : prefix.trim();
		}

		/**
		 * Returns the group prefix.
		 *
		 * @return The group prefix.
		 * @since 1.8
		 */
		public String getPrefix() {
			return prefix;
		}

		/**
		 * Returns the page id.
		 * 
		 * @param id The group id.
		 * @return The page id.
		 * @since 1.8
		 */
		public String getId(String id) {
			return prefix + idPattern + (id == null ? "" : id.trim());
		}

		/**
		 * Returns the group id.
		 * 
		 * @param id The page id.
		 * @return The group id.
		 * @since 1.8
		 */
		public String getGroupId(String id) {
			if (id == null)
				return null;

			id = id.trim();

			return id.startsWith(prefix + idPattern) ? id.substring((prefix + idPattern).length()) : null;
		}

	}

}
