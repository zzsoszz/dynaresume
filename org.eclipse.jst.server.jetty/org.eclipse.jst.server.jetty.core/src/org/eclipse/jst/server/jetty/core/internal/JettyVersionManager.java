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

import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.server.jetty.core.internal.jetty70.Jetty70Configuration;
import org.eclipse.jst.server.jetty.core.internal.jetty70.Jetty70Handler;

public class JettyVersionManager {
	
	public static final JettyVersionManager INSTANCE = new JettyVersionManager();

	private Map<String, JettyVersionData> versionDatas = new HashMap<String, JettyVersionData>();

	private List<String> runtimeTypes = new ArrayList<String>();
	
	public enum JettyVersion {
		V70, V80
	}

	private JettyVersionManager() {
		// Jetty 7.0
		register(JettyVersion.V70, new Jetty70Handler(),
				new Jetty70Configuration());
	}

	public void register(JettyVersion version,
			IJettyVersionHandler versionHandler,
			IJettyConfiguration configuration) {
		versionDatas.put(version.name(), new JettyVersionData(
				versionHandler, configuration));
		
		String versionNumber = version.name().substring(1, version.name().length());		
		runtimeTypes.add("org.eclipse.jst.server.jetty.runtime." + versionNumber);
	}

	public IJettyVersionHandler getJettyVersionHandler(String id) {
		String version = getVersion(id);
		JettyVersionData data = versionDatas.get(version);
		if (data == null) {
			throw new JettyVersionHandlerNotFoundException(version);
		}
		return data.versionHandler;
	}

	public IJettyConfiguration getJettyConfiguration(String id) {
		String version = getVersion(id);
		JettyVersionData data = versionDatas.get(version);
		if (data == null) {
			throw new JettyVersionHandlerNotFoundException(version);
		}
		return data.configuration;
	}
	
	private String getVersion(String id) {
		String version = id;
		int index = version.lastIndexOf('.');
		if (index != -1) {
			version = version.substring(index+1, version.length());
		}
		if (!version.startsWith("v")) {
			version = "v" + version;
		}
		version = version.toUpperCase();
		return version;
	}
	
	

	private static class JettyVersionData {
		public final IJettyVersionHandler versionHandler;
		public final IJettyConfiguration configuration;

		public JettyVersionData(IJettyVersionHandler versionHandler,
				IJettyConfiguration configuration) {
			this.versionHandler = versionHandler;
			this.configuration = configuration;
		}
	}

	private static class JettyVersionHandlerNotFoundException extends
			RuntimeException {
		private static final long serialVersionUID = 1L;

		private static final String MESSAGE = "Version Handler not founded with serverType={0}.";

		public JettyVersionHandlerNotFoundException(String serverType) {
			super(format(MESSAGE, serverType));
		}
	}
	
	public Collection<String> getRuntimeTypes() {
		return runtimeTypes;
	}

}
