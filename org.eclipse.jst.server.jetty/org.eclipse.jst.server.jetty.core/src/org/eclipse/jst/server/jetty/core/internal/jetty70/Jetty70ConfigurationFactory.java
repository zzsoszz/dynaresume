package org.eclipse.jst.server.jetty.core.internal.jetty70;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jst.server.jetty.core.internal.IJettyConfiguration;
import org.eclipse.jst.server.jetty.core.internal.IJettyConfigurationFactory;

public class Jetty70ConfigurationFactory implements IJettyConfigurationFactory {

	public static final IJettyConfigurationFactory INSTANCE = new Jetty70ConfigurationFactory();
	
	public IJettyConfiguration createJettyConfiguration(IFolder path) {
		return new Jetty70Configuration(path);
	}

}
