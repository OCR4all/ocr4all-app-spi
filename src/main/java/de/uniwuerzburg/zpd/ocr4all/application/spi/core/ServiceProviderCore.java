/**
 * File:     ServiceProviderCore.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     07.07.2022
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import java.security.ProviderException;
import java.util.ArrayList;
import java.util.List;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.ConfigurationServiceProvider;

/**
 * Defines core implementations for service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public abstract class ServiceProviderCore implements ServiceProvider {
	/**
	 * The configuration.
	 */
	protected ConfigurationServiceProvider configuration;

	/**
	 * The status.
	 */
	protected ServiceProvider.Status status = ServiceProvider.Status.loaded;

	/**
	 * True if the service provider is initialized as soon as the provider is
	 * loaded. Otherwise, its initialization is deferred and will be performed in a
	 * new thread. Initialization is also deferred if it is disabled.
	 */
	protected boolean isEagerInitialized = true;

	/**
	 * True if the service provider is enabled, this means, the service provider is
	 * active when the application is launched. Otherwise, it is inactive when the
	 * application is launched and its initialization is deferred and will be
	 * performed in a new thread.
	 */
	protected boolean isEnabled = false;

	/**
	 * The journal.
	 */
	protected List<JournalEntryServiceProvider> journal = new ArrayList<>();

	/**
	 * Default constructor for a core implementation of a service provider.
	 * 
	 * @since 1.8
	 */
	public ServiceProviderCore() {
		super();

		journal.add(new JournalEntryServiceProvider(true, JournalEntryServiceProvider.Level.info,
				"loaded service provider", null, status));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#configure(
	 * boolean, boolean,
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.env.ConfigurationServiceProvider)
	 */
	@Override
	public void configure(boolean isEagerInitialized, boolean isEnabled, ConfigurationServiceProvider configuration) {
		if (ServiceProvider.Status.loaded.equals(status)) {
			this.isEagerInitialized = isEagerInitialized;
			this.isEnabled = isEnabled;
			this.configuration = configuration;

			status = ServiceProvider.Status.configured;

			journal.add(new JournalEntryServiceProvider(true, JournalEntryServiceProvider.Level.info,
					"configured service provider", ServiceProvider.Status.loaded, status));
		} else
			journal.add(new JournalEntryServiceProvider(false, JournalEntryServiceProvider.Level.warn,
					"the service provider can only be configured in 'loaded' status", status, status));
	}

	/**
	 * Implementing subclasses that require special initialization can override this
	 * method to implement their logic. This method is called during the
	 * initialization process.
	 * 
	 * @throws ProviderException Throw specialized, provider-specific runtime
	 *                           errors.
	 * @since 1.8
	 */
	public void initializeCallback() throws ProviderException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#initialize()
	 */
	@Override
	public JournalEntryServiceProvider initialize() {
		JournalEntryServiceProvider journalEntry;

		if (ServiceProvider.Status.configured.equals(status)) {
			status = ServiceProvider.Status.initializing;

			journal.add(new JournalEntryServiceProvider(true, JournalEntryServiceProvider.Level.info,
					"initializing service provider", ServiceProvider.Status.configured, status));

			String errorMessage = null;
			try {
				initializeCallback();
			} catch (ProviderException e) {
				errorMessage = "(provider exception) " + e.getMessage();
			} catch (Exception e) {
				errorMessage = "(unexpected exception) " + e.getMessage();
			}

			if (errorMessage == null) {
				status = ServiceProvider.Status.active;

				journalEntry = new JournalEntryServiceProvider(true, JournalEntryServiceProvider.Level.info,
						"started service provider", ServiceProvider.Status.initializing, status);
				journal.add(journalEntry);
			} else {
				status = ServiceProvider.Status.inactive;

				journalEntry = new JournalEntryServiceProvider(false, JournalEntryServiceProvider.Level.warn,
						"stopped service provider, since it can not be initialized - " + errorMessage.trim(),
						ServiceProvider.Status.initializing, status);
				journal.add(journalEntry);
			}
		} else {
			journalEntry = new JournalEntryServiceProvider(false, JournalEntryServiceProvider.Level.warn,
					"the service provider can only be initialized in 'configured' status", status, status);
			journal.add(journalEntry);
		}

		return journalEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#
	 * isEagerInitialized()
	 */
	@Override
	public boolean isEagerInitialized() {
		return isEagerInitialized;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#getStatus()
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#eager(java.
	 * lang.String)
	 */
	@Override
	public JournalEntryServiceProvider eager(String user) {
		JournalEntryServiceProvider journalEntry;
		if (!isEagerInitialized) {
			isEagerInitialized = true;

			journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
					"eager initialized service provider", status, status);

			journal.add(journalEntry);
		} else
			journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
					"the service provider is already eager initialized", status, status);

		return journalEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#lazy(java.
	 * lang.String)
	 */
	@Override
	public JournalEntryServiceProvider lazy(String user) {
		JournalEntryServiceProvider journalEntry;
		if (isEagerInitialized) {
			isEagerInitialized = false;

			journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
					"lazy initialized service provider", status, status);

			journal.add(journalEntry);
		} else
			journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
					"the service provider is already lazy initialized", status, status);

		return journalEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#enable(java.
	 * lang.String)
	 */
	@Override
	public JournalEntryServiceProvider enable(String user) {
		JournalEntryServiceProvider journalEntry;
		if (!isEnabled) {
			isEnabled = true;

			journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
					"enabled service provider", status, status);

			journal.add(journalEntry);
		} else
			journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
					"the service provider is already enabled", status, status);

		return journalEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#disable(java
	 * .lang.String)
	 */
	@Override
	public JournalEntryServiceProvider disable(String user) {
		JournalEntryServiceProvider journalEntry;
		if (isEnabled) {
			isEnabled = false;

			journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
					"disabled service provider", status, status);

			journal.add(journalEntry);
		} else
			journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
					"the service provider is already disabled", status, status);

		return journalEntry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#start(java.
	 * lang.String)
	 */
	@Override
	public JournalEntryServiceProvider start(String user) {
		if (ServiceProvider.Status.configured.equals(status))
			return initialize();
		else {
			JournalEntryServiceProvider journalEntry;

			if (ServiceProvider.Status.inactive.equals(status)) {
				status = ServiceProvider.Status.active;

				journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
						"started service provider", ServiceProvider.Status.inactive, status);

				journal.add(journalEntry);
			} else
				journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
						"the service provider can only be started in 'inactive' status", status, status);

			return journalEntry;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#restart(java
	 * .lang.String)
	 */
	@Override
	public JournalEntryServiceProvider restart(String user) {
		if (ServiceProvider.Status.configured.equals(status))
			return initialize();
		else {
			JournalEntryServiceProvider journalEntry;
			
			if (ServiceProvider.Status.active.equals(status) || ServiceProvider.Status.inactive.equals(status)) {
				final ServiceProvider.Status sourceStatus = status;
				status = ServiceProvider.Status.active;

				journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
						"restarted service provider", sourceStatus, status);

				journal.add(journalEntry);
			} else
				journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
						"the service provider can only be restarted in 'active' or 'inactive' status", status, status);

			return journalEntry;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#stop(java.
	 * lang.String)
	 */
	@Override
	public JournalEntryServiceProvider stop(String user) {
		JournalEntryServiceProvider journalEntry;
		
		if (ServiceProvider.Status.active.equals(status)) {
			status = ServiceProvider.Status.inactive;

			journalEntry = new JournalEntryServiceProvider(user, true, JournalEntryServiceProvider.Level.info,
					"stopped service provider", ServiceProvider.Status.active, status);

			journal.add(journalEntry);
		} else
			journalEntry = new JournalEntryServiceProvider(user, false, JournalEntryServiceProvider.Level.warn,
					"the service provider can only be stopped in 'active' status", status, status);

		return journalEntry;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider#getJournal()
	 */
	@Override
	public List<JournalEntryServiceProvider> getJournal() {
		return new ArrayList<>(journal);
	}

}
