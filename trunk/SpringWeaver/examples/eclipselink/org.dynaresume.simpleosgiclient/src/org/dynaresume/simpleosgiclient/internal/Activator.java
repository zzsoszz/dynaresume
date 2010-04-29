/*******************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *******************************************************************************/
package org.dynaresume.simpleosgiclient.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	// private ServiceTracker userServiceTracker = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	// private FindAllUsersThread findAllUsersThread = null;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Bundle ["
				+ context.getBundle().getSymbolicName() + "]");

		// // Create and open the MovieFinder ServiceTracker
		// userServiceTracker = new ServiceTracker(context, UserService.class
		// .getName(), null);
		// userServiceTracker.open();
		//
		// // Start Thread which call UserService#findAllUsers();
		// findAllUsersThread = new FindAllUsersThread(userServiceTracker);
		// findAllUsersThread.start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Bundle ["
				+ context.getBundle().getSymbolicName() + "]");

		// Stop Thread which call UserService#findAllUsers();
		// findAllUsersThread.interrupt();
		// findAllUsersThread = null;
		//
		// // Close the MovieFinder ServiceTracker
		// userServiceTracker.close();

	}
}