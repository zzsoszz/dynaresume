package org.eclipse.jst.server.jetty.core.internal;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jst.server.jetty.core.IJettyConfiguration;

public interface IJettyVersionProvider {

	IJettyVersionHandler getJettyVersionHandler();
	
	IJettyConfiguration createJettyConfiguration(IFolder path);
}
