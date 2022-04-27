/**
 * File:     Argument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.Optional;

/**
 * Defines arguments for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public abstract class Argument {
	/**
	 * The argument.
	 */
	private final String argument;

	/**
	 * Creates an argument for a model.
	 * 
	 * @param argument The argument.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	Argument(String argument) throws IllegalArgumentException {
		super();

		if (argument == null)
			throw new IllegalArgumentException("the argument cannot be null.");

		this.argument = argument;
	}

	/**
	 * Returns the argument summary.
	 * 
	 * @return The summary.
	 * @since 1.8
	 */
	protected abstract Summary getSummary();

	/**
	 * Returns the argument.
	 *
	 * @return The argument.
	 * @since 1.8
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 * Returns true if it is a single value argument.
	 * 
	 * @return True if it is a single value argument. Otherwise it is a multiple
	 *         values argument.
	 * @since 1.8
	 */
	public abstract boolean isSingleValue();

	/**
	 * Returns true if it is a multiple values argument.
	 * 
	 * @return True if it is a multiple values argument. Otherwise it is a single
	 *         values argument.
	 * @since 1.8
	 */
	public boolean isMultipleValues() {
		return !isSingleValue();
	}

	/**
	 * Summary is an immutable class that describes arguments.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	protected class Summary {
		/**
		 * The type.
		 */
		private final String type;

		/**
		 * The argument.
		 */
		private final String argument;

		/**
		 * The value. Empty if not defined.
		 */
		private final Optional<String> value;

		/**
		 * Creates a summary of the argument.
		 * 
		 * @param type     The type.
		 * @param argument The argument.
		 * @param value    The value. Empty if not defined.
		 * @since 1.8
		 */
		public Summary(String type, String argument, Optional<String> value) {
			super();
			this.type = type;
			this.argument = argument;
			this.value = value;
		}

		/**
		 * Returns the type.
		 *
		 * @return The type.
		 * @since 1.8
		 */
		public String getType() {
			return type;
		}

		/**
		 * Returns the argument.
		 *
		 * @return The argument.
		 * @since 1.8
		 */
		public String getArgument() {
			return argument;
		}

		/**
		 * Returns the value.
		 *
		 * @return The value. Empty if not defined.
		 * @since 1.8
		 */
		public Optional<String> getValue() {
			return value;
		}

	}
}
