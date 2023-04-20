/**
 * File:     MultipleValueArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.List;
import java.util.Optional;

/**
 * Defines multiple value arguments for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @param <V> The value type.
 * @since 1.8
 */
public abstract class MultipleValueArgument<V> extends Argument {
	/**
	 * The values.
	 */
	private final Optional<List<V>> values;

	/**
	 * Creates a multiple value argument for model.
	 * 
	 * @param argument The argument.
	 * @param value    The values.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	MultipleValueArgument(String argument, List<V> values) throws IllegalArgumentException {
		super(argument);

		this.values = values == null || values.isEmpty() ? Optional.empty() : Optional.of(values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.
	 * Argument#isSingleValue()
	 */
	@Override
	public boolean isSingleValue() {
		return false;
	}

	/**
	 * Returns the values.
	 *
	 * @return The values. Empty if no values are present.
	 * @since 1.8
	 */
	public Optional<List<V>> getValues() {
		return values;
	}

}
