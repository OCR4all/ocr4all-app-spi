/**
 * File:     MsaProcessorServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.calamari.spi.msa
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     01.03.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import de.uniwuerzburg.zpd.ocr4all.application.communication.message.spi.EventSPI;
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Framework;
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.MicroserviceArchitecture;
import de.uniwuerzburg.zpd.ocr4all.application.spi.util.SPIUtils;

/**
 * Defines processors for microservice architecture (MSA) service providers.
 * When implementing the required method
 * {@link ProcessServiceProvider.Processor#execute}, this class should be
 * initialized by calling the method
 * {@link CoreProcessorServiceProvider#initialize} at the beginning and
 * completed by calling the method
 * {@link CoreProcessorServiceProvider#complete}.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public abstract class MsaProcessorServiceProvider<C extends ProcessorCore.Callback, F extends Framework>
		extends CoreProcessorServiceProvider<C, F> {
	/**
	 * The job key.
	 */
	protected final String key = SPIUtils.getUUID();

	/**
	 * The event controller.
	 */
	private final MicroserviceArchitecture.EventController eventController;

	/**
	 * The event handler id. 0 if not registered.
	 */
	private int idEventHandler = 0;

	/**
	 * Creates a processor for microservice architecture (MSA) service provider for
	 * Calamari.
	 * 
	 * @param eventController The event controller.
	 * @since 17
	 */
	public MsaProcessorServiceProvider(MicroserviceArchitecture.EventController eventController) {
		super();

		this.eventController = eventController;
	}

	/**
	 * Register the event handler.
	 * 
	 * @since 17
	 */
	protected void registerEventHandler() {
		idEventHandler = eventController.register(key, new MicroserviceArchitecture.EventHandler() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * de.uniwuerzburg.zpd.ocr4all.application.spi.env.MicroserviceArchitecture.
			 * EventHandler#handle(de.uniwuerzburg.zpd.ocr4all.application.communication.
			 * message.spi.EventSPI)
			 */
			@Override
			public void handle(EventSPI event) {
				MsaProcessorServiceProvider.this.handle(event);
			}
		});

	}

	/**
	 * Unregister the event handler.
	 * 
	 * @since 17
	 */
	protected void unregisterEventHandler() {
		if (idEventHandler > 0)
			eventController.unregister(idEventHandler);
	}

	/**
	 * Handles the spi event.
	 * 
	 * @param event The spi event to handle.
	 * @since 17
	 */
	protected abstract void handle(EventSPI event);

}
