/**
 * File:     ModelArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.Argument.Summary;

/**
 * Defines models with their arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class ModelArgument {
	/**
	 * The arguments.
	 */
	private final List<Argument> arguments = new ArrayList<>();

	/**
	 * The arguments indexed by names.
	 */
	private final Hashtable<String, Argument> argumentNames = new Hashtable<>();

	/**
	 * Creates a model with arguments.
	 * 
	 * @param arguments The arguments.
	 * @since 1.8
	 */
	public ModelArgument(List<Argument> arguments) {
		super();

		if (arguments != null)
			for (Argument argument : arguments)
				if (argument != null) {
					this.arguments.add(argument);
					argumentNames.put(argument.getArgument(), argument);
				}
	}

	/**
	 * Returns the argument with given name.
	 * 
	 * @param name The argument name.
	 * @return The argument. Empty if the argument is unknown.
	 * @since 1.8
	 */
	public Optional<Argument> getArgument(String name) {
		return name == null || !argumentNames.containsKey(name) ? Optional.empty()
				: Optional.of(argumentNames.get(name));
	}

	/**
	 * Returns the argument with given name.
	 * 
	 * @param <T>   The argument class.
	 * @param clazz The argument class.
	 * @param name  The argument name.
	 * @return The argument. Null if the argument is unknown.
	 * @throws ClassCastException Throws if the argument is not of given class.
	 * @since 1.8
	 */
	@SuppressWarnings("unchecked")
	public <T extends Argument> T getArgument(Class<T> clazz, String name) throws ClassCastException {
		return name == null || !argumentNames.containsKey(name) ? null : (T) argumentNames.get(name);
	}

	/**
	 * Returns the arguments.
	 *
	 * @return The arguments.
	 * @since 1.8
	 */
	public List<Argument> getArguments() {
		return new ArrayList<Argument>(arguments);
	}

	/**
	 * Returns the argument names.
	 *
	 * @return The argument names.
	 * @since 1.8
	 */
	public Set<String> getArgumentNames() {
		return new HashSet<>(argumentNames.keySet());
	}

	/**
	 * Returns the argument names that are missing from the given names.
	 *
	 * @param names The argument names we are looking for.
	 * @return The missing argument names. The returned order corresponds to the
	 *         given order.
	 * @since 1.8
	 */
	public List<String> getMissedArguments(String... names) {
		return getMissedArguments(Arrays.asList(names));
	}

	/**
	 * Returns the argument names that are missing from the given names.
	 *
	 * @param names The argument names we are looking for.
	 * @return The missing argument names. The returned order corresponds to the
	 *         order of the given collection.
	 * @since 1.8
	 */
	public List<String> getMissedArguments(Collection<String> names) {
		List<String> missed = new ArrayList<>();

		if (names != null)
			for (String name : names)
				if (name != null && !argumentNames.containsKey(name))
					missed.add(name);

		return missed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		for (Argument argument : arguments) {
			Summary summary = argument.getSummary();

			if (buffer.length() > 0)
				buffer.append(", ");

			buffer.append(summary.getArgument() + "[" + summary.getType() + ": "
					+ (summary.getValue().isPresent() ? summary.getValue().get() : "<not set>") + "]");
		}

		return buffer.toString();
	}

}
