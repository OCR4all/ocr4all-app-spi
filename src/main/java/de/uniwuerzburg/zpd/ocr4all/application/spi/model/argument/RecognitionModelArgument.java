/**
 * File:     RecognitionModelArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.List;
import java.util.Optional;

/**
 * Defines recognition model values for model arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class RecognitionModelArgument extends MultipleValueArgument<String> {

	/**
	 * Creates recognition model values for a model argument.
	 * 
	 * @param argument The argument.
	 * @param values   The values.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	public RecognitionModelArgument(String argument, List<String> values) throws IllegalArgumentException {
		super(argument, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.
	 * Argument#getSummary()
	 */
	@Override
	protected Summary getSummary() {
		return new Summary("recognition model", getArgument(),
				getValues().isPresent() ? Optional.of(getValues().get().toString()) : Optional.empty());
	}
}
