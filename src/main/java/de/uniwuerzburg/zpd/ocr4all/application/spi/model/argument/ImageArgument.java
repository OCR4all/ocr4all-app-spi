/**
 * File:     ImageArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     23.06.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.List;
import java.util.Optional;

/**
 * Defines image values for model arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class ImageArgument extends MultipleValueArgument<Integer> {

	/**
	 * Creates image values for a model argument.
	 * 
	 * @param argument The argument.
	 * @param values   The values.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	public ImageArgument(String argument, List<Integer> values) throws IllegalArgumentException {
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
		return new Summary("image", getArgument(),
				getValues().isPresent() ? Optional.of(getValues().get().toString()) : Optional.empty());
	}
}
