/**
 * File:     StringArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.Optional;

/**
 * Defines string values for model arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class StringArgument extends SingleValueArgument<String> {

	/**
	 * Creates a string value for a model argument.
	 * 
	 * @param argument The argument.
	 * @param value    The value.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	public StringArgument(String argument, String value) throws IllegalArgumentException {
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
		return new Summary("string", getArgument(),
				getValue().isPresent() ? Optional.of(getValue().get()) : Optional.empty());
	}
}
