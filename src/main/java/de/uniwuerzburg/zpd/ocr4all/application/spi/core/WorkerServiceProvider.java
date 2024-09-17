/**
 * File:     WorkerServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     17.09.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Database;
import de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.ModelArgument;

/**
 * Defines service provider interfaces with workers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface WorkerServiceProvider<D extends Database> extends ServiceProvider {
	/**
	 * Returns a new worker for the service provider.
	 * 
	 * @return A new worker for the service provider.
	 * @since 17
	 */
	public Worker<D> newAgent();

	/**
	 * Defines workers for service providers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public interface Worker<D extends Database> {
		/**
		 * Executes the worker.
		 * 
		 * @param database      The database for the worker.
		 * @param modelArgument The models with their arguments.
		 * @return The worker output.
		 * @since 17
		 */
		public String execute(D database, ModelArgument modelArgument);
	}

}
