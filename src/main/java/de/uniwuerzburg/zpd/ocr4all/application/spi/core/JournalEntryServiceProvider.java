/**
 * File:     JournalEntryServiceProvider.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     04.07.2022
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

import java.util.Date;

import de.uniwuerzburg.zpd.ocr4all.application.spi.core.ServiceProvider.Status;

/**
 * JournalServiceProvider is an immutable class that defines service provider
 * journal entries.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class JournalEntryServiceProvider {
	/**
	 * Defines messages levels.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public enum Level {
		debug, info, warn, error
	}

	/**
	 * The date.
	 */
	private final Date date = new Date();

	/**
	 * The user.
	 */
	private final String user;

	/**
	 * The level.
	 */
	private final Level level;

	/**
	 * The message.
	 */
	private final String message;

	/**
	 * The source status.
	 */
	private final ServiceProvider.Status sourceStatus;

	/**
	 * The target status.
	 */
	private final ServiceProvider.Status targetStatus;

	/**
	 * Creates a service provider journal entry.
	 * 
	 * @param user         The user.
	 * @param level        The level.
	 * @param message      The message.
	 * @param sourceStatus The source status.
	 * @param targetStatus The target status.
	 * @since 1.8
	 */
	public JournalEntryServiceProvider(String user, Level level, String message, Status sourceStatus,
			Status targetStatus) {
		super();

		this.user = user;
		this.level = level;
		this.message = message;
		this.sourceStatus = sourceStatus;
		this.targetStatus = targetStatus;
	}

	/**
	 * Returns the date.
	 *
	 * @return The date.
	 * @since 1.8
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Returns the user.
	 *
	 * @return The user.
	 * @since 1.8
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Returns the level.
	 *
	 * @return The level.
	 * @since 1.8
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Returns the message.
	 *
	 * @return The message.
	 * @since 1.8
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the source status.
	 *
	 * @return The source status.
	 * @since 1.8
	 */
	public ServiceProvider.Status getSourceStatus() {
		return sourceStatus;
	}

	/**
	 * Returns the target status.
	 *
	 * @return The target status.
	 * @since 1.8
	 */
	public ServiceProvider.Status getTargetStatus() {
		return targetStatus;
	}

}
