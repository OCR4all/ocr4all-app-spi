/**
 * File:     TrainingFramework.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     24.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * TrainingFramework is an immutable class that defines frameworks for training
 * service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class TrainingFramework extends Framework {
	/**
	 * The folder for data.
	 */
	private final Path data;

	/**
	 * The folder for assemble.
	 */
	private final Path assemble;

	/**
	 * The dataset.
	 */
	private final Dataset dataset;

	/**
	 * The model configuration.
	 */
	private final ModelConfiguration modelConfiguration;

	/**
	 * The model id.
	 */
	private final String modelId;

	/**
	 * Creates a training framework for a service provider.
	 * 
	 * @param operatingSystem    The operating system.
	 * @param uid                The effective system user ID. -1 if not defined.
	 * @param gid                The effective system group ID. -1 if not defined.
	 * @param application        The application.
	 * @param user               The user. Null if not defined.
	 * @param data               The data folder.
	 * @param assemble           The assemble folder.
	 * @param dataset            The dataset.
	 * @param modelConfiguration The model configuration.
	 * @param dataset            The dataset.
	 * @param temporary          The temporary directory.
	 * @since 17
	 */
	public TrainingFramework(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Path data, Path assemble, Dataset dataset, ModelConfiguration modelConfiguration, String modelId,
			Path temporary) {
		super(operatingSystem, uid, gid, application, user, temporary);

		this.data = data;
		this.assemble = assemble;
		this.dataset = dataset;
		this.modelConfiguration = modelConfiguration;
		this.modelId = modelId == null || modelId.isBlank() ? null : modelId.trim();
	}

	/**
	 * Returns true if the folder for data is a directory.
	 *
	 * @return True if the folder is a directory; false if the folder does not
	 *         exist, is not a directory, or it cannot be determined if the folder
	 *         is a directory or not.
	 * 
	 * @since 1.8
	 */
	public boolean isDataDirectory() {
		return Files.isDirectory(data);
	}

	/**
	 * Returns the folder for data.
	 *
	 * @return The folder for data.
	 * @since 1.8
	 */
	public Path getData() {
		return data;
	}

	/**
	 * Returns true if the folder for assemble is a directory.
	 *
	 * @return True if the folder is a directory; false if the folder does not
	 *         exist, is not a directory, or it cannot be determined if the folder
	 *         is a directory or not.
	 * 
	 * @since 1.8
	 */
	public boolean isAssembleDirectory() {
		return Files.isDirectory(assemble);
	}

	/**
	 * Returns the folder for assemble.
	 *
	 * @return The folder for assemble.
	 * @since 1.8
	 */
	public Path getAssemble() {
		return assemble;
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
	 * Returns the model id.
	 *
	 * @return The model id.
	 * @since 17
	 */
	public String getModelId() {
		return modelId;
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
