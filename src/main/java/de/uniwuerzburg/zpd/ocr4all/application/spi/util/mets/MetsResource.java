/**
 * File:     MetsResource.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util.mets
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     12.07.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util.mets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Defines mets resources.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class MetsResource {
	/**
	 * Defines templates.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public enum Template {
		mets_agent("mets-agent"), mets_file_group("mets-file-group"), mets_file("mets-file"), mets_page("mets-page");

		/**
		 * The folder.
		 */
		private static final String folder = "templates/mets/";

		/**
		 * The suffix.
		 */
		private static final String suffix = ".template";

		/**
		 * The name.
		 */
		private final String name;

		/**
		 * Returns an input stream for reading the specified resource.
		 * 
		 * @param name The resource name.
		 * @return An input stream for reading the specified resource. Null if the
		 *         resource could not be found.
		 * @since 17
		 */
		private static InputStream getResourceAsStream(String name) {
			return MetsResource.class.getClassLoader().getResourceAsStream(name);
		}

		/**
		 * Returns the resource content as text.
		 * 
		 * @param name The resource name.
		 * @return The resource content as text.
		 * @throws IllegalArgumentException Throws if the resource could not be found.
		 * @throws IOException              If an I/O error occurs.
		 * @since 17
		 */
		public static String getResourceAsText(String name) throws IllegalArgumentException, IOException {
			StringBuilder resultStringBuilder = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(getResourceAsStream(name)))) {
				String line;
				while ((line = br.readLine()) != null) {
					resultStringBuilder.append(line).append("\n");
				}
			}

			return resultStringBuilder.toString();
		}

		/**
		 * Creates a template.
		 * 
		 * @param name The name.
		 * @since 17
		 */
		private Template(String name) {
			this.name = name;
		}

		/**
		 * Returns the resource name.
		 * 
		 * @return The resource name.
		 * @since 17
		 */
		private String getResourceName() {
			return folder + name + suffix;
		}

		/**
		 * Returns the template content.
		 * 
		 * @return The template content
		 * @throws IllegalArgumentException Throws if the resource could not be found.
		 * @throws IOException              If an I/O error occurs.
		 * @since 17
		 */
		private String getResourceAsText() throws IllegalArgumentException, IOException {
			return getResourceAsText(getResourceName());
		}
	}

	/**
	 * Define patterns for mets templates.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public enum Pattern {
		other_role, software_name, input_file_group, output_file_group, parameter,

		file_group, file_template,

		file_id, file_mime_type, file_name,

		page_id;

		/**
		 * Returns the pattern.
		 * 
		 * @return The pattern.
		 * @since 17
		 */
		public String getPattern() {
			return "[ocr4all-" + name() + "]";
		}
	}

	/**
	 * The resources.
	 */
	private final Hashtable<Template, String> resources = new Hashtable<>();

	/**
	 * Default constructor for a mets resources.
	 * 
	 * @throws IllegalArgumentException Throws if a resource could not be found.
	 * @throws IOException              If an I/O error occurs.
	 * 
	 * @since 17
	 */
	public MetsResource() throws IllegalArgumentException, IOException {
		super();

		for (Template template : Template.values())
			resources.put(template, template.getResourceAsText());
	}

	/**
	 * Returns the resource of given template.
	 *
	 * @param template The template to return the resource.
	 * @return The resource.
	 * @since 17
	 */
	public String getResources(Template template) {
		return template == null ? null : resources.get(template);
	}

}
