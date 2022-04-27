/**
 * File:     RecognitionModelField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     24.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * RecognitionModelField is an immutable class that defines recognition model
 * fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class RecognitionModelField extends Field<String> {
	/**
	 * True if the application recognition models are required.
	 */
	private final boolean isApplicationModels;

	/**
	 * True if the recognition models of the selected project are required.
	 */
	private final boolean isProjectModels;

	/**
	 * True if the recognition models of the remainder projects are required.
	 */
	private final boolean isRemainderProjectsModels;

	/**
	 * True if multiple models can be selected.
	 */
	private final boolean isMultipleModels;

	/**
	 * Creates a recognition model field for a model. The recognition models of the
	 * application and selected project are required. Multiple models can be
	 * selected.
	 * 
	 * @param argument    The argument.
	 * @param description The description.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization description, Internationalization placeholder)
			throws IllegalArgumentException {
		this(argument, description, placeholder, true, true, false, true);
	}

	/**
	 * Creates a recognition model field for a model.
	 * 
	 * @param argument                  The argument.
	 * @param description               The description.
	 * @param placeholder               The placeholder. Null if no placeholder is
	 *                                  required.
	 * @param isApplicationModels       True if the application recognition models
	 *                                  are required.
	 * @param isProjectModels           True if the recognition models of the
	 *                                  selected project are required.
	 * @param isRemainderProjectsModels True if the recognition models of the
	 *                                  remainder projects are required.
	 * @param isMultipleModels          True if multiple models can be selected.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization description, Internationalization placeholder,
			boolean isApplicationModels, boolean isProjectModels, boolean isRemainderProjectsModels,
			boolean isMultipleModels) throws IllegalArgumentException {
		super(argument, null, description, null, placeholder, false);

		this.isApplicationModels = isApplicationModels;
		this.isProjectModels = isProjectModels;
		this.isRemainderProjectsModels = isRemainderProjectsModels;
		this.isMultipleModels = isMultipleModels;
	}

	/**
	 * Creates a recognition model field for a model. The recognition models of the
	 * application and selected project are required. Multiple models can be
	 * selected.
	 * 
	 * @param argument    The argument.
	 * @param description The description.
	 * @param warning     The warning. Null if no warning is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization description, Internationalization warning,
			Internationalization placeholder) throws IllegalArgumentException {
		this(argument, description, warning, placeholder, true, true, false, true, false);
	}

	/**
	 * Creates a recognition model field for a model.
	 * 
	 * @param argument                  The argument.
	 * @param description               The description.
	 * @param warning                   The warning. Null if no warning is required.
	 * @param placeholder               The placeholder. Null if no placeholder is
	 *                                  required.
	 * @param isApplicationModels       True if the application recognition models
	 *                                  are required.
	 * @param isProjectModels           True if the recognition models of the
	 *                                  selected project are required.
	 * @param isRemainderProjectsModels True if the recognition models of the
	 *                                  remainder projects are required.
	 * @param isMultipleModels          True if multiple models can be selected.
	 * @param isDisabled                True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization description, Internationalization warning,
			Internationalization placeholder, boolean isApplicationModels, boolean isProjectModels,
			boolean isRemainderProjectsModels, boolean isMultipleModels, boolean isDisabled)
			throws IllegalArgumentException {
		super(argument, null, description, warning, placeholder, isDisabled);

		this.isApplicationModels = isApplicationModels;
		this.isProjectModels = isProjectModels;
		this.isRemainderProjectsModels = isRemainderProjectsModels;
		this.isMultipleModels = isMultipleModels;
	}

	/**
	 * Returns true if the application recognition models are required.
	 *
	 * @return True if the application recognition models are required.
	 * @since 1.8
	 */
	public boolean isApplicationModels() {
		return isApplicationModels;
	}

	/**
	 * Returns true if the recognition models of the selected project are required.
	 *
	 * @return True if the recognition models of the selected project are required.
	 * @since 1.8
	 */
	public boolean isProjectModels() {
		return isProjectModels;
	}

	/**
	 * Returns true if the recognition models of the remainder projects are
	 * required.
	 *
	 * @return True if the recognition models of the remainder projects are
	 *         required.
	 * @since 1.8
	 */
	public boolean isRemainderProjectsModels() {
		return isRemainderProjectsModels;
	}

	/**
	 * Returns true if multiple models can be selected.
	 *
	 * @return True if multiple models can be selected.
	 * @since 1.8
	 */
	public boolean isMultipleModels() {
		return isMultipleModels;
	}

}
