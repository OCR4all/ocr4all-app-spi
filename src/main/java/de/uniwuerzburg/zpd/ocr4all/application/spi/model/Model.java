/**
 * File:     Model.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     19.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model is an immutable class that defines models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class Model {
	/**
	 * The entries.
	 */
	private final List<Entry> entries = new ArrayList<>();

	/**
	 * Creates a model.
	 * 
	 * @param entries The entries.
	 * @throws IllegalArgumentException Throws if an entry is null.
	 * @since 1.8
	 */
	public Model(Entry... entries) throws IllegalArgumentException {
		this(Arrays.asList(entries));
	}

	/**
	 * Creates a model.
	 * 
	 * @param entries The entries.
	 * @throws IllegalArgumentException Throws if the entries or one of its entries
	 *                                  is null.
	 * @since 1.8
	 */
	public Model(List<Entry> entries) throws IllegalArgumentException {
		super();

		if (entries == null)
			throw new IllegalArgumentException("The entries cannot be null.");

		for (Entry entry : entries)
			if (entry == null)
				throw new IllegalArgumentException("The entry cannot be null.");
			else
				this.entries.add(entry);
	}

	/**
	 * Returns the entries.
	 *
	 * @return The entries.
	 * @since 1.8
	 */
	public List<Entry> getEntries() {
		return new ArrayList<Entry>(entries);
	}

}
