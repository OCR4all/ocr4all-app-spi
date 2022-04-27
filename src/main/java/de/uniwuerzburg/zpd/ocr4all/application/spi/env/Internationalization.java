/**
 * File:     Internationalization.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     29.01.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.util.Locale;

/**
 * Defines internationalizations.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
@FunctionalInterface
public interface Internationalization {
	/**
	 * Returns the string for given locale.
	 * 
	 * @param locale The locale.
	 * @return The string for given locale. Null if not defined.
	 * @since 1.8
	 */
	public String getString(Locale locale);
}
