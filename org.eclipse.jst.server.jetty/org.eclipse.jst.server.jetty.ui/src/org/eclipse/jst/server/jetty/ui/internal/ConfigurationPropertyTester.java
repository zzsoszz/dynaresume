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