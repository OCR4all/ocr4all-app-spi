/**
 * File:     DecimalArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.Optional;

/**
 * Defines decimal values for model arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class DecimalArgument extends SingleValueArgument<Float> {

	/**
	 * Creates a decimal value for a model argument.
	 * 
	 * @param argument The argument.
	 * @param value    The value.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	public DecimalArgument(String argument, Float value) throws IllegalArgumentException {
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
		return new Summary("decimal", getArgument(),
				getValue().isPresent() ? Optional.of("" + getValue().get().floatValue()) : Optional.empty());
	}

}
