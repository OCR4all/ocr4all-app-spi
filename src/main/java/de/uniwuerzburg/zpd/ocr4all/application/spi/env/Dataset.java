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
 * Defines data sets.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class Dataset {
	/**
	 * The collections.
	 */
	private List<Collection> collections;

	/**
	 * Default constructor for a data set.
	 * 
	 * @since 17
	 */
	public Dataset() {
		super();
	}

	/**
	 * Creates a dataset.
	 * 
	 * @since 17
	 */
	public Dataset(java.util.Collection<Collection> collections) {
		super();

		this.collections = new ArrayList<>();
		for (Collection collection : collections)
			if (collection != null && collection.isConsistent())
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
	 * Set the collections.
	 *
	 * @param collections The collections to set.
	 * @since 17
	 */
	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	/**
	 * Defines collections for data sets.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Collection {
		/**
		 * The id.
		 */
		private String id;

		/**
		 * The sets.
		 */
		private List<Set> sets;

		/**
		 * Default constructor for a collection.
		 * 
		 * @since 17
		 */
		public Collection() {
			super();
		}

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

			this.sets = new ArrayList<>();
			for (Set set : sets)
				if (set != null && set.isConsistent())
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
		 * Set the id.
		 *
		 * @param id The id to set.
		 * @since 17
		 */
		public void setId(String id) {
			this.id = id;
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
		 * Set the sets.
		 *
		 * @param sets The sets to set.
		 * @since 17
		 */
		public void setSets(List<Set> sets) {
			this.sets = sets;
		}

		/**
		 * Defines sets for collections.
		 *
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 1.8
		 */
		public static class Set {
			/**
			 * The id.
			 */
			private String id;

			/**
			 * The xml extension.
			 */
			private String xml;

			/**
			 * The image extension.
			 */
			private String image;

			/**
			 * Default constructor for a set.
			 * 
			 * @since 17
			 */
			public Set() {
				super();
			}

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
			 * Set the id.
			 *
			 * @param id The id to set.
			 * @since 17
			 */
			public void setId(String id) {
				this.id = id;
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
			 * Set the xml extension.
			 *
			 * @param xml The xml extension to set.
			 * @since 17
			 */
			public void setXml(String xml) {
				this.xml = xml;
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

			/**
			 * Set the image extension.
			 *
			 * @param image The image extension to set.
			 * @since 17
			 */
			public void setImage(String image) {
				this.image = image;
			}
		}
	}
}
