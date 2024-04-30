/**
 * File:     ProcessServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     19.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Framework;
import de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.ModelArgument;

/**
 * Defines service provider interfaces for processes.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public interface ProcessServiceProvider extends ServiceProvider {
	/**
	 * Returns a new processor for the service provider.
	 * 
	 * @return A new processor for the service provider.
	 * @since 1.8
	 */
	public Processor newProcessor();

	/**
	 * Defines processors for service providers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public interface Processor {
		/**
		 * Defines return states for the execution of the process.
		 *
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 1.8
		 */
		public enum State {
			/**
			 * the process was carried out without problems
			 */
			completed,
			/**
			 * the process was aborted during execution
			 */			
			canceled,
			/**
			 * the process was interrupted due to failures
			 */
			interrupted
		}

		/**
		 * Executes the process.
		 * 
		 * @param callback      The callback method for processor updates. If null, no
		 *                      callback is performed.
		 * @param framework     The framework for the processor.
		 * @param modelArgument The models with their arguments.
		 * @return The state of the execution of the process.
		 * @since 1.8
		 */
		public State execute(Callback callback, Framework framework, ModelArgument modelArgument);

		/**
		 * Cancels the process.
		 * 
		 * @since 1.8
		 */
		public void cancel();

		/**
		 * Defines callback.
		 *
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 1.8
		 */
		public interface Callback {
			/**
			 * Callback method for updated progress.
			 * 
			 * @param progress The current progress.
			 * @since 1.8
			 */
			public void updatedProgress(float progress);

			/**
			 * Callback method for updated standard output.
			 * 
			 * @param message The current message.
			 * @since 1.8
			 */
			public void updatedStandardOutput(String message);

			/**
			 * Callback method for updated standard error.
			 * 
			 * @param message The current message.
			 * @since 1.8
			 */
			public void updatedStandardError(String message);

			/**
			 * Callback method to lock the snapshot.
			 * 
			 * @param comment The snapshot lock comment.
			 * @since 1.8
			 */
			public void lockSnapshot(String comment);
		}
	}
}
