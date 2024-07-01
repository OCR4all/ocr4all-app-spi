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
public class TrainingFramework extends StorageFramework {
	/**
	 * The dataset.
	 */
	private final Dataset dataset;

	/**
	 * The model configuration.
	 */
	private final ModelConfiguration modelConfiguration;

	/**
	 * Creates a training framework for a service provider.
	 * 
	 * @param operatingSystem    The operating system.
	 * @param uid                The effective system user ID. -1 if not defined.
	 * @param gid                The effective system group ID. -1 if not defined.
	 * @param application        The application.
	 * @param user               The user. Null if not defined.
	 * @param dataset            The dataset.
	 * @param modelConfiguration The model configuration.
	 * @param output             The output directory.
	 * @param temporary          The temporary directory.
	 * @since 17
	 */
	public TrainingFramework(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Dataset dataset, ModelConfiguration modelConfiguration, Path output, Path temporary) {
		super(operatingSystem, uid, gid, application, user, output, temporary);

		this.dataset = dataset;
		this.modelConfiguration = modelConfiguration;
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

	/**
	 * Returns the model configuration.
	 *
	 * @return The model configuration.
	 * @since 17
	 */
	public ModelConfiguration getModelConfiguration() {
		return modelConfiguration;
	}

	/**
	 * Defines model configurations.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public static class ModelConfiguration {
		/**
		 * The folder.
		 */
		private final String folder;

		/**
		 * The engine file.
		 */
		private final String engine;

		/**
		 * Creates model configurations.
		 * 
		 * @param folder The folder.
		 * @param engine The engine file.
		 * @since 17
		 */
		public ModelConfiguration(String folder, String engine) {
			super();
			this.folder = folder;
			this.engine = engine;
		}

		/**
		 * Returns the folder.
		 *
		 * @return The folder.
		 * @since 17
		 */
		public String getFolder() {
			return folder;
		}

		/**
		 * Returns the engine.
		 *
		 * @return The engine.
		 * @since 17
		 */
		public String getEngine() {
			return engine;
		}

	}
}
