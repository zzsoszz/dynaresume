package org.eclipse.jst.server.jetty.core.command;

import org.eclipse.jst.server.jetty.core.IJettyConfigurationWorkingCopy;
import org.eclipse.jst.server.jetty.core.WebModule;
import org.eclipse.jst.server.jetty.core.internal.Messages;
/**
 * Command to remove a web module.
 */
public class RemoveWebModuleCommand extends ConfigurationCommand {
	protected int index;
	protected WebModule module;

	/**
	 * RemoveWebModuleCommand constructor comment.
	 * 
	 * @param configuration a tomcat configuration
	 * @param index an index
	 */
	public RemoveWebModuleCommand(IJettyConfigurationWorkingCopy configuration, int index) {
		super(configuration, Messages.configurationEditorActionRemoveWebModule);
		this.index = index;
	}

	/**
	 * Execute the command.
	 */
	public void execute() {
		module = (WebModule) configuration.getWebModules().get(index);
		configuration.removeWebModule(index);
	}

	/**
	 * Undo the command.
	 */
	public void undo() {
		configuration.addWebModule(index, module);
	}
}