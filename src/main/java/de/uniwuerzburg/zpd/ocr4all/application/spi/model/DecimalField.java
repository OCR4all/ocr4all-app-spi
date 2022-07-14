/**
 * File:     DecimalField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * DecimalField is an immutable class that defines decimal fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class DecimalField extends NumberField<Float> {
	/**
	 * Creates a decimal field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param label       The label.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public DecimalField(String argument, Float value, Internationalization label, Internationalization placeholder)
			throws IllegalArgumentException {
		super(argument, value, label, placeholder);
	}

	/**
	 * Creates a decimal field for a model.
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
	public DecimalField(String argument, Float value, Internationalization label, Internationalization description,
			Internationalization placeholder, boolean isDisabled) throws IllegalArgumentException {
		super(argument, value, label, description, placeholder, isDisabled);
	}

	/**
	 * Creates a decimal field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param label       The label.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param step        The legal number intervals. Null if no step is required.
	 * @param minimum     The minimum value. Null if no minimum value is required.
	 * @param maximum     The maximum value. Null if no maximum value is required.
	 * @param unit        The unit. Null if no unit is available.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public DecimalField(String argument, Float value, Internationalization label, Internationalization placeholder,
			Float step, Float minimum, Float maximum, Internationalization unit) throws IllegalArgumentException {
		super(argument, value, label, placeholder, step, minimum, maximum, unit);
	}

	/**
	 * Creates a decimal field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param label       The label.
	 * @param description The description. Null if no description is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param step        The legal number intervals. Null if no step is required.
	 * @param minimum     The minimum value. Null if no minimum value is required.
	 * @param maximum     The maximum value. Null if no maximum value is required.
	 * @param unit        The unit. Null if no unit is available.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the label is null.
	 * @since 1.8
	 */
	public DecimalField(String argument, Float value, Internationalization label, Internationalization description,
			Internationalization placeholder, Float step, Float minimum, Float maximum, Internationalization unit,
			boolean isDisabled) throws IllegalArgumentException {
		super(argument, value, label, description, placeholder, step, minimum, maximum, unit, isDisabled);
	}
}
