/**
 * File:     MicroserviceArchitecture.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.03.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import de.uniwuerzburg.zpd.ocr4all.application.communication.message.spi.EventSPI;

/**
 * MicroserviceArchitecture is an immutable class that defines microservice
 * architectures.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class MicroserviceArchitecture {
	/**
	 * The hosts. The key is the id.
	 */
	private final Hashtable<String, Host> hosts = new Hashtable<>();

	/**
	 * The event controller.
	 */
	private final EventController eventController;

	/**
	 * Creates a microservice architecture.
	 * 
	 * @param eventController The event controller.
	 * @param hosts           The hosts. The host ids has to be unique and non null.
	 * @since 17
	 */
	public MicroserviceArchitecture(EventController eventController, Collection<Host> hosts) {
		super();

		this.eventController = eventController;

		if (hosts != null)
			for (Host host : hosts)
				if (host != null && host.getId() != null)
					this.hosts.put(host.getId(), host);
	}

	/**
	 * Returns the event controller.
	 *
	 * @return The event controller.
	 * @since 17
	 */
	public EventController getEventController() {
		return eventController;
	}

	/**
	 * Returns the hosts sorted by id.
	 *
	 * @return The hosts sorted by id.
	 * @since 17
	 */
	public List<Host> getHosts() {
		List<Host> list = new ArrayList<>(hosts.values());

		Collections.sort(list, new Comparator<Host>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(Host h1, Host h2) {
				return h1.getId().compareToIgnoreCase(h2.getId());
			}
		});

		return list;
	}

	/**
	 * Returns the host ids.
	 *
	 * @return The host ids.
	 * @since 17
	 */
	public List<String> getHostIds() {
		List<String> ids = new ArrayList<>(hosts.keySet());

		Collections.sort(ids, String.CASE_INSENSITIVE_ORDER);

		return ids;
	}

	/**
	 * Returns the host.
	 *
	 * @param id The id.
	 * @return The host. Null if unknown.
	 * @since 17
	 */
	public Host getHost(String id) {
		return id == null ? null : hosts.get(id);
	}

	/**
	 * Defines event controllers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public interface EventController {
		/**
		 * Register an event handler to spi events.
		 * 
		 * @param key     The event key to register.
		 * @param handler The event handler.
		 * @return The id of the registered event handler.
		 * @since 17
		 */
		public int register(String key, EventHandler handler);

		/**
		 * Unregister an event handler.
		 * 
		 * @param id The id of the event handler to unregister.
		 * @since 17
		 */
		public void unregister(int id);
	}

	/**
	 * Defines functional interfaces to handle spi events.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	@FunctionalInterface
	public interface EventHandler {
		/**
		 * Handles the spi event.
		 * 
		 * @param event The spi event to handle.
		 * @since 17
		 */
		public void handle(EventSPI event);
	}

	/**
	 * Host ist an immutable class that defines hosts.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public static class Host {
		/**
		 * The id.
		 */
		private final String id;

		/**
		 * The url.
		 */
		private final String url;

		/**
		 * Creates a host.
		 * 
		 * @param id  The id.
		 * @param url The url.
		 * @since 17
		 */
		public Host(String id, String url) {
			super();

			this.id = id;
			this.url = url;
		}

		/**
		 * Returns the id.
		 *
		 * @return The id.
		 * @since 17
		 */
		public String getId() {
			return id;
		}

		/**
		 * Returns the url.
		 *
		 * @return The url.
		 * @since 17
		 */
		public String getUrl() {
			return url;
		}

	}

}
