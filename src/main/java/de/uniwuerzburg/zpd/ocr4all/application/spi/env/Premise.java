/**
 * File:     Premise.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     29.01.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.util.Locale;

/**
 * Defines premises interfaces for service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class Premise {
	/**
	 * Defines states.
	 * <ul>
	 * <li>release: releases the service provider without message</li>
	 * <li>info: releases the service provider with info message</li>
	 * <li>warn: releases the service provider with warn message</li>
	 * <li>block: blocks the service provider</li>
	 * </ul>
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public enum State {
		release, info, warn, block
	}

	/**
	 * The state.
	 */
	private final State state;

	/**
	 * The message.
	 */
	private final Internationalization message;

	/**
	 * Default constructor for a premise with state release.
	 * 
	 * @since 1.8
	 */
	public Premise() {
		this(State.release, null);
	}

	/**
	 * Creates a premise.
	 * 
	 * @param state   The state. If null, use state release. The states info, warn
	 *                and block expect a message.
	 * @param message The message.
	 * @since 1.8
	 */
	public Premise(State state, Internationalization message) throws IllegalArgumentException {
		super();

		this.state = state == null ? State.release : state;
		this.message = State.release.equals(this.state) ? null : message;
	}

	/**
	 * Returns the state.
	 * 
	 * @return The state.
	 * @since 1.8
	 */
	public State getState() {
		return state;
	}

	/**
	 * Returns the message.
	 *
	 * @param locale The locale.
	 * @return The message.
	 * @since 1.8
	 */
	public String getMessage(Locale locale) {
		return message == null ? null : message.getString(locale);
	}

}
