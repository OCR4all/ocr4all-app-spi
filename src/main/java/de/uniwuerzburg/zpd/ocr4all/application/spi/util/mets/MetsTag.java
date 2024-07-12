/**
 * File:     MetsTag.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util.mets
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     12.07.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util.mets;

/**
 * Defines mets xml tags.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public enum MetsTag {
	header("metsHdr"),

	fileSection("fileSec"),

	structureMap("structMap"), physicalSequence(false, "div"), pages(false, "div"), fileId(false, "fptr");

	/**
	 * True if it is a main tag.
	 */
	private final boolean isMainTag;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * Creates a main mets xml tag.
	 * 
	 * @param name The name.
	 * @since 17
	 */
	private MetsTag(String name) {
		this.isMainTag = true;
		this.name = name;
	}

	/**
	 * Creates a mets xml tag.
	 * 
	 * @param isMainTag True if it is a main tag.
	 * @param name      The name.
	 * @since 17
	 */
	private MetsTag(boolean isMainTag, String name) {
		this.isMainTag = isMainTag;
		this.name = name;
	}

	/**
	 * Returns true if it is a main tag.
	 *
	 * @return True if it is a main tag.
	 * @since 17
	 */
	public boolean isMainTag() {
		return isMainTag;
	}

	/**
	 * Returns the tag.
	 * 
	 * @return The tag.
	 * @since 17
	 */
	public String getTag() {
		return "mets:" + name;
	}

	/**
	 * Returns true if the given line contains the open tag.
	 * 
	 * @param line The line.
	 * @return True if the given line contains the open tag.
	 * @since 17
	 */
	public boolean isOpenTag(String line) {
		return line != null && line.contains("<" + getTag());
	}

	/**
	 * Returns true if the given line contains the close tag.
	 * 
	 * @param line The line.
	 * @return True if the given line contains the close tag.
	 * @since 17
	 */
	public boolean isCloseTag(String line) {
		return line != null && line.contains("</" + getTag() + ">");
	}

	/**
	 * Returns the main mets xml tag in the given line.
	 * 
	 * @param line The line to search for the tag.
	 * @return The main mets xml tag. Null if no tag is found.
	 * @since 17
	 */
	public static MetsTag getMainOpenTag(String line) {
		if (line != null)
			for (MetsTag tag : MetsTag.values())
				if (tag.isMainTag() && tag.isOpenTag(line))
					return tag;

		return null;
	}
}
