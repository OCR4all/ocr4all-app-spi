/**
 * File:     ImageField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     23.06.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * ImageField is an immutable class that defines image fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class ImageField extends Field<Object> {
	/**
	 * True if it is a workflow image.
	 */
	private final boolean isWorkflow;

	/**
	 * True if hide check box.
	 */
	private final boolean isHideCheckbox;

	/**
	 * True if zoom the thumbnails.
	 */
	private final boolean isZoom;

	/**
	 * True if select by image type.
	 */
	private final boolean isSelectType;

	/**
	 * True if select by image keyword.
	 */
	private final boolean isSelectKeyword;

	/**
	 * True if select by image altogether.
	 */
	private final boolean isSelectAltogether;

	/**
	 * Creates an image field for a model with all select options. Furthermore,
	 * shows the check box and zooms the thumbnails.
	 * 
	 * @param argument The argument.
	 * @param label    The label.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public ImageField(String argument, Internationalization label) throws IllegalArgumentException {
		this(argument, label, null);
	}

	/**
	 * Creates an image field for a model with all select options. Furthermore,
	 * shows the check box and zooms the thumbnails.
	 * 
	 * @param argument    The argument.
	 * @param label       The label.
	 * @param description The description. Null if no description is required.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public ImageField(String argument, Internationalization label, Internationalization description)
			throws IllegalArgumentException {
		this(argument, label, description, false, false);
	}

	/**
	 * Creates an image field for a model with all select options. Furthermore,
	 * shows the check box and zooms the thumbnails.
	 * 
	 * @param argument    The argument.
	 * @param label       The label.
	 * @param description The description. Null if no description is required.
	 * @param isWorkflow  True if it is a workflow image.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public ImageField(String argument, Internationalization label, Internationalization description, boolean isWorkflow,
			boolean isDisabled) throws IllegalArgumentException {
		this(argument, label, description, isWorkflow, false, true, true, true, true, isDisabled);
	}

	/**
	 * Creates an image field for a model.
	 * 
	 * @param argument           The argument.
	 * @param label              The label.
	 * @param description        The description. Null if no description is
	 *                           required.
	 * @param isWorkflow         True if it is a workflow image.
	 * @param isHideCheckbox     True if hide check box.
	 * @param isZoom             True if zoom the thumbnails.
	 * @param isSelectType       True if select by image type.
	 * @param isSelectKeyword    True if select by image keyword.
	 * @param isSelectAltogether True if select by image altogether.
	 * @param isDisabled         True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public ImageField(String argument, Internationalization label, Internationalization description, boolean isWorkflow,
			boolean isHideCheckbox, boolean isZoom, boolean isSelectType, boolean isSelectKeyword,
			boolean isSelectAltogether, boolean isDisabled) throws IllegalArgumentException {
		super(argument, null, label, description, null, isDisabled);

		this.isWorkflow = isWorkflow;
		this.isHideCheckbox = isHideCheckbox;
		this.isZoom = isZoom;
		this.isSelectType = isSelectType;
		this.isSelectKeyword = isSelectKeyword;
		this.isSelectAltogether = isSelectAltogether;
	}

	/**
	 * Returns true if it is a workflow image.
	 *
	 * @return True if it is a workflow image.
	 * @since 1.8
	 */
	public boolean isWorkflow() {
		return isWorkflow;
	}

	/**
	 * Returns true if hide check box.
	 *
	 * @return True if hide check box.
	 * @since 1.8
	 */
	public boolean isHideCheckbox() {
		return isHideCheckbox;
	}

	/**
	 * Returns true if zoom the thumbnails.
	 *
	 * @return True if zoom the thumbnails.
	 * @since 1.8
	 */
	public boolean isZoom() {
		return isZoom;
	}

	/**
	 * Returns true if select by image type.
	 *
	 * @return True if select by image type.
	 * @since 1.8
	 */
	public boolean isSelectType() {
		return isSelectType;
	}

	/**
	 * Returns true if select by image keyword.
	 *
	 * @return True if select by image keyword.
	 * @since 1.8
	 */
	public boolean isSelectKeyword() {
		return isSelectKeyword;
	}

	/**
	 * Returns true if select by image altogether.
	 *
	 * @return True if select by image altogether.
	 * @since 1.8
	 */
	public boolean isSelectAltogether() {
		return isSelectAltogether;
	}

}
