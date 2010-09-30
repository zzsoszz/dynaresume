package org.eclipse.jst.server.jetty.core.internal.jetty80;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jst.server.jetty.core.IJettyConfiguration;
import org.eclipse.jst.server.jetty.core.internal.IJettyVersionHandler;
import org.eclipse.jst.server.jetty.core.internal.IJettyVersionProvider;
import org.eclipse.jst.server.jetty.core.internal.jetty70.Jetty70Configuration;

public class Jetty80Provider implements IJettyVersionProvider {

	public static final IJettyVersionProvider INSTANCE = new Jetty80Provider();

	private IJettyVersionHandler versionHandler = new Jetty80Handler();

	public IJettyVersionHandler getJettyVersionHandler() {
		return versionHandler;
	}

	public IJettyConfiguration createJettyConfiguration(IFolder path) {
		return new Jetty70Configuration(path);
	}
}
