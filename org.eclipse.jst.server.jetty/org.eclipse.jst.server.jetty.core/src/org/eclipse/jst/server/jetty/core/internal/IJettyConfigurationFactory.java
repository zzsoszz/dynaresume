package org.eclipse.jst.server.jetty.core.internal;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jst.server.jetty.core.IJettyConfiguration;

public interface IJettyConfigurationFactory {

	IJettyConfiguration createJettyConfiguration(IFolder path);
}
