/**
 * File:     ActionServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.FrameworkCore;
import de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.ModelArgument;

/**
 * Defines service provider interfaces for actions.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface ActionServiceProvider extends ProcessorServiceProvider<ActionServiceProvider.Processor> {
	/**
	 * Defines processors for service providers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public interface Processor extends ProcessorCore {
		/**
		 * Executes the process.
		 * 
		 * @param callback      The callback method for processor updates. If null, no
		 *                      callback is performed.
		 * @param framework     The framework for the processor.
		 * @param modelArgument The models with their arguments.
		 * @return The state of the execution of the process.
		 * @since 17
		 */
		public State execute(Callback callback, FrameworkCore framework, ModelArgument modelArgument);
	}

}
