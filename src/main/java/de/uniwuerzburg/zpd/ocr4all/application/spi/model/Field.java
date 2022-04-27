/**
 * File:     Field.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.Locale;
import java.util.Optional;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * Field is an immutable class that defines fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class Field<F> extends Entry {
	/**
	 * The argument.
	 */
	private final String argument;

	/**
	 * The default value. Empty if not required.
	 */
	private final Optional<F> value;

	/**
	 * The warning. Null if no warning is required.
	 */
	private final Internationalization warning;

	/**
	 * The placeholder. Null if no placeholder is required.
	 */
	private final Internationalization placeholder;

	/**
	 * Creates a field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	Field(String argument, F value, Internationalization description, Internationalization placeholder)
			throws IllegalArgumentException {
		this(argument, value, description, null, placeholder, false);
	}

	/**
	 * Creates a field for a model.
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
	Field(String argument, F value, Internationalization description, Internationalization warning,
			Internationalization placeholder, boolean isDisabled) throws IllegalArgumentException {
		super(description, isDisabled);

		if (argument == null)
			throw new IllegalArgumentException("the argument cannot be null.");

		this.argument = argument;

		this.value = value == null ? Optional.empty() : Optional.of(value);

		this.warning = warning;
		this.placeholder = placeholder;
	}

	/**
	 * Returns the argument.
	 *
	 * @return The argument.
	 * @since 1.8
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 * Returns the default value.
	 *
	 * @return The default value. Empty if not defined.
	 * @since 1.8
	 */
	public Optional<F> getValue() {
		return value;
	}

	/**
	 * Returns the warning.
	 *
	 * @param locale The locale.
	 * @return The warning. Empty if not available.
	 * @since 1.8
	 */
	public Optional<String> getWarning(Locale locale) {
		return warning == null || warning.getString(locale) == null ? Optional.empty()
				: Optional.of(warning.getString(locale));
	}

	/**
	 * Returns the placeholder.
	 *
	 * @param locale The locale.
	 * @return The placeholder. Empty if not available.
	 * @since 1.8
	 */
	public Optional<String> getPlaceholder(Locale locale) {
		return placeholder == null || placeholder.getString(locale) == null ? Optional.empty()
				: Optional.of(placeholder.getString(locale));
	}

}
