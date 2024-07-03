/**
 * File:     ProcessorCore.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.core
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     20.06.2024
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.core;

/**
 * Defines processor cores.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 17
 */
public interface ProcessorCore {
	/**
	 * Defines return states for the execution of the process.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public enum State {
		/**
		 * the process was carried out without problems
		 */
		completed,
		/**
		 * the process was aborted during execution
		 */
		canceled,
		/**
		 * the process was interrupted due to failures
		 */
		interrupted
	}

	/**
	 * Cancels the process.
	 * 
	 * @since 17
	 */
	public void cancel();

	/**
	 * Defines callback.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public interface Callback {
		/**
		 * Callback method for updated progress.
		 * 
		 * @param progress The current progress.
		 * @since 17
		 */
		public void updatedProgress(float progress);

		/**
		 * Callback method for updated standard output.
		 * 
		 * @param message The current message.
		 * @since 17
		 */
		public void updatedStandardOutput(String message);

		/**
		 * Callback method for updated standard error.
		 * 
		 * @param message The current message.
		 * @since 17
		 */
		public void updatedStandardError(String message);
	}


	/**
	 * Defines callback.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 17
	 */
	public interface LockSnapshotCallback extends Callback{
		/**
		 * Callback method to lock the snapshot.
		 * 
		 * @param comment The snapshot lock comment.
		 * @since 17
		 */
		public void lockSnapshot(String comment);
	}
}
