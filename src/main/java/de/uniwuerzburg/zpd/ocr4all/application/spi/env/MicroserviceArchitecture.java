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
	 * Creates a microservice architecture.
	 * 
	 * @param hosts The hosts.
	 * @since 17
	 */
	public MicroserviceArchitecture(Collection<Host> hosts) {
		super();

		if (hosts != null)
			for (Host host : hosts)
				if (host != null && host.getId() != null)
					this.hosts.put(host.getId(), host);
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
