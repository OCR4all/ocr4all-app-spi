/**
 * File:     StringField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.Optional;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * StringField is an immutable class that defines string fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class StringField extends Field<String> {
	/**
	 * The content type.
	 */
	private final Optional<String> contentType;

	/**
	 * Creates a string field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public StringField(String argument, String value, Internationalization description,
			Internationalization placeholder) throws IllegalArgumentException {
		this(argument, value, description, null, placeholder, false);
	}

	/**
	 * Creates a string field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param warning     The warning. Null if no warning is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public StringField(String argument, String value, Internationalization description, Internationalization warning,
			Internationalization placeholder, boolean isDisabled) throws IllegalArgumentException {
		this(argument, value, null, description, warning, placeholder, isDisabled);
	}

	/**
	 * Creates a string field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param contentType The content type. Null if not required.
	 * @param description The description.
	 * @param warning     The warning. Null if no warning is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public StringField(String argument, String value, String contentType, Internationalization description,
			Internationalization warning, Internationalization placeholder, boolean isDisabled)
			throws IllegalArgumentException {
		super(argument, value, description, warning, placeholder, isDisabled);

		this.contentType = contentType == null ? Optional.empty() : Optional.of(contentType);
	}

	/**
	 * Returns the content type.
	 *
	 * @return The content type.
	 * @since 1.8
	 */
	public Optional<String> getContentType() {
		return contentType;
	}

}
