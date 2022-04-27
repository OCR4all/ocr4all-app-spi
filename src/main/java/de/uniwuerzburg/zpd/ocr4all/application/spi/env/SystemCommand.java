/**
 * File:     SystemCommand.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     12.04.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;

/**
 * SystemCommand is an immutable class that defines system commands.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class SystemCommand {
	/**
	 * Defines types.
	 *
	 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
	 * @version 1.0
	 * @since 1.8
	 */
	public enum Type {
		docker, convert, identify
	}

	/**
	 * The type.
	 */
	private final Type type;

	/**
	 * The command.
	 */
	private final Path command;

	/**
	 * True if available.
	 */
	private final boolean isAvailable;

	/**
	 * Creates a system command.
	 * 
	 * @param command     The command.
	 * @param isAvailable True if available.
	 * @since 1.8
	 */
	public SystemCommand(Type type, Path command, boolean isAvailable) {
		super();

		this.type = type;
		this.command = command;
		this.isAvailable = isAvailable;
	}

	/**
	 * Returns the type.
	 *
	 * @return The type.
	 * @since 1.8
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Returns the command.
	 *
	 * @return The command.
	 * @since 1.8
	 */
	public Path getCommand() {
		return command;
	}

	/**
	 * Returns true if available.
	 *
	 * @return True if available.
	 * @since 1.8
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

}
