/*******************************************************************************
 * Copyright (c) 2010 Angelo Zerr and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - Initial API and implementation 
 *******************************************************************************/
package org.eclipse.jst.server.jetty.core.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerPort;

public abstract class JettyConfiguration implements IJettyConfiguration {

	public static final String REMOVE_WEB_MODULE_PROPERTY = "removeWebModule";
	
	/**
	 * Return the port number.
	 * 
	 * @return int
	 */
	public ServerPort getMainPort() {
		Collection<ServerPort> serverPorts = getServerPorts();
		for (ServerPort serverPort : serverPorts) {
			// Return only an HTTP port from the selected Service
			if (serverPort.getProtocol().toLowerCase().equals("http")
					&& serverPort.getId().indexOf('/') < 0)
				return serverPort;
		}
		return null;
	}

	/**
	 * Returns the partial URL applicable to this module.
	 * 
	 * @param webModule
	 *            a web module
	 * @return the partial URL
	 */
	public String getWebModuleURL(IModule webModule) {
		WebModule module = getWebModule(webModule);
		if (module != null)
			return module.getPath();

		IWebModule webModule2 = (IWebModule) webModule.loadAdapter(
				IWebModule.class, null);
		return "/" + webModule2.getContextRoot();
	}

	/**
	 * Returns the given module from the config.
	 * 
	 * @param module
	 *            a web module
	 * @return a web module
	 */
	public WebModule getWebModule(IModule module) {
		if (module == null)
			return null;

		String memento = module.getId();

		List<WebModule> modules = getWebModules();
		int size = modules.size();
		for (int i = 0; i < size; i++) {
			WebModule webModule = modules.get(i);
			if (memento.equals(webModule.getMemento())) {
				return webModule;
			}
		}
		return null;
	}
	
	/**
	 * Returns the prefix that is used in front of the
	 * web module path property. (e.g. "webapps")
	 *
	 * @return java.lang.String
	 */
	public String getDocBasePrefix() {
		return "";
	}
}
