/**
 * File:     PageXMLParser.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.util.pagexml
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     17.07.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.util.pagexml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * Defines PageXML parsers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public class PageXMLParser {
	/**
	 * The xml mapper that does not fails on unknown properties.
	 */
	private final XmlMapper xmlMapper = new XmlMapper();
	{
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Default constructor for a PageXML parser.
	 * 
	 * @since 17
	 */
	public PageXMLParser() {
		super();
	}

	/**
	 * Deserialise the given PageXML content.
	 * 
	 * @param content The PageXML content.
	 * @return The deserialised PageXML.
	 * @throws JsonProcessingException Throws if problems are encountered when
	 *                                 processing (parsing, generating) JSON/XML
	 *                                 content that are not pure I/O problems. .
	 * @throws JsonMappingException    Throws if the input JSON/XML structure does
	 *                                 not match structure expected for result type
	 *                                 (or has other mismatch issues).
	 * @since 17
	 */
	public Root deserialise(String content) throws JsonProcessingException, JsonMappingException {
		return xmlMapper.readValue(content, Root.class);
	}

	/**
	 * Deserialise the given PageXML file.
	 * 
	 * @param file The PageXML file.
	 * @return The deserialised PageXML.
	 * @throws JsonParseException   Throws if underlying input contains invalid
	 *                              content of type JsonParser supports (JSON/XML
	 *                              for default case).
	 * @throws JsonMappingException Throws if the input JSON/XML structure does not
	 *                              match structure expected for result type (or has
	 *                              other mismatch issues).
	 * @throws IOException          Throws if a low-level I/O problem (unexpected
	 *                              end-of-input, network error) occurs.
	 * @since 17
	 */
	public Root deserialise(File file) throws JsonParseException, JsonMappingException, IOException {
		return xmlMapper.readValue(file, Root.class);
	}

	/**
	 * Defines PageXML deserialised root objects.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	@JacksonXmlRootElement(namespace = "PcGts")
	public static class Root {
		/**
		 * The page.
		 */
		@JacksonXmlProperty(localName = "Page")
		private Page page;

		/**
		 * Returns the page.
		 *
		 * @return The page.
		 * @since 17
		 */
		public Page getPage() {
			return page;
		}

		/**
		 * Set the page.
		 *
		 * @param page The page to set.
		 * @since 17
		 */
		public void setPage(Page page) {
			this.page = page;
		}

		/**
		 * Defines PageXML deserialised page objects.
		 *
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 17
		 */
		public static class Page {
			/**
			 * The text regions.
			 */
			@JacksonXmlProperty(localName = "TextRegion")
			@JacksonXmlElementWrapper(useWrapping = false)
			private List<TextRegion> textRegions;

			/**
			 * Returns the text regions.
			 *
			 * @return The text regions.
			 * @since 17
			 */
			public List<TextRegion> getTextRegions() {
				return textRegions;
			}

			/**
			 * Set the text regions.
			 *
			 * @param textRegions The text regions to set.
			 * @since 17
			 */
			public void setTextRegions(List<TextRegion> textRegions) {
				this.textRegions = textRegions;
			}

			/**
			 * Defines PageXML deserialised text equivalence core objects.
			 *
			 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
			 * @version 1.0
			 * @since 17
			 */
			public static class TextEquivalenceCore {
				/**
				 * The text equivalence.
				 */
				@JacksonXmlProperty(localName = "TextEquiv")
				private TextEquivalence textEquivalence;

				/**
				 * Returns the text equivalence.
				 *
				 * @return The text equivalence.
				 * @since 17
				 */
				public TextEquivalence getTextEquivalence() {
					return textEquivalence;
				}

				/**
				 * Set the text equivalence.
				 *
				 * @param textEquivalence The text equivalence to set.
				 * @since 17
				 */
				public void setTextEquivalence(TextEquivalence textEquivalence) {
					this.textEquivalence = textEquivalence;
				}

				/**
				 * Defines PageXML deserialised text equivalence objects.
				 *
				 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
				 * @version 1.0
				 * @since 17
				 */
				public static class TextEquivalence {
					/**
					 * The index.
					 */
					@JacksonXmlProperty(localName = "index")
					private Integer index;

					/**
					 * The unicode text.
					 */
					@JacksonXmlProperty(localName = "Unicode")
					private Unicode unicode;

					/**
					 * Returns the index.
					 *
					 * @return The index.
					 * @since 17
					 */
					public Integer getIndex() {
						return index;
					}

					/**
					 * Set the index.
					 *
					 * @param index The index to set.
					 * @since 17
					 */
					public void setIndex(Integer index) {
						this.index = index;
					}

					/**
					 * Returns the unicode text.
					 *
					 * @return The unicode text.
					 * @since 17
					 */
					public Unicode getUnicode() {
						return unicode;
					}

					/**
					 * Set the unicode text.
					 *
					 * @param unicode The unicode text to set.
					 * @since 17
					 */
					public void setUnicode(Unicode unicode) {
						this.unicode = unicode;
					}

					/**
					 * Defines PageXML deserialised unicode objects.
					 *
					 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
					 * @version 1.0
					 * @since 17
					 */
					public static class Unicode {
						/**
						 * The text.
						 */
						@JacksonXmlText
						private String text;

						/**
						 * Returns the text.
						 *
						 * @return The text.
						 * @since 17
						 */
						public String getText() {
							return text;
						}

						/**
						 * Set the text.
						 *
						 * @param text The text to set.
						 * @since 17
						 */
						public void setText(String text) {
							this.text = text;
						}
					}
				}
			}

			/**
			 * Defines PageXML deserialised text region objects.
			 *
			 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
			 * @version 1.0
			 * @since 17
			 */
			public static class TextRegion extends TextEquivalenceCore {
				/**
				 * The text lines.
				 */
				@JacksonXmlProperty(localName = "TextLine")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<TextLine> textLines;

				/**
				 * Returns the text lines.
				 *
				 * @return The text lines.
				 * @since 17
				 */
				public List<TextLine> getTextLines() {
					return textLines;
				}

				/**
				 * Set the textLines.
				 *
				 * @param textLines The textLines to set.
				 * @since 17
				 */
				public void setTextLines(List<TextLine> textLines) {
					this.textLines = textLines;
				}

				/**
				 * Defines PageXML deserialised text line objects.
				 *
				 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
				 * @version 1.0
				 * @since 17
				 */
				public static class TextLine extends TextEquivalenceCore {
					/**
					 * The words.
					 */
					@JacksonXmlProperty(localName = "Word")
					@JacksonXmlElementWrapper(useWrapping = false)
					private List<Word> words;

					/**
					 * Returns the words.
					 *
					 * @return The words.
					 * @since 17
					 */
					public List<Word> getWords() {
						return words;
					}

					/**
					 * Set the words.
					 *
					 * @param words The words to set.
					 * @since 17
					 */
					public void setWords(List<Word> words) {
						this.words = words;
					}

					/**
					 * Defines PageXML deserialised word objects.
					 *
					 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
					 * @version 1.0
					 * @since 17
					 */
					public static class Word extends TextEquivalenceCore {

						/**
						 * The glyphs.
						 */
						@JacksonXmlProperty(localName = "Glyph")
						@JacksonXmlElementWrapper(useWrapping = false)
						private List<Glyph> glyphs;

						/**
						 * Returns the glyphs.
						 *
						 * @return The glyphs.
						 * @since 17
						 */
						public List<Glyph> getGlyphs() {
							return glyphs;
						}

						/**
						 * Set the glyphs.
						 *
						 * @param glyphs The glyphs to set.
						 * @since 17
						 */
						public void setGlyphs(List<Glyph> glyphs) {
							this.glyphs = glyphs;
						}

						/**
						 * Defines PageXML deserialised glyph objects.
						 *
						 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
						 * @version 1.0
						 * @since 17
						 */
						public static class Glyph extends TextEquivalenceCore {

						}
					}
				}
			}
		}
	}
}
