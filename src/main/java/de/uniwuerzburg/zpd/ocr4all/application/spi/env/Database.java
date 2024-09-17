/**
 * File:     Database.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     17.09.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;

/**
 * Database is an immutable class that defines databases for service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class Database {
	/**
	 * The parent folder.
	 */
	private final Path parent;

	/**
	 * The folder name.
	 */
	private final String name;

	/**
	 * Creates a database for a service provider.
	 * 
	 * @param parent The parent folder.
	 * @param name   The folder name.
	 * @since 17
	 */
	public Database(Path parent, String name) {
		super();

		this.parent = parent;
		this.name = name;
	}

	/**
	 * Returns the parent folder.
	 *
	 * @return The parent folder.
	 * @since 17
	 */
	public Path getParent() {
		return parent;
	}

	/**
	 * Returns the folder name.
	 *
	 * @return The folder name.
	 * @since 17
	 */
	public String getName() {
		return name;
	}

}
