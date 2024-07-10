/**
 * File:     ProcessorServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Framework;
import de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.ModelArgument;

/**
 * Defines service provider interfaces with processors.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface ProcessorServiceProvider<C extends ProcessorCore.Callback, F extends Framework>
		extends ServiceProvider {
	/**
	 * Returns a new processor for the service provider.
	 * 
	 * @return A new processor for the service provider.
	 * @since 17
	 */
	public Processor<C, F> newProcessor();

	/**
	 * Defines processors for service providers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public interface Processor<C extends ProcessorCore.Callback, F extends Framework> extends ProcessorCore {
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
		public State execute(C callback, F framework, ModelArgument modelArgument);
	}

}
