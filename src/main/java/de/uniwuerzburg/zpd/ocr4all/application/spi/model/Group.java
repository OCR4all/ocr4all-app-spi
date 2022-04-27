/**
 * File:     Group.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * Group is an immutable class that defines groups of entries.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class Group extends Entry {
	/**
	 * True if the collapsible group container is open at the beginning. Otherwise,
	 * it is closed.
	 */
	private final boolean isOpen;

	/**
	 * The entries.
	 */
	private final List<Entry> entries = new ArrayList<>();

	/**
	 * Creates a group of entries. The collapsible group container is closed at the
	 * beginning.
	 * 
	 * @param description The description.
	 * @param entries     The entries.
	 * @throws IllegalArgumentException Throws if the description or one of its
	 *                                  entries is null.
	 * @since 1.8
	 */
	public Group(Internationalization description, Entry... entries) throws IllegalArgumentException {
		this(false, description, entries);
	}

	/**
	 * Creates a group of entries. The collapsible group container is closed at the
	 * beginning.
	 * 
	 * @param description The description.
	 * @param entries     The entries.
	 * @throws IllegalArgumentException Throws if the description, the entries or
	 *                                  one of its entries is null.
	 * @since 1.8
	 */
	public Group(Internationalization description, List<Entry> entries) throws IllegalArgumentException {
		this(false, description, entries);
	}

	/**
	 * Creates a group of entries.
	 * 
	 * @param isOpen      True if the collapsible group container is open at the
	 *                    beginning. Otherwise, it is closed.
	 * @param description The description.
	 * @param entries     The entries.
	 * @throws IllegalArgumentException Throws if the description or one of its
	 *                                  entries is null.
	 * @since 1.8
	 */
	public Group(boolean isOpen, Internationalization description, Entry... entries) throws IllegalArgumentException {
		this(isOpen, description, Arrays.asList(entries));
	}

	/**
	 * Creates a group of entries.
	 * 
	 * @param isOpen      True if the collapsible group container is open at the
	 *                    beginning. Otherwise, it is closed.
	 * @param description The description.
	 * @param entries     The entries.
	 * @throws IllegalArgumentException Throws if the description, the entries or
	 *                                  one of its entries is null.
	 * @since 1.8
	 */
	public Group(boolean isOpen, Internationalization description, List<Entry> entries)
			throws IllegalArgumentException {
		this(isOpen, description, entries, false);
	}

	/**
	 * Creates a group of entries.
	 * 
	 * @param isOpen      True if the collapsible group container is open at the
	 *                    beginning. Otherwise, it is closed.
	 * @param description The description.
	 * @param entries     The entries.
	 * @param isDisabled  True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the description, the entries or
	 *                                  one of its entries is null.
	 * @since 1.8
	 */
	public Group(boolean isOpen, Internationalization description, List<Entry> entries, boolean isDisabled)
			throws IllegalArgumentException {
		super(description, isDisabled);

		if (entries == null)
			throw new IllegalArgumentException("the entries cannot be null.");

		for (Entry entry : entries)
			if (entry == null)
				throw new IllegalArgumentException("the entry cannot be null.");
			else
				this.entries.add(entry);

		this.isOpen = isOpen;
	}

	/**
	 * Returns true if the collapsible group container is open at the beginning.
	 * Otherwise, it is closed.
	 *
	 * @return True if the collapsible group container is open at the beginning.
	 *         Otherwise, it is closed.
	 * @since 1.8
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Returns the entries.
	 *
	 * @return The entries.
	 * @since 1.8
	 */
	public List<Entry> getEntries() {
		return new ArrayList<>(entries);
	}
}
