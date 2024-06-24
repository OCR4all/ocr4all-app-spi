/**
 * File:     TrainingFramework.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     24.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;

/**
 * TrainingFramework is an immutable class that defines frameworks for training
 * service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class TrainingFramework extends FrameworkCore {
	/**
	 * The dataset.
	 */
	private final Dataset dataset;

	/**
	 * Creates a training framework for a service provider.
	 * 
	 * @param operatingSystem The operating system.
	 * @param uid             The effective system user ID. -1 if not defined.
	 * @param gid             The effective system group ID. -1 if not defined.
	 * @param application     The application.
	 * @param user            The user. Null if not defined.
	 * @param dataset         The dataset.
	 * @param output          The output directory.
	 * @param temporary       The temporary directory.
	 * @since 17
	 */
	public TrainingFramework(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Dataset dataset, Path output, Path temporary) {
		super(operatingSystem, uid, gid, application, user, output, temporary);

		this.dataset = dataset;
	}

	/**
	 * Returns the dataset.
	 *
	 * @return The dataset.
	 * @since 17
	 */
	public Dataset getDataset() {
		return dataset;
	}

}
