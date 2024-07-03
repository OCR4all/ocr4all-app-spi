/**
 * File:     ProcessFramework.java
 * Package:  de.uniwuerzburg.zpd.ocr4all.application.spi.env
 * 
 * Author:   Herbert Baier (herbert.baier@uni-wuerzburg.de)
 * Date:     29.01.2021
 */
package de.uniwuerzburg.zpd.ocr4all.application.spi.env;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * ProcessFramework is an immutable class that defines process frameworks for
 * service providers.
 *
 * @author <a href="mailto:herbert.baier@uni-wuerzburg.de">Herbert Baier</a>
 * @version 1.0
 * @since 1.8
 */
public class ProcessFramework extends Framework {
	/**
	 * The target.
	 */
	private final Target target;

	/**
	 * The snapshot track for the output directory. The track for a root snapshot is
	 * an empty list. Null if not available.
	 */
	private List<Integer> snapshotTrack;

	/**
	 * The projects directory.
	 */
	private final Path projects;

	/**
	 * The output directory.
	 */
	private final Path output;

	/**
	 * Creates a process framework for a service provider.
	 * 
	 * @param operatingSystem The operating system.
	 * @param uid             The effective system user ID. -1 if not defined.
	 * @param gid             The effective system group ID. -1 if not defined.
	 * @param application     The application.
	 * @param user            The user. Null if not defined.
	 * @param target          The target.
	 * @param output          The output directory.
	 * @param snapshotTrack   The snapshot track for the output directory. The track
	 *                        for a root snapshot is an empty list. Null if not
	 *                        available.
	 * @param projects        The projects directory.
	 * @param temporary       The temporary directory.
	 * @since 1.8
	 */
	public ProcessFramework(OperatingSystem operatingSystem, int uid, int gid, Application application, String user,
			Target target, Path output, List<Integer> snapshotTrack, Path projects, Path temporary) {
		super(operatingSystem, uid, gid, application, user, temporary);

		this.target = target;
		this.snapshotTrack = snapshotTrack;
		this.projects = projects;
		this.output = output;
	}

	/**
	 * Returns the target.
	 *
	 * @return The target.
	 * @since 1.8
	 */
	public Target getTarget() {
		return target;
	}

	/**
	 * Returns the snapshot track for the output directory.
	 *
	 * @return The snapshot track for the output directory. The track for a root
	 *         snapshot is an empty list. Null if not available.
	 * @since 1.8
	 */
	public List<Integer> getSnapshotTrack() {
		return snapshotTrack;
	}

	/**
	 * Set the snapshotTrack.
	 *
	 * @param snapshotTrack The snapshotTrack to set.
	 * @since 1.8
	 */
	public void setSnapshotTrack(List<Integer> snapshotTrack) {
		this.snapshotTrack = snapshotTrack;
	}

	/**
	 * Returns the processor workspace path.
	 *
	 * @return The processor workspace path. Null if not available.
	 * @since 1.8
	 */
	public Path getProcessorWorkspace() {
		return target == null || target.getSandbox() == null ? null : target.getSandbox().getSnapshots();
	}

	/**
	 * Returns the processor workspace path relative to projects home.
	 *
	 * @return The processor workspace path relative to projects home. Null if not
	 *         available.
	 * @since 1.8
	 */
	public Path getProcessorWorkspaceRelativeProjects() {
		if (projects == null)
			return null;
		else {
			Path processorWorkspace = getProcessorWorkspace();

			if (processorWorkspace == null || !processorWorkspace.startsWith(projects)
					|| processorWorkspace.equals(projects))
				return null;
			else
				return Paths.get(processorWorkspace.toString().substring(projects.toString().length() + 1));

		}
	}

	/**
	 * Returns the mets path.
	 *
	 * @return The mets path. Null if not available.
	 * @since 1.8
	 */
	public Path getMets() {
		return getProcessorWorkspace() == null || target.getSandbox().getMets() == null
				|| target.getSandbox().getMets().getFile() == null ? null
						: Paths.get(getProcessorWorkspace().toString(), target.getSandbox().getMets().getFile());
	}

	/**
	 * Returns the mets group.
	 *
	 * @return The mets group. Null if not available.
	 * @since 1.8
	 */
	public String getMetsGroup() {
		return target == null || target.getSandbox() == null || target.getSandbox().getMets() == null ? null
				: target.getSandbox().getMets().getGroup();
	}

	/**
	 * Returns the relative output path of the processor workspace, this means, the
	 * sandbox snapshots root path.
	 *
	 * @return The relative output path of the processor workspace. Null if the
	 *         relative output path can not be specified.
	 * @since 1.8
	 */
	public Path getOutputRelativeProcessorWorkspace() {
		return target == null || target.getSandbox() == null ? null
				: target.getSandbox().getSnapshotsRelative(getOutput());
	}

	/**
	 * Returns the projects directory.
	 *
	 * @return The projects directory.
	 * @since 17
	 */
	public Path getProjects() {
		return projects;
	}

	/**
	 * Returns the output directory.
	 *
	 * @return The output directory.
	 * @since 17
	 */
	public Path getOutput() {
		return output;
	}

}
