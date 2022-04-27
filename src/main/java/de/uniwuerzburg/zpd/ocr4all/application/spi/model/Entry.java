/**
 * File:     Entry.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.Locale;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * Entry is an immutable class that defines entries for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class Entry {
	/**
	 * The description.
	 */
	private final Internationalization description;

	/**
	 * True if the field is disabled.
	 */
	private final boolean isDisabled;

	/**
	 * Creates an entry for a model.
	 * 
	 * @param description The description.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the description is null.
	 * @since 1.8
	 */
	Entry(Internationalization description, boolean isDisabled) throws IllegalArgumentException {
		super();

		if (description == null)
			throw new IllegalArgumentException("the description cannot be null.");

		this.description = description;
		this.isDisabled = isDisabled;
	}

	/**
	 * Returns the description.
	 *
	 * @param locale The locale.
	 * @return The description.
	 * @since 1.8
	 */
	public String getDescription(Locale locale) {
		return description.getString(locale);
	}

	/**
	 * Returns true if the field is disabled.
	 *
	 * @return True if the field is disabled.
	 * @since 1.8
	 */
	public boolean isDisabled() {
		return isDisabled;
	}

}
