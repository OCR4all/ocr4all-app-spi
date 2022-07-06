/**
 * File:     ServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     23.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.ConfigurationServiceProvider;
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Premise;
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Target;
import de.uniwuerzburg.zpd.ocr4all.application.spi.model.Model;

/**
 * Defines service provider interfaces.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public interface ServiceProvider {
	/**
	 * Defines service provider statuses.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public enum Status {
		loaded, initialized, inactive, active
	}

	/**
	 * Returns the provider.
	 *
	 * @return The provider.
	 * @since 1.8
	 */
	public String getProvider();

	/**
	 * Returns true if the initialization of the service provider is deferred and
	 * will be performed in a new thread. Otherwise, initialization is performed as
	 * soon as the provider is loaded.
	 * 
	 * @return True if the initialization of the service provider is deferred and
	 *         will be performed in a new thread. Otherwise, initialization is
	 *         performed as soon as the provider is loaded.
	 * @since 1.8
	 */
	default boolean isLazyInitialization() {
		return false;
	}

	/**
	 * Initializes the service provider.
	 * 
	 * @param isEnabled     True if the service provider is enabled.
	 * @param configuration The configuration.
	 * @since 1.8
	 */
	public void initialize(boolean isEnabled, ConfigurationServiceProvider configuration);

	/**
	 * Returns true if the service provider is enabled.
	 * 
	 * @return True if the service provider is enabled.
	 * @since 1.8
	 */
	public boolean isEnabled();

	/**
	 * Returns the service provider status.
	 * 
	 * @return The service provider status.
	 * @since 1.8
	 */
	public Status getStatus();

	/**
	 * Enables the service provider.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider enable(String user);

	/**
	 * Disables the service provider.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider disable(String user);

	/**
	 * Starts the service provider. It can only be started in 'inactive' status.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider start(String user);

	/**
	 * Restarts the service provider. It can only be restarted in 'active' or
	 * 'inactive' status.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider restart(String user);

	/**
	 * Stops the service provider. It can only be stopped in 'active' status.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider stop(String user);

	/**
	 * Returns the journal.
	 * 
	 * @return the journal.
	 * @since 1.8
	 */
	public List<JournalEntryServiceProvider> getJournal();

	/**
	 * Returns the service provider name for given locale. The service provider is
	 * only registered if the name is not null and not empty for the ocr4all
	 * application language ocr4all.
	 * 
	 * @param locale The locale. This value is never null.
	 * @return The service provider name.
	 * @since 1.8
	 */
	public String getName(Locale locale);

	/**
	 * Returns the provider version.
	 * 
	 * @return The provider version.
	 * @since 1.8
	 */
	public float getVersion();

	/**
	 * Returns the service provider description for given locale.
	 * 
	 * @param locale The locale. This value is never null.
	 * @return The service provider description. Empty, if the description is not
	 *         set.
	 * @since 1.8
	 */
	default Optional<String> getDescription(Locale locale) {
		return Optional.empty();
	}

	/**
	 * Returns the service provider icon. Font Awesome 5.10.1 are supported.
	 * 
	 * @return The service provider icon. Empty, if the icon is not set.
	 * @since 1.8
	 */
	default Optional<String> getIcon() {
		return Optional.empty();
	}

	/**
	 * Returns the index to sort the service provider in the navigation tool bar.
	 * 
	 * @return The index to sort the service provider in the navigation tool bar.
	 * @since 1.8
	 */
	public int getIndex();

	/**
	 * Returns the premise.
	 * 
	 * @param target The target. Null if the premise is generic, this means, it
	 *               should not depend on a target.
	 * @return The premise.
	 * @since 1.8
	 */
	default Premise getPremise(Target target) {
		return null;
	}

	/**
	 * Returns the model.
	 * 
	 * @param target The target. Null if the model is generic, this means, it should
	 *               not depend on a target.
	 * @return The model.
	 * @since 1.8
	 */
	default Model getModel(Target target) {
		return null;
	}
}
