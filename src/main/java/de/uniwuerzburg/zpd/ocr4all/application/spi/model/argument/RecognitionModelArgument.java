/**
 * File:     RecognitionModelArgument.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     18.12.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument;

import java.util.List;
import java.util.Optional;

/**
 * Defines recognition model values for model arguments.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public final class RecognitionModelArgument extends Argument {
	/**
	 * The assembles.
	 */
	private final Optional<List<Assemble>> assembles;

	/**
	 * Creates recognition model values for a model argument.
	 * 
	 * @param argument  The argument.
	 * @param assembles The assembles.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 17
	 */
	public RecognitionModelArgument(String argument, List<Assemble> assembles) throws IllegalArgumentException {
		super(argument);

		this.assembles = assembles == null || assembles.isEmpty() ? Optional.empty() : Optional.of(assembles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.
	 * Argument#getSummary()
	 */
	@Override
	protected Summary getSummary() {
		return new Summary("recognition model", getArgument(),
				assembles.isPresent() ? Optional.of(assembles.get().toString()) : Optional.empty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniwuerzburg.zpd.ocr4all.application.spi.model.argument.Argument#
	 * isSingleValue()
	 */
	@Override
	public boolean isSingleValue() {
		return false;
	}

	/**
	 * Returns the assembles.
	 *
	 * @return The assembles.
	 * @since 17
	 */
	public Optional<List<Assemble>> getAssembles() {
		return assembles;
	}

	/**
	 * Model is an immutable class that defines assembles.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public static class Assemble {
		/**
		 * The id.
		 */
		private final String id;

		/**
		 * The models.
		 */
		private final List<String> models;

		/**
		 * Creates an assemble.
		 * 
		 * @param id     The id.
		 * @param models The models.
		 * @since 17
		 */
		public Assemble(String id, List<String> models) throws IllegalArgumentException {
			super();

			if (id == null || id.isBlank())
				throw new IllegalArgumentException("the assemble argument id can not be empty.");
			this.id = id;

			this.models = models;
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
		 * Returns the models.
		 *
		 * @return The models.
		 * @since 17
		 */
		public List<String> getModels() {
			return models;
		}
	}

}
