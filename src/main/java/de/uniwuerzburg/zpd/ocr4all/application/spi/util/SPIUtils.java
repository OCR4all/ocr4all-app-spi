/**
 * File:     SPIUtils.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     19.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util;

import java.nio.file.Path;
import java.nio.file.Paths;
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

	/**
	 * Returns the relative path part of target path with respect to the root path.
	 * 
	 * @param root   The root path.
	 * @param target The target path.
	 * @return The relative path. Null if the root path is not a prefix of the
	 *         target path.
	 * @since 1.8
	 */
	public static Path getRelativePath(Path root, Path target) {
		if (root == null || target == null)
			return null;
		else {
			target = target.normalize();

			if (!target.startsWith(root))
				return null;
			else
				return Paths.get(root.equals(target) ? "" : target.toString().substring(root.toString().length() + 1));
		}
	}

}
