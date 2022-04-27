/**
 * File:     IntegerArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.Optional;

/**
 * Defines integer values for model arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class IntegerArgument extends SingleValueArgument<Integer> {

	/**
	 * Creates an integer value for a model argument.
	 * 
	 * @param argument The argument.
	 * @param value    The value.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	public IntegerArgument(String argument, Integer value) throws IllegalArgumentException {
		super(argument, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.
	 * Argument#getSummary()
	 */
	@Override
	protected Summary getSummary() {
		return new Summary("integer", getArgument(),
				getValue().isPresent() ? Optional.of("" + getValue().get().intValue()) : Optional.empty());
	}
}
