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
	 * The label.
	 */
	private final Internationalization label;

	/**
	 * True if the field is disabled.
	 */
	private final boolean isDisabled;

	/**
	 * Creates an entry for a model.
	 * 
	 * @param label      The label.
	 * @param isDisabled True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the label is null.
	 * @since 1.8
	 */
	Entry(Internationalization label, boolean isDisabled) throws IllegalArgumentException {
		super();

		if (label == null)
			throw new IllegalArgumentException("the label cannot be null.");

		this.label = label;
		this.isDisabled = isDisabled;
	}

	/**
	 * Returns the label.
	 *
	 * @param locale The locale.
	 * @return The label.
	 * @since 1.8
	 */
	public String getLabel(Locale locale) {
		return label.getString(locale);
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
