/**
 * File:     ConfigurationServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     12.04.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * ConfigurationServiceProvider is an immutable class that defines
 * configurations for the service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class ConfigurationServiceProvider {
	/**
	 * The properties.
	 */
	private final Hashtable<String, Hashtable<String, Property>> properties = new Hashtable<>();

	/**
	 * The system commands.
	 */
	private final Hashtable<SystemCommand.Type, SystemCommand> systemCommands = new Hashtable<>();

	/**
	 * Creates a configuration for a service provider.
	 * 
	 * @param properties     The properties.
	 * @param systemCommands The system commands.
	 * @since 1.8
	 */
	public ConfigurationServiceProvider(Collection<Property> properties, SystemCommand... systemCommands) {
		this(properties, Arrays.asList(systemCommands));
	}

	/**
	 * Creates a configuration for a service provider.
	 * 
	 * @param properties     The properties.
	 * @param systemCommands The system commands.
	 * @since 1.8
	 */
	public ConfigurationServiceProvider(Collection<Property> properties, Collection<SystemCommand> systemCommands) {
		super();

		if (properties != null)
			for (Property property : properties)
				if (property != null && property.getCollection() != null && property.getKey() != null) {
					Hashtable<String, Property> collection = this.properties.get(property.getCollection());
					if (collection == null) {
						collection = new Hashtable<>();
						this.properties.put(property.getCollection(), collection);
					}

					collection.put(property.getKey(), property);
				}

		if (systemCommands != null)
			for (SystemCommand systemCommand : systemCommands)
				if (systemCommand != null && systemCommand.getType() != null && systemCommand.getCommand() != null)
					this.systemCommands.put(systemCommand.getType(), systemCommand);
	}

	/**
	 * Returns the available property collections.
	 * 
	 * @return The available property collections.
	 * @since 1.8
	 */
	public Set<String> getPropertyCollections() {
		return new HashSet<>(properties.keySet());
	}

	/**
	 * Returns the property keys of a collection.
	 * 
	 * @param collection The collection.
	 * @return The property keys of a collection. Null if the collection is unknown.
	 * @since 1.8
	 */
	public Set<String> getPropertyKeys(String collection) {
		Hashtable<String, Property> set = properties.get(collection);

		return set == null ? null : new HashSet<>(set.keySet());
	}

	/**
	 * Returns the property with the given key of desired collection.
	 *
	 * @param collection The collection.
	 * @param key        The key.
	 * @return The property. Null if unknown.
	 * @since 1.8
	 */
	public Property getProperty(String collection, String key) {
		Hashtable<String, Property> set = properties.get(collection);

		return set == null ? null : set.get(key);
	}

	/**
	 * Returns true if the system command is registered and available.
	 *
	 * @return True if the system command is registered and available.
	 * @since 1.8
	 */
	public boolean isSystemCommandAvailable(SystemCommand.Type type) {
		SystemCommand systemCommand = getSystemCommand(type);

		return systemCommand != null && systemCommand.isAvailable();
	}

	/**
	 * Returns the system command.
	 *
	 * @return The system command. Null if not registered.
	 * @since 1.8
	 */
	public SystemCommand getSystemCommand(SystemCommand.Type type) {
		return type == null ? null : systemCommands.get(type);
	}

	/**
	 * Returns the value of the key of the service provider collection.
	 * 
	 * @param collection The service provider collection with a key and a default
	 *                   value.
	 * @return The value of the key of the service provider collection.
	 * @since 1.8
	 */
	public String getValue(CollectionKey collection) {
		return getValue(this, collection);
	}

	/**
	 * Returns the value of the key of the service provider collection.
	 * 
	 * @param configuration The service provider configuration.
	 * @param collection    The service provider collection with a key and a default
	 *                      value.
	 * @return The value of the key of the service provider collection.
	 * @since 1.8
	 */
	public static String getValue(ConfigurationServiceProvider configuration, CollectionKey collection) {
		if (configuration == null || collection == null || collection.getName() == null)
			return null;

		ConfigurationServiceProvider.Property property = configuration.getProperty(collection.getName(),
				collection.getKey());

		return property == null || property.getValue() == null
				|| (!collection.isBlank() && property.getValue().isBlank()) ? collection.getDefaultValue()
						: (collection.isTrim() ? property.getValue().trim() : property.getValue());
	}

	/**
	 * Defines collections for service provider with keys and default values.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public interface CollectionKey {
		/**
		 * Returns the collection name.
		 * 
		 * @return The collection name.
		 * @since 1.8
		 */
		public String getName();

		/**
		 * Returns the collection key.
		 * 
		 * @return The collection key.
		 * @since 1.8
		 */
		public String getKey();

		/**
		 * Returns the default value, if the key is not available in the collection.
		 * 
		 * @return The default value, if the key is not available in the collection.
		 * @since 1.8
		 */
		public String getDefaultValue();

		/**
		 * True if black collection values are allowed. Otherwise returns the default
		 * value. Default value is false.
		 * 
		 * @return True if black collection values are allowed.
		 * @since 1.8
		 */
		public default boolean isBlank() {
			return false;
		}

		/**
		 * True if the collection value should be trimmed. Default value is true.
		 * 
		 * @return True if the collection value should be trimmed.
		 * @since 1.8
		 */
		public default boolean isTrim() {
			return true;
		}
	}

	/**
	 * Property is an immutable class that defines properties for service providers.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Property {
		/**
		 * The collection.
		 */
		private final String collection;

		/**
		 * The key.
		 */
		private final String key;

		/**
		 * The value.
		 */
		private final String value;

		/**
		 * Creates a property for service providers.
		 * 
		 * @param collection The collection.
		 * @param key        The key.
		 * @param value      The value.
		 * @since 1.8
		 */
		public Property(String collection, String key, String value) {
			super();

			this.collection = collection;
			this.key = key;
			this.value = value;
		}

		/**
		 * Returns the collection.
		 *
		 * @return The collection.
		 * @since 1.8
		 */
		public String getCollection() {
			return collection;
		}

		/**
		 * Returns the key.
		 *
		 * @return The key.
		 * @since 1.8
		 */
		public String getKey() {
			return key;
		}

		/**
		 * Returns the value.
		 *
		 * @return The value.
		 * @since 1.8
		 */
		public String getValue() {
			return value;
		}

	}
}
