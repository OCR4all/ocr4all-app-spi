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
	 * @param label       The label.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public StringField(String argument, String value, Internationalization label, Internationalization placeholder)
			throws IllegalArgumentException {
		this(argument, value, label, null, placeholder, false);
	}

	/**
	 * Creates a string field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param label       The label.
	 * @param description The description. Null if no description is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public StringField(String argument, String value, Internationalization label, Internationalization description,
			Internationalization placeholder, boolean isDisabled) throws IllegalArgumentException {
		this(argument, value, null, label, description, placeholder, isDisabled);
	}

	/**
	 * Creates a string field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param contentType The content type. Null if not required.
	 * @param label       The label.
	 * @param description The description. Null if no description is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public StringField(String argument, String value, String contentType, Internationalization label,
			Internationalization description, Internationalization placeholder, boolean isDisabled)
			throws IllegalArgumentException {
		super(argument, value, label, description, placeholder, isDisabled);

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
