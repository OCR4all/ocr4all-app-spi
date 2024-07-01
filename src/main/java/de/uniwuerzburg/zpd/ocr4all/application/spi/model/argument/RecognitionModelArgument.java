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
 * @since 1.8
 */
public final class RecognitionModelArgument extends Argument {
	/**
	 * The collections.
	 */
	private final Optional<List<Collection>> collections;

	/**
	 * Creates recognition model values for a model argument.
	 * 
	 * @param argument    The argument.
	 * @param collections The collections.
	 * @throws IllegalArgumentException Throws if the argument is null.
	 * @since 1.8
	 */
	public RecognitionModelArgument(String argument, List<Collection> collections) throws IllegalArgumentException {
		super(argument);

		this.collections = collections == null || collections.isEmpty() ? Optional.empty() : Optional.of(collections);
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
				collections.isPresent() ? Optional.of(collections.get().toString()) : Optional.empty());
	}

	/**
	 * Returns the collections.
	 *
	 * @return The collections.
	 * @since 17
	 */
	public Optional<List<Collection>> getCollections() {
		return collections;
	}

	/**
	 * Collection is an immutable class that defines collections for datasets.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Collection {
		/**
		 * The id.
		 */
		private final String id;

		/**
		 * The images.
		 */
		private final List<String> images;

		/**
		 * Creates a dataset.
		 * 
		 * @param id     The id.
		 * @param images The images.
		 * @since 17
		 */
		public Collection(String id, List<String> images) {
			super();

			if (id == null || id.isBlank())
				throw new IllegalArgumentException("the collection argument id can not be empty.");
			this.id = id;

			this.images = images;
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
		 * Returns the images.
		 *
		 * @return The images.
		 * @since 17
		 */
		public List<String> getImages() {
			return images;
		}

	}

}
