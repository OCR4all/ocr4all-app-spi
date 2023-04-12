/**
 * File:     Target.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     22.01.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.uniwuerzburg.zpd.ocr4all.application.spi.util.ImageFormat;

/**
 * Target is an immutable class that defines targets for service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class Target {
	/**
	 * The folder for exchange.
	 */
	private final Path exchange;

	/**
	 * The folder for opt.
	 */
	private final Path opt;

	/**
	 * The project target.
	 */
	private final Project project;

	/**
	 * The sandbox target.
	 */
	private final Sandbox sandbox;

	/**
	 * Creates a target for a service provider.
	 * 
	 * @param exchange The exchange folder.
	 * @param opt      The opt folder.
	 * @param project  The project target.
	 * @param sandbox  The sandbox target.
	 * @since 1.8
	 */
	public Target(Path exchange, Path opt, Project project, Sandbox sandbox) {
		super();

		this.exchange = exchange;
		this.opt = opt;
		this.project = project;
		this.sandbox = sandbox;
	}

	/**
	 * Returns true if the folder for exchange is a directory.
	 *
	 * @return True if the folder is a directory; false if the folder does not
	 *         exist, is not a directory, or it cannot be determined if the folder
	 *         is a directory or not.
	 * 
	 * @since 1.8
	 */
	public boolean isExchangeDirectory() {
		return Files.isDirectory(exchange);
	}

	/**
	 * Returns the folder for exchange.
	 *
	 * @return The folder for exchange.
	 * @since 1.8
	 */
	public Path getExchange() {
		return exchange;
	}

	/**
	 * Returns true if the folder for opt is a directory.
	 *
	 * @return True if the folder is a directory; false if the folder does not
	 *         exist, is not a directory, or it cannot be determined if the folder
	 *         is a directory or not.
	 * 
	 * @since 1.8
	 */
	public boolean isOptDirectory() {
		return Files.isDirectory(opt);
	}

	/**
	 * Returns the folder for opt.
	 *
	 * @return The folder for opt.
	 * @since 1.8
	 */
	public Path getOpt() {
		return opt;
	}

	/**
	 * Returns true if the project target is set.
	 *
	 * @return True if the project target is set.
	 * @since 1.8
	 */
	public boolean isProjectSet() {
		return project != null;
	}

	/**
	 * Returns the project target.
	 *
	 * @return The project target.
	 * @since 1.8
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Returns true if the sandbox target is set.
	 *
	 * @return True if the sandbox target is set.
	 * @since 1.8
	 */
	public boolean isSandboxSet() {
		return sandbox != null;
	}

	/**
	 * Returns the sandbox target.
	 *
	 * @return The sandbox target.
	 * @since 1.8
	 */
	public Sandbox getSandbox() {
		return sandbox;
	}

	/**
	 * Sandbox is an immutable class that defines project targets.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Project {
		/**
		 * The root path.
		 */
		private final Path root;

		/**
		 * The folio configuration file.
		 */
		private final Path folio;

		/**
		 * The folders for the images.
		 */
		private final Images images;

		/**
		 * Creates a project target.
		 * 
		 * @param root   The root path.
		 * @param folio  The folio configuration file.
		 * @param images The folders for the images.
		 * @since 1.8
		 */
		public Project(Path root, Path folio, Images images) {
			super();

			this.root = root;
			this.folio = folio;
			this.images = images;
		}

		/**
		 * Returns the root path.
		 *
		 * @return The root path.
		 * @since 1.8
		 */
		public Path getRoot() {
			return root;
		}

		/**
		 * Returns the relative path to the root path.
		 *
		 * @param path The path to get the relative path.
		 * @return The relative path to root path. Null if the root path is not a prefix
		 *         of the given path.
		 * @since 1.8
		 */
		public Path getRootRelative(Path path) {
			return getRelativePath(root, path);
		}

		/**
		 * Returns true if the folio is a configuration file.
		 *
		 * @return True if the folio is a configuration file.
		 * @since 1.8
		 */
		public boolean isFolioFile() {
			return Files.isRegularFile(folio);
		}

		/**
		 * Returns the folio configuration file.
		 *
		 * @return The folio configuration file.
		 * @since 1.8
		 */
		public Path getFolio() {
			return folio;
		}

		/**
		 * Returns true if the folders for the images is set.
		 *
		 * @return True if the folders for the images is set.
		 * @since 1.8
		 */
		public boolean isImagesSet() {
			return images != null;
		}

		/**
		 * Returns the folders for the images.
		 *
		 * @return The folders for the images.
		 * @since 1.8
		 */
		public Images getImages() {
			return images;
		}

		/**
		 * Images is an immutable class that defines folders for the projects images.
		 *
		 * 
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 1.8
		 */
		public static class Images {
			/**
			 * The folder for the folios.
			 */
			private final Path folios;

			/**
			 * The derivatives quality images for the folios.
			 */
			private final Derivatives derivatives;

			/**
			 * Creates folders for the project images.
			 * 
			 * @param folios      The folder for the folios.
			 * @param derivatives The derivatives quality image folders for the folios.
			 * @since 1.8
			 */
			public Images(Path folios, Derivatives derivatives) {
				super();

				this.folios = folios;
				this.derivatives = derivatives;
			}

			/**
			 * Returns true if the folder for the folios is a directory.
			 *
			 * @return True if the folder is a directory; false if the folder does not
			 *         exist, is not a directory, or it cannot be determined if the folder
			 *         is a directory or not.
			 * 
			 * @since 1.8
			 */
			public boolean isFoliosDirectory() {
				return Files.isDirectory(folios);
			}

			/**
			 * Returns true if the folios directory is empty.
			 * 
			 * @throws IOException If an I/O error occurs.
			 * @since 1.8
			 */
			public boolean isFoliosEmpty() throws IOException {
				return isDirectoryEmpty(folios);
			}

			/**
			 * Returns the folder for the folios.
			 *
			 * @return The folder for the folios.
			 * @since 1.8
			 */
			public Path getFolios() {
				return folios;
			}

			/**
			 * Returns true if the derivatives quality images for the folios is set.
			 *
			 * @return True if the derivatives quality images for the folios is set.
			 * @since 1.8
			 */
			public boolean isDerivativesSet() {
				return derivatives != null;
			}

			/**
			 * Returns the derivatives quality images for the folios.
			 *
			 * @return The derivatives quality images for the folios.
			 * @since 1.8
			 */
			public Derivatives getDerivatives() {
				return derivatives;
			}

			/**
			 * Derivatives is an immutable class that defines derivatives quality image
			 * folders for the folios.
			 *
			 * 
			 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
			 * @version 1.0
			 * @since 1.8
			 */
			public static class Derivatives {
				/**
				 * The folios derivatives format.
				 */
				private final ImageFormat format;

				/**
				 * The folder for the folios derivatives quality thumbnail.
				 */
				private final Path thumbnail;

				/**
				 * The folder for the folios derivatives quality detail.
				 */
				private final Path detail;

				/**
				 * The folder for the folios derivatives quality best.
				 */
				private final Path best;

				/**
				 * Creates derivatives quality image folders for the folios.
				 * 
				 * @param format    The folios derivatives format.
				 * @param thumbnail The folder for the folios derivatives quality thumbnail.
				 * @param detail    The folder for the folios derivatives quality detail.
				 * @param best      The folder for the folios derivatives quality best.
				 * @since 1.8
				 */
				public Derivatives(ImageFormat format, Path thumbnail, Path detail, Path best) {
					super();

					this.format = format;
					this.thumbnail = thumbnail;
					this.detail = detail;
					this.best = best;
				}

				/**
				 * Returns the folios derivatives format.
				 *
				 * @return The folios derivatives format.
				 * @since 1.8
				 */
				public ImageFormat getFormat() {
					return format;
				}

				/**
				 * Returns true if the folder for the folios derivatives quality thumbnail is a
				 * directory.
				 *
				 * @return True if the folder is a directory; false if the folder does not
				 *         exist, is not a directory, or it cannot be determined if the folder
				 *         is a directory or not.
				 * 
				 * @since 1.8
				 */
				public boolean isThumbnailDirectory() {
					return Files.isDirectory(thumbnail);
				}

				/**
				 * Returns the folder for the folios derivatives quality thumbnail.
				 *
				 * @return The folder for the folios derivatives quality thumbnail.
				 * @since 1.8
				 */
				public Path getThumbnail() {
					return thumbnail;
				}

				/**
				 * Returns true if the folder for the folios derivatives quality detail is a
				 * directory.
				 *
				 * @return True if the folder is a directory; false if the folder does not
				 *         exist, is not a directory, or it cannot be determined if the folder
				 *         is a directory or not.
				 * 
				 * @since 1.8
				 */
				public boolean isDetailDirectory() {
					return Files.isDirectory(detail);
				}

				/**
				 * Returns the folder for the folios derivatives quality detail.
				 *
				 * @return The folder for the folios derivatives quality detail.
				 * @since 1.8
				 */
				public Path getDetail() {
					return detail;
				}

				/**
				 * Returns true if the folder for the folios derivatives quality best is a
				 * directory.
				 *
				 * @return True if the folder is a directory; false if the folder does not
				 *         exist, is not a directory, or it cannot be determined if the folder
				 *         is a directory or not.
				 * 
				 * @since 1.8
				 */
				public boolean isBestDirectory() {
					return Files.isDirectory(best);
				}

				/**
				 * Returns the folder for the folios derivatives quality best.
				 *
				 * @return The folder for the folios derivatives quality best.
				 * @since 1.8
				 */
				public Path getBest() {
					return best;
				}
			}
		}
	}

	/**
	 * Sandbox is an immutable class that defines sandbox targets.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public static class Sandbox {
		/**
		 * The root path.
		 */
		private final Path root;

		/**
		 * The snapshots root path.
		 */
		private final Path snapshots;

		/**
		 * True if the sandbox was launched.
		 */
		private final boolean isLaunched;

		/**
		 * The input path. Null if there is not input directory.
		 */
		private final Path input;

		/**
		 * The snapshot track for the input directory. The track for a root snapshot is
		 * an empty list. Null if not available.
		 */
		private List<Integer> snapshotTrack;

		/**
		 * The mets.
		 */
		private final Mets mets;

		/**
		 * Creates a sandbox target.
		 * 
		 * @param root          The root path.
		 * @param snapshots     The snapshots root path.
		 * @param isLaunched    True if the sandbox was launched.
		 * @param input         The input path. Null if there is not input directory.
		 * @param snapshotTrack The snapshot track for the input directory. The track
		 *                      for a root snapshot is an empty list. Null if not
		 *                      available.
		 * @param mets          The mets core configuration.
		 * @since 1.8
		 */
		public Sandbox(Path root, Path snapshots, boolean isLaunched, Path input, List<Integer> snapshotTrack,
				Mets mets) {
			super();

			this.root = root;
			this.snapshots = snapshots;
			this.isLaunched = isLaunched;
			this.input = input;
			this.snapshotTrack = snapshotTrack;
			this.mets = mets;
		}

		/**
		 * Returns the root path.
		 *
		 * @return The root path.
		 * @since 1.8
		 */
		public Path getRoot() {
			return root;
		}

		/**
		 * Returns the relative path to the root path.
		 *
		 * @param path The path to get the relative path.
		 * @return The relative path to root path. Null if the root path is not a prefix
		 *         of the given path.
		 * @since 1.8
		 */
		public Path getRootRelative(Path path) {
			return getRelativePath(root, path);
		}

		/**
		 * Returns the snapshots root path.
		 *
		 * @return The snapshots root path.
		 * @since 1.8
		 */
		public Path getSnapshots() {
			return snapshots;
		}

		/**
		 * Returns the relative path to the snapshots root path.
		 *
		 * @param path The path to get the relative path.
		 * @return The relative path to snapshots root path. Null if the snapshots root
		 *         path is not a prefix of the given path.
		 * @since 1.8
		 */
		public Path getSnapshotsRelative(Path path) {
			return getRelativePath(snapshots, path);
		}

		/**
		 * Returns true if the sandbox was launched.
		 *
		 * @return True if the sandbox was launched.
		 * @since 1.8
		 */
		public boolean isLaunched() {
			return isLaunched;
		}

		/**
		 * Returns the input path.
		 *
		 * @return The input path. Null if there is not input directory.
		 * @since 1.8
		 */
		public Path getInput() {
			return input;
		}

		/**
		 * Returns the snapshot track for the input directory.
		 *
		 * @return The snapshot track for the input directory. The track for a root
		 *         snapshot is an empty list. Null if not available.
		 * @since 1.8
		 */
		public List<Integer> getSnapshotTrack() {
			return snapshotTrack;
		}

		/**
		 * Returns the mets core configuration.
		 *
		 * @return The mets core configuration.
		 * @since 1.8
		 */
		public Mets getMets() {
			return mets;
		}

		/**
		 * Defines mets core configuration.
		 *
		 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
		 * @version 1.0
		 * @since 1.8
		 */
		public static class Mets {
			/**
			 * The file name.
			 */
			private String file;

			/**
			 * The group.
			 */
			private String group;

			/**
			 * Creates a mets core configuration
			 * 
			 * @param file  The file name.
			 * @param group The group.
			 * @since 1.8
			 */
			public Mets(String file, String group) {
				super();

				this.file = file;
				this.group = group;
			}

			/**
			 * Returns the file name.
			 *
			 * @return The file name.
			 * @since 1.8
			 */
			public String getFile() {
				return file;
			}

			/**
			 * Set the file name.
			 *
			 * @param file The file name to set.
			 * @since 1.8
			 */
			public void setFile(String file) {
				this.file = file;
			}

			/**
			 * Returns the group.
			 *
			 * @return The group.
			 * @since 1.8
			 */
			public String getGroup() {
				return group;
			}

			/**
			 * Set the group.
			 *
			 * @param group The group to set.
			 * @since 1.8
			 */
			public void setGroup(String group) {
				this.group = group;
			}

		}
	}

	/**
	 * Returns true if the directory is empty.
	 * 
	 * @param path The directory.
	 * @return True if the directory is empty.
	 * @throws IOException If an I/O error occurs.
	 * @since 1.8
	 */
	private static boolean isDirectoryEmpty(final Path path) throws IOException {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
			return !dirStream.iterator().hasNext();
		}
	}

	/**
	 * Returns the relative path part of target path with respect to the root path.
	 * 
	 * @param root   The root path.
	 * @param target The target path.
	 * @return The relative path. Null if the root path is not a prefix of the
	 *         target path.
	 * @since 1.8
	 */
	private static Path getRelativePath(Path root, Path target) {
		if (root == null || target == null)
			return null;
		else {
			target = target.normalize();

			if (!target.startsWith(root))
				return null;
			else
				return Paths.get(root.equals(target) ? "" : target.toString().substring(root.toString().length() + 1));
		}
	}

}
