/**
 * File:     LauncherServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     30.03.2022
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi;

import de.uniwuerzburg.zpd.ocr4all.application.spi.core.ProcessorCore;
import de.uniwuerzburg.zpd.ocr4all.application.spi.core.ProcessorServiceProvider;
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.ProcessFramework;

/**
 * Defines service provider interfaces for launchers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public interface LauncherServiceProvider
		extends ProcessorServiceProvider<ProcessorCore.LockSnapshotCallback, ProcessFramework> {

}
