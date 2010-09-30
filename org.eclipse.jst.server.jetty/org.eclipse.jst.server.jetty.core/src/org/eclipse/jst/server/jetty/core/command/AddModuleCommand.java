package org.eclipse.jst.server.jetty.core.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.server.jetty.core.internal.Messages;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServerWorkingCopy;
/**
 * Command to add a web module to a server.
 */
public class AddModuleCommand extends AbstractOperation {
	protected IServerWorkingCopy server;
	protected IModule module;
	protected int modules = -1;

	/**
	 * AddModuleCommand constructor comment.
	 * 
	 * @param server a server
	 * @param module a web module
	 */
	public AddModuleCommand(IServerWorkingCopy server, IModule module) {
		super(Messages.configurationEditorActionAddWebModule);
		this.server = server;
		this.module = module;
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			server.modifyModules(new IModule[] { module }, null, monitor);
		} catch (Exception e) {
			// ignore
		}
		return Status.OK_STATUS;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return execute(monitor, info);
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			server.modifyModules(null, new IModule[] { module }, monitor);
		} catch (Exception e) {
			// ignore
		}
		return Status.OK_STATUS;
	}
}
