/**
 * File:     SPIUtils.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     19.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util;

import java.util.UUID;

/**
 * Defines spi utilities.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class SPIUtils {

	/**
	 * Returns an immutable universally unique identifier ({@code UUID}). The
	 * {@code UUID} represents a 128-bit value and is generated using a
	 * cryptographically strong pseudo random number generator.
	 * 
	 * @return A randomly generated {@code UUID}.
	 * @since 1.8
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

}
