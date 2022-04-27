/**
 * File:     NumberField.java
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
 * NumberField is an immutable class that defines number fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class NumberField<N extends Number> extends Field<N> {
	/**
	 * The legal number intervals.
	 */
	private final Optional<N> step;

	/**
	 * The minimum value.
	 */
	private final Optional<N> minimum;

	/**
	 * The maximum value.
	 */
	private final Optional<N> maximum;

	/**
	 * The unit. Null if no unit is available.
	 */
	private final Internationalization unit;

	/**
	 * Creates a number field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param placeholder The placeholder. Null if no placeholder is required.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	NumberField(String argument, N value, Internationalization description, Internationalization placeholder)
			throws IllegalArgumentException {
		this(argument, value, description, null, placeholder, false);
	}

	/**
	 * Creates a number field for a model.
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
	NumberField(String argument, N value, Internationalization description, Internationalization warning,
			Internationalization placeholder, boolean isDisabled) throws IllegalArgumentException {
		this(argument, value, description, warning, placeholder, null, null, null, null, isDisabled);
	}

	/**
	 * Creates a number field for a model.
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
	NumberField(String argument, N value, Internationalization description, Internationalization placeholder, N step,
			N minimum, N maximum, Internationalization unit) throws IllegalArgumentException {
		this(argument, value, description, null, placeholder, step, minimum, maximum, unit, false);
	}

	/**
	 * Creates a number field for a model.
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
	NumberField(String argument, N value, Internationalization description, Internationalization warning,
			Internationalization placeholder, N step, N minimum, N maximum, Internationalization unit,
			boolean isDisabled) throws IllegalArgumentException {
		super(argument, value, description, warning, placeholder, isDisabled);

		this.step = step == null ? Optional.empty() : Optional.of(step);
		this.minimum = minimum == null ? Optional.empty() : Optional.of(minimum);
		this.maximum = maximum == null ? Optional.empty() : Optional.of(maximum);
		this.unit = unit;
	}

	/**
	 * Returns the legal number intervals.
	 *
	 * @return The legal number intervals.
	 * @since 1.8
	 */
	public Optional<N> getStep() {
		return step;
	}

	/**
	 * Returns the minimum.
	 *
	 * @return The minimum. Empty if not available.
	 * @since 1.8
	 */
	public Optional<N> getMinimum() {
		return minimum;
	}

	/**
	 * Returns the maximum.
	 *
	 * @return The maximum. Empty if not available.
	 * @since 1.8
	 */
	public Optional<N> getMaximum() {
		return maximum;
	}

	/**
	 * Returns the unit.
	 *
	 * @param locale The locale.
	 * @return The unit. Empty if not available.
	 * @since 1.8
	 */
	public Optional<String> getUnit(Locale locale) {
		return unit == null || unit.getString(locale) == null ? Optional.empty() : Optional.of(unit.getString(locale));
	}

}
