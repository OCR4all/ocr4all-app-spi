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
	 * @param description The description.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public DecimalField(String argument, Float value, Internationalization description,
			Internationalization placeholder) throws IllegalArgumentException {
		super(argument, value, description, placeholder);
	}

	/**
	 * Creates a decimal field for a model.
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
	public DecimalField(String argument, Float value, Internationalization description, Internationalization warning,
			Internationalization placeholder, boolean isDisabled) throws IllegalArgumentException {
		super(argument, value, description, warning, placeholder, isDisabled);
	}

	/**
	 * Creates a decimal field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param step        The legal number intervals. Null if no step is required.
	 * @param minimum     The minimum value. Null if no minimum value is required.
	 * @param maximum     The maximum value. Null if no maximum value is required.
	 * @param unit        The unit. Null if no unit is available.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public DecimalField(String argument, Float value, Internationalization description,
			Internationalization placeholder, Float step, Float minimum, Float maximum, Internationalization unit)
			throws IllegalArgumentException {
		super(argument, value, description, placeholder, step, minimum, maximum, unit);
	}

	/**
	 * Creates a decimal field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param warning     The warning. Null if no warning is required.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @param step        The legal number intervals. Null if no step is required.
	 * @param minimum     The minimum value. Null if no minimum value is required.
	 * @param maximum     The maximum value. Null if no maximum value is required.
	 * @param unit        The unit. Null if no unit is available.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public DecimalField(String argument, Float value, Internationalization description, Internationalization warning,
			Internationalization placeholder, Float step, Float minimum, Float maximum, Internationalization unit,
			boolean isDisabled) throws IllegalArgumentException {
		super(argument, value, description, warning, placeholder, step, minimum, maximum, unit, isDisabled);
	}
}
