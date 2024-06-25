/**
 * File:     ProcessServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     19.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.ProcessFramework;

/**
 * Defines service provider interfaces for processes.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface ProcessServiceProvider extends ProcessorServiceProvider<ProcessFramework> {

}
