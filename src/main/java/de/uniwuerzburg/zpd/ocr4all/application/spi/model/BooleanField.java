/**
 * File:     BooleanField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * BooleanField is an immutable class that defines boolean fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class BooleanField extends Field<Boolean> {

	/**
	 * Creates a boolean field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public BooleanField(String argument, Boolean value, Internationalization description)
			throws IllegalArgumentException {
		super(argument, value, description, null, null, false);
	}

	/**
	 * Creates a boolean field for a model.
	 * 
	 * @param argument    The argument.
	 * @param value       The default value. Null if not required.
	 * @param description The description.
	 * @param warning     The warning. Null if no warning is required.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument or the description is
	 *                                  null.
	 * @since 1.8
	 */
	public BooleanField(String argument, Boolean value, Internationalization description, Internationalization warning,
			boolean isDisabled) throws IllegalArgumentException {
		super(argument, value, description, warning, null, isDisabled);
	}

}
