package org.eclipse.jst.server.jetty.core.command;

import org.eclipse.jst.server.jetty.core.IJettyConfigurationWorkingCopy;
import org.eclipse.jst.server.jetty.core.WebModule;
import org.eclipse.jst.server.jetty.core.internal.Messages;


/**
 * Command to add a web module.
 */
public class AddWebModuleCommand extends ConfigurationCommand {
	protected WebModule module;
	protected int modules = -1;

	/**
	 * AddWebModuleCommand constructor comment.
	 * 
	 * @param configuration a Jetty configuration
	 * @param module a web module
	 */
	public AddWebModuleCommand(IJettyConfigurationWorkingCopy configuration, WebModule module) {
		super(configuration, Messages.configurationEditorActionAddWebModule);
		this.module = module;
	}

	/**
	 * Execute the command.
	 */
	public void execute() {
		modules = configuration.getWebModules().size();
		configuration.addWebModule(-1, module);
	}

	/**
	 * Undo the command.
	 */
	public void undo() {
		configuration.removeWebModule(modules);
	}
}
