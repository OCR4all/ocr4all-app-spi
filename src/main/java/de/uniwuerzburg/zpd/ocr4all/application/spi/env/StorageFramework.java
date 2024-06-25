/**
 * File:     StorageFramework.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;

/**
 * StorageFramework is an immutable class that defines storage frameworks for
 * service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class StorageFramework extends Framework {
	/**
	 * The output directory.
	 */
	private final Path output;

	/**
	 * Creates a storage framework for a service provider.
	 * 
	 * @param operatingSystem The operating system.
	 * @param uid             The effective system user ID. -1 if not defined.
	 * @param gid             The effective system group ID. -1 if not defined.
	 * @param application     The application.
	 * @param user            The user. Null if not defined.
	 * @param output          The output directory.
	 * @param temporary       The temporary directory.
	 * @since 17
	 */
	public StorageFramework(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Path output, Path temporary) {
		super(operatingSystem, uid, gid, application, user, temporary);

		this.output = output;
	}

	/**
	 * Returns the output directory.
	 *
	 * @return The output directory.
	 * @since 17
	 */
	public Path getOutput() {
		return output;
	}

}
