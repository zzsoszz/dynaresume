package org.eclipse.jst.server.jetty.core.internal;

import org.eclipse.core.resources.IFolder;

public interface IJettyConfigurationFactory {

	IJettyConfiguration createJettyConfiguration(IFolder path);
}
