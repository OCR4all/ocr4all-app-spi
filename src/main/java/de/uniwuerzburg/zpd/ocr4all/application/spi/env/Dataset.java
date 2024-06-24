/**
 * File:     Dataset.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     24.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.util.ArrayList;
import java.util.List;

/**
 * Dataset is an immutable class that defines datasets.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class Dataset {
	/**
	 * The collections.
	 */
	private final List<Collection> collections = new ArrayList<>();

	/**
	 * Creates a dataset.
	 * 
	 * @since 17
	 */
	public Dataset(java.util.Collection<Collection> collections) {
		super();

		for (Collection collection : collections)
			if (collection.isConsistent())
				this.collections.add(collection);
	}

	/**
	 * Returns the collections.
	 *
	 * @return The collections.
	 * @since 17
	 */
	public List<Collection> getCollections() {
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
		 * The sets.
		 */
		private final List<Set> sets = new ArrayList<>();

		/**
		 * Creates a collection.
		 * 
		 * @param id   The id.
		 * @param sets The sets.
		 * @since 17
		 */
		public Collection(String id, java.util.Collection<Set> sets) {
			super();

			this.id = id;
			for (Set set : sets)
				if (set.isConsistent())
					this.sets.add(set);
		}

		/**
		 * Returns true if the set is consistent.
		 * 
		 * @return True if the set is consistent.
		 * @since 17
		 */
		private boolean isConsistent() {
			return id != null && !sets.isEmpty();
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
		 * Returns the sets.
		 *
		 * @return The sets.
		 * @since 17
		 */
		public List<Set> getSets() {
			return sets;
		}

		/**
		 * Set is an immutable class that defines sets for collections.
		 *
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 1.8
		 */
		public static class Set {
			/**
			 * The id.
			 */
			private final String id;

			/**
			 * The xml extension.
			 */
			private final String xml;

			/**
			 * The image extension.
			 */
			private final String image;

			/**
			 * Creates a set.
			 * 
			 * @param id    The id.
			 * @param xml   The xml extension.
			 * @param image The image extension.
			 * @since 17
			 */
			public Set(String id, String xml, String image) {
				super();

				this.id = id == null || id.isBlank() ? null : id.trim();
				this.xml = xml == null || xml.isBlank() ? null : xml.trim();
				this.image = image == null || image.isBlank() ? null : image.trim();
			}

			/**
			 * Returns true if the set is consistent.
			 * 
			 * @return True if the set is consistent.
			 * @since 17
			 */
			private boolean isConsistent() {
				return id != null && xml != null && image != null;
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
			 * Returns the xml extension.
			 *
			 * @return The xml extension.
			 * @since 17
			 */
			public String getXml() {
				return xml;
			}

			/**
			 * Returns the image extension.
			 *
			 * @return The image extension.
			 * @since 17
			 */
			public String getImage() {
				return image;
			}
		}
	}
}
