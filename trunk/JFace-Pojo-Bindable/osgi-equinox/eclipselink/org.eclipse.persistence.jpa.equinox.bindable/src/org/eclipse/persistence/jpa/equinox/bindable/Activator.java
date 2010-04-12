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
package org.eclipse.persistence.jpa.equinox.bindable;

import org.eclipse.core.databinding.pojo.bindable.initializer.osgi.OSGiBindableInitializer;
import org.eclipse.persistence.jpa.equinox.weaving.IWeaver;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * This activator start/stop {@link EquinoxBindableInitializer} which register
 * into OSGi registry services an instance of {@link EquinoxBindableWeaver}.
 * 
 * <p>
 * {@link EquinoxBindableWeaver} is the service wich transform Pojo Class into
 * Pojo Bindable which implements EclipseLink {@link IWeaver}. This service is
 * consummed into OSGi fragment org.eclipse.persistence.jpa.equinox.weaving
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

	private OSGiBindableInitializer initializer = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		initializer = new EquinoxBindableInitializer(context);
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
