package org.eclipse.jst.server.jetty.ui.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jst.server.jetty.core.IJettyServer;
import org.eclipse.wst.server.core.IServerAttributes;

/**
 * 
 */
public class ConfigurationPropertyTester extends PropertyTester {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
	 * java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		try {
			IServerAttributes server = (IServerAttributes) receiver;
			IJettyServer jettyServer = (IJettyServer) server.loadAdapter(
					IJettyServer.class, null);
			if (jettyServer != null)
				return jettyServer.getServerConfiguration() != null;
		} catch (Exception e) {
			// ignore
		}
		return false;
	}
}