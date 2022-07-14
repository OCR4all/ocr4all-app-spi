/**
 * File:     SelectField.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.model
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     24.11.2020
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import de.uniwuerzburg.zpd.ocr4all.application.spi.env.Internationalization;

/**
 * SelectField is an immutable class that defines select fields for models.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public final class SelectField extends Field<Object> {
	/**
	 * True if multiple options can be selected at once.
	 */
	private final boolean isMultipleOptions;

	/**
	 * The items.
	 */
	private final List<Item> items = new ArrayList<>();

	/**
	 * Creates a select field for a model. Only one option can be selected at once.
	 * 
	 * @param argument The argument.
	 * @param label    The label.
	 * @param items    The items.
	 * @throws IllegalArgumentException Throws if the argument, the label, the items
	 *                                  is null. Furthermore, throws if the option
	 *                                  items are empty.
	 * @since 1.8
	 */
	public SelectField(String argument, Internationalization label, Item... items) throws IllegalArgumentException {
		this(argument, label, Arrays.asList(items));
	}

	/**
	 * Creates a select field for a model. Only one option can be selected at once.
	 * 
	 * @param argument The argument.
	 * @param label    The label.
	 * @param items    The items.
	 * @throws IllegalArgumentException Throws if the argument, the label, the items
	 *                                  is null. Furthermore, throws if the option
	 *                                  items are empty.
	 * @since 1.8
	 */
	public SelectField(String argument, Internationalization label, List<Item> items) throws IllegalArgumentException {
		this(argument, label, false, items);
	}

	/**
	 * Creates a select field for a model.
	 * 
	 * @param argument          The argument.
	 * @param label             The label.
	 * @param isMultipleOptions True if multiple options can be selected at once.
	 * @param items             The items.
	 * @throws IllegalArgumentException Throws if the argument, the label, the items
	 *                                  is null. Furthermore, throws if the option
	 *                                  items are empty.
	 * @since 1.8
	 */
	public SelectField(String argument, Internationalization label, boolean isMultipleOptions, Item... items)
			throws IllegalArgumentException {
		this(argument, label, isMultipleOptions, Arrays.asList(items));
	}

	/**
	 * Creates a select field for a model.
	 * 
	 * @param argument          The argument.
	 * @param label             The label.
	 * @param isMultipleOptions True if multiple options can be selected at once.
	 * @param items             The items.
	 * @throws IllegalArgumentException Throws if the argument, the label, the items
	 *                                  is null. Furthermore, throws if the option
	 *                                  items are empty.
	 * @since 1.8
	 */
	public SelectField(String argument, Internationalization label, boolean isMultipleOptions, List<Item> items)
			throws IllegalArgumentException {
		this(argument, label, null, isMultipleOptions, items, false);
	}

	/**
	 * Creates a select field for a model.
	 * 
	 * @param argument          The argument.
	 * @param label             The label.
	 * @param description       The description. Null if no description is required.
	 * @param isMultipleOptions True if multiple options can be selected at once.
	 * @param items             The items.
	 * @throws IllegalArgumentException Throws if the argument, the label, the items
	 *                                  is null. Furthermore, throws if the option
	 *                                  items are empty.
	 * @since 1.8
	 */
	public SelectField(String argument, Internationalization label, Internationalization description,
			boolean isMultipleOptions, Item... items) throws IllegalArgumentException {
		this(argument, label, description, isMultipleOptions, Arrays.asList(items), false);
	}

	/**
	 * Creates a select field for a model.
	 * 
	 * @param argument          The argument.
	 * @param label             The label.
	 * @param description       The description. Null if no description is required.
	 * @param isMultipleOptions True if multiple options can be selected at once.
	 * @param items             The items.
	 * @param isDisabled        True if the field is disabled.
	 * @throws IllegalArgumentException Throws if the argument, the label, the items
	 *                                  is null. Furthermore, throws if the option
	 *                                  items are empty.
	 * @since 1.8
	 */
	public SelectField(String argument, Internationalization label, Internationalization description,
			boolean isMultipleOptions, List<Item> items, boolean isDisabled) throws IllegalArgumentException {
		super(argument, null, label, description, null, isDisabled);

		if (items == null)
			throw new IllegalArgumentException("the items cannot be null.");

		for (Item item : items)
			if (item != null) {
				if (item instanceof Option)
					this.items.add(item);
				else {
					Association association = (Association) item;

					if (association.getOptions() != null) {
						List<Option> options = new ArrayList<>();

						for (Option option : association.getOptions())
							if (option != null)
								options.add(option);

						if (!options.isEmpty())
							this.items.add(new Association(options, item.description));
					}
				}
			}

		if (this.items.isEmpty())
			throw new IllegalArgumentException("the items cannot be empty.");

		this.isMultipleOptions = isMultipleOptions;

	}

	/**
	 * Returns true if multiple options can be selected at once.
	 *
	 * @return True if multiple options can be selected at once.
	 * @since 1.8
	 */
	public boolean isMultipleOptions() {
		return isMultipleOptions;
	}

	/**
	 * Returns the items.
	 *
	 * @return The items.
	 * @since 1.8
	 */
	public List<Item> getItems() {
		return new ArrayList<>(items);
	}

	/**
	 * Item is an immutable class that defines items.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Item {
		/**
		 * The description. Null if not defined.
		 */
		private final Internationalization description;

		/**
		 * Creates an item.
		 * 
		 * @param description The description. Null if not defined.
		 * @since 1.8
		 */
		public Item(Internationalization description) {
			super();
			this.description = description;
		}

		/**
		 * Returns the description.
		 *
		 * @param locale The locale.
		 * @return The description. Empty if not available.
		 * @since 1.8
		 */
		public Optional<String> getDescription(Locale locale) {
			return description == null || description.getString(locale) == null ? Optional.empty()
					: Optional.of(description.getString(locale));
		}
	}

	/**
	 * Association is an immutable class that defines associations.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Association extends Item {
		/**
		 * The options.
		 */
		private final List<Option> options;

		/**
		 * Creates an association.
		 * 
		 * @param options     The options.
		 * @param description The description. Null if not defined.
		 * @since 1.8
		 */
		public Association(List<Option> options, Internationalization description) throws IllegalArgumentException {
			super(description);

			this.options = options;
		}

		/**
		 * Returns the options.
		 *
		 * @return The options.
		 * @since 1.8
		 */
		public List<Option> getOptions() {
			return new ArrayList<>(options);
		}
	}

	/**
	 * Option is an immutable class that defines options.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Option extends Item {
		/**
		 * True if selected.
		 */
		private final boolean isSelected;

		/**
		 * The value.
		 */
		private final String value;

		/**
		 * True if disabled.
		 */
		private final boolean isDisabled;

		/**
		 * Creates an option. The option is not selected.
		 * 
		 * @param value       The value.
		 * @param description The description. Null if not defined.
		 * @since 1.8
		 */
		public Option(String value, Internationalization description) throws IllegalArgumentException {
			this(false, value, description);
		}

		/**
		 * Creates an option.
		 * 
		 * @param isSelected  True if selected.
		 * @param value       The value.
		 * @param description The description. Null if not defined.
		 * @throws IllegalArgumentException Throws if the value is null or empty.
		 * @since 1.8
		 */
		public Option(boolean isSelected, String value, Internationalization description)
				throws IllegalArgumentException {
			this(isSelected, value, description, false);
		}

		/**
		 * Creates an option.
		 * 
		 * @param isSelected  True if selected.
		 * @param value       The value.
		 * @param description The description. Null if not defined.
		 * @param isDisabled  True if disabled.
		 * @throws IllegalArgumentException Throws if the value is null or empty.
		 * @since 1.8
		 */
		public Option(boolean isSelected, String value, Internationalization description, boolean isDisabled)
				throws IllegalArgumentException {
			super(description);
			if (value == null || value.trim().isEmpty())
				throw new IllegalArgumentException("the value cannot be null or empty.");

			this.isSelected = isSelected;
			this.value = value.trim();
			this.isDisabled = isDisabled;
		}

		/**
		 * Returns true if selected.
		 *
		 * @return True if selected.
		 * @since 1.8
		 */
		public boolean isSelected() {
			return isSelected;
		}

		/**
		 * Returns the value.
		 *
		 * @return The value.
		 * @since 1.8
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Returns true if the option is disabled.
		 *
		 * @return True if the option is disabled.
		 * @since 1.8
		 */
		public boolean isDisabled() {
			return isDisabled;
		}

	}
}
