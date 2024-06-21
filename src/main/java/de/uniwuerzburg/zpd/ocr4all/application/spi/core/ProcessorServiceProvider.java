/**
 * File:     ActionServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

/**
 * Defines service provider interfaces with processors.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface ProcessorServiceProvider<P extends ProcessorCore> extends ServiceProvider {
	/**
	 * Returns a new processor for the service provider.
	 * 
	 * @return A new processor for the service provider.
	 * @since 17
	 */
	public P newProcessor();
}
