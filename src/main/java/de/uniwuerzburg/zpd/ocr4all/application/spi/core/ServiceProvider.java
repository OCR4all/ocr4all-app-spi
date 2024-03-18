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
import de.uniwuerzburg.zpd.ocr4all.application.spi.env.MicroserviceArchitecture;
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
		loaded, configured, initializing, inactive, active
	}

	/**
	 * Returns the provider appellation.
	 *
	 * @return The provider appellation.
	 * @since 1.8
	 */
	public String getProvider();

	/**
	 * Configures the service provider. This action is performed after the provider
	 * is loaded by the application. The provider should only store locally the
	 * information specified in the parameters. After that, the initialization is
	 * performed, during which the provider can perform some logic that can be
	 * executed in a new thread that does not block the initialization of the other
	 * providers.
	 * 
	 * @param isEagerInitialized       True if the service provider is initialized
	 *                                 as soon as the provider is loaded. Otherwise,
	 *                                 its initialization is deferred and will be
	 *                                 performed in a new thread.
	 * @param isEnabled                True if the service provider is enabled, this
	 *                                 means, the service provider is active when
	 *                                 the application is launched. Otherwise, it is
	 *                                 inactive when the application is launched.
	 * @param threadPool               The thread pool for the execution of the
	 *                                 service provider. If null, the service
	 *                                 provider will be executed using the scheduler
	 *                                 default pool.
	 * @param configuration            The configuration.
	 * @param microserviceArchitecture The microservice architecture.
	 * @since 1.8
	 */
	public void configure(boolean isEagerInitialized, boolean isEnabled, String threadPool,
			ConfigurationServiceProvider configuration, MicroserviceArchitecture microserviceArchitecture);

	/**
	 * Initializes the service provider. This action is performed after
	 * configuration and delays until the provider should be activated for the first
	 * time. This allows the provider to perform some required logic. If the
	 * provider is enabled, this means, it should be activated when the application
	 * is launched, then the initialization can be executed in a new thread that
	 * does not block the initialization of the other providers (lazy
	 * initialization).
	 * 
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider initialize();

	/**
	 * Returns true if the service provider is initialized as soon as the provider
	 * is loaded/configured. Otherwise, its initialization is deferred and will be
	 * performed in a new thread. Initialization is also deferred if it is disabled.
	 * 
	 * @return True if the service provider is initialized as soon as the provider
	 *         is loaded/configured. Otherwise, its initialization is deferred and
	 *         will be performed in a new thread.
	 * @since 1.8
	 */
	public boolean isEagerInitialized();

	/**
	 * Returns true if the service provider is enabled, this means, the service
	 * provider is active when the application is launched. Otherwise, it is
	 * inactive when the application is launched.
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
	 * Eager initializes the service provider if it is enabled, this means, the
	 * service provider initialization is performed as soon as the provider is
	 * loaded.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider eager(String user);

	/**
	 * Lazy initializes the service provider if it is enabled, this means, the
	 * service providers initialization is deferred and will be performed in a new
	 * thread.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider lazy(String user);

	/**
	 * Enables the service provider, this means, the service provider will be active
	 * when the application is launched.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider enable(String user);

	/**
	 * Disables the service provider, this means, the service provider will be
	 * inactive when the application is launched.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider disable(String user);

	/**
	 * Starts the service provider. The provider will be initialized if his status
	 * is 'configured'.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider start(String user);

	/**
	 * Restarts the service provider. The provider will be initialized if his status
	 * is 'configured'.
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
	 * Returns true if the thread pool for the execution of the service provider is
	 * set.
	 * 
	 * @return True if the thread pool for the execution of the service provider is
	 *         set.
	 * @since 1.8
	 */
	public boolean isThreadPoolSet();

	/**
	 * Returns the thread pool for the execution of the service provider.
	 * 
	 * @return The thread pool. Null if not set.
	 * @since 1.8
	 */
	public String getThreadPool();

	/**
	 * Set the thread pool for the execution of the service provider.
	 * 
	 * @param user The user.
	 * @param pool The thread pool. If null or blank, the pool is reset and the
	 *             service provider will be executed using the scheduler default
	 *             pool.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider setThreadPool(String user, String pool);

	/**
	 * Reset the thread pool. The service provider will be executed using the
	 * scheduler default pool.
	 * 
	 * @param user The user.
	 * @return The journal entry for the action.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider resetThreadPool(String user);

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
	 * Returns the categories.
	 *
	 * @return The categories.
	 * @since 1.8
	 */
	public List<String> getCategories();

	/**
	 * Returns the steps.
	 *
	 * @return The steps.
	 * @since 1.8
	 */
	public List<String> getSteps();

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
	 * Returns an advice.
	 * 
	 * @return An advice.
	 * @since 1.8
	 */
	default String getAdvice() {
		return null;
	}

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
