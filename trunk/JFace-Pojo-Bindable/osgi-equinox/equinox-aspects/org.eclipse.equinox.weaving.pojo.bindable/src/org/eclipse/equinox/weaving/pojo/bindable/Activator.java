/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.equinox.weaving.pojo.bindable;

import org.eclipse.equinox.service.weaving.IWeavingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * This activator start/stop the Equinox-Aspect {@link WeavingServiceFactory}
 * which register into OSGi registry services this factory.
 * 
 * <p>
 * {@link WeavingService} is the service wich transform Pojo Class into Pojo
 * Bindable which implements Equinox-Aspect {@link IWeavingService}. This
 * service is consummed into OSGi fragment org.eclipse.equinox.weaving.hook
 * linked to org.eclipse.osgi OSGi bundle.
 * </p>
 * 
 * <p>
 * IMPORTANT : this Bundle must be started before all Bundles wich use Pojo
 * Class wich must be transformed into Bindable Pojo.
 * </p>
 * 
 */
public class Activator implements BundleActivator {

	private WeavingServiceFactory initializer = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		initializer = new WeavingServiceFactory(context);
		initializer.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		initializer.stop();
	}

}
