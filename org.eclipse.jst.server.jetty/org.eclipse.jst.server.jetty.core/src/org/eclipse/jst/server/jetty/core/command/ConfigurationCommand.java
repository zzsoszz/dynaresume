package org.eclipse.jst.server.jetty.core.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.server.jetty.core.IJettyConfigurationWorkingCopy;

/**
 * Configuration command.
 */
public abstract class ConfigurationCommand extends AbstractOperation {
	protected IJettyConfigurationWorkingCopy configuration;

	/**
	 * ConfigurationCommand constructor comment.
	 * 
	 * @param configuration
	 *            a Jetty configuration
	 * @param label
	 *            a label
	 */
	public ConfigurationCommand(IJettyConfigurationWorkingCopy configuration,
			String label) {
		super(label);
		this.configuration = configuration;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return execute(monitor, info);
	}

	public abstract void execute();

	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		execute();
		return null;
	}

	public abstract void undo();

	public IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		undo();
		return null;
	}
}