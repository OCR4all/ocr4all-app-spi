/**
 * File:     RecognitionModelField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     24.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.Optional;

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
	 * Defines types.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public enum Type {
		/**
		 * The Calamari engine.
		 */
		Calamari,
		/**
		 * The Tesseract engine.
		 */
		Tesseract
	}

	/**
	 * The type.
	 */
	private final Type type;

	/**
	 * The minimum version.
	 */
	private final Optional<String> minimumVersion;

	/**
	 * The maximum version.
	 */
	private final Optional<String> maximumVersion;

	/**
	 * True if multiple models can be selected.
	 */
	private final boolean isMultipleModels;

	/**
	 * The suffix for the model file names.
	 */
	private final String suffix;

	/**
	 * Creates a recognition model field for a model. The recognition models of the
	 * application and selected project are required. Multiple models can be
	 * selected.
	 * 
	 * @param argument    The argument.
	 * @param label       The label.
	 * @param description The description. Null if no description is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param type        The type.
	 * @param suffix      The suffix for the model file names.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization label, Internationalization description,
			Internationalization placeholder, Type type, String suffix) throws IllegalArgumentException {
		this(argument, label, description, placeholder, type, null, null, true, suffix);
	}

	/**
	 * Creates a recognition model field for a model.
	 * 
	 * @param argument         The argument.
	 * @param label            The label.
	 * @param description      The description. Null if no description is required.
	 * @param placeholder      The placeholder. Null if no placeholder is required.
	 * @param type             The type.
	 * @param minimumVersion   The minimum version.
	 * @param maximumVersion   The maximum version.
	 * @param isMultipleModels True if multiple models can be selected.
	 * @param suffix           The suffix for the model file names.
	 * @throws IllegalArgumentException Throws if the argument or the label or the
	 *                                  type is null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization label, Internationalization description,
			Internationalization placeholder, Type type, String minimumVersion, String maximumVersion,
			boolean isMultipleModels, String suffix) throws IllegalArgumentException {
		this(argument, label, description, placeholder, type, minimumVersion, maximumVersion, isMultipleModels, suffix,
				false);
	}

	/**
	 * Creates a recognition model field for a model.
	 * 
	 * @param argument         The argument.
	 * @param label            The label.
	 * @param description      The description. Null if no description is required.
	 * @param placeholder      The placeholder. Null if no placeholder is required.
	 * @param type             The type.
	 * @param minimumVersion   The minimum version.
	 * @param maximumVersion   The maximum version.
	 * @param isMultipleModels True if multiple models can be selected.
	 * @param suffix           The suffix for the model file names.
	 * @param isDisabled       True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the label or the
	 *                                  type is null.
	 * @since 1.8
	 */
	public RecognitionModelField(String argument, Internationalization label, Internationalization description,
			Internationalization placeholder, Type type, String minimumVersion, String maximumVersion,
			boolean isMultipleModels, String suffix, boolean isDisabled) throws IllegalArgumentException {
		super(argument, null, label, description, placeholder, isDisabled);

		if (type == null)
			throw new IllegalArgumentException("the argument type can not be null.");

		if (suffix == null || suffix.isBlank())
			throw new IllegalArgumentException("the argument suffix can not be empty.");

		this.type = type;

		this.minimumVersion = minimumVersion == null || minimumVersion.isBlank() ? Optional.empty()
				: Optional.of(minimumVersion.trim());
		this.maximumVersion = maximumVersion == null || maximumVersion.isBlank() ? Optional.empty()
				: Optional.of(maximumVersion.trim());

		this.isMultipleModels = isMultipleModels;

		this.suffix = suffix.trim();
	}

	/**
	 * Returns the type.
	 *
	 * @return The type.
	 * @since 17
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Returns the minimum version.
	 *
	 * @return The minimum version.
	 * @since 17
	 */
	public Optional<String> getMinimumVersion() {
		return minimumVersion;
	}

	/**
	 * Returns the maximum version.
	 *
	 * @return The maximum version.
	 * @since 17
	 */
	public Optional<String> getMaximumVersion() {
		return maximumVersion;
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

	/**
	 * Returns the suffix for the model file names.
	 *
	 * @return The suffix for the model file names.
	 * @since 17
	 */
	public String getSuffix() {
		return suffix;
	}

}
