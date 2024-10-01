/**
 * File:     ActionServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     17.09.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi;

import de.uniwuerzburg.zpd.ocr4all.application.spi.core.WorkerServiceProvider;
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Database;

/**
 * Defines service provider interfaces for actions.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface ActionServiceProvider extends WorkerServiceProvider<Database> {

}
