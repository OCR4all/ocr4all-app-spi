/**
 * File:     SingleValueArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.Optional;

/**
 * Defines single value arguments for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @param <V> The value type.
 * @since       1.8
 */
public abstract class SingleValueArgument<V> extends Argument {
	/**
	 * The value.
	 */
	private final Optional<V> value;

	/**
	 * Creates a single value argument for model.
	 * 
	 * @param argument The argument.
	 * @param value    The value.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	SingleValueArgument(String argument, V value) throws IllegalArgumentException {
		super(argument);

		this.value = value == null ? Optional.empty() : Optional.of(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.
	 * Argument#isSingleValue()
	 */
	@Override
	public boolean isSingleValue() {
		return true;
	}

	/**
	 * Returns the value.
	 *
	 * @return The value. Empty if no value is present.
	 * @since 1.8
	 */
	public Optional<V> getValue() {
		return value;
	}

}
