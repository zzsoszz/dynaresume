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

import java.util.Collection;

import org.dynaresume.domain.User;
import org.dynaresume.services.UserService;
import org.eclipse.persistence.internal.weaving.PersistenceWeaved;

public class FindAllUsersThread extends Thread {

	private static final long TIMER = 5000;

	// private ServiceTracker userServiceTracker;
	//
	// public FindAllUsersThread(ServiceTracker userServiceTracker) {
	// this.userServiceTracker = userServiceTracker;
	// }

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run() {
		while (!super.isInterrupted()) {
			try {
				// 1. Get UserService
				UserService userService = getUserService();
				if (userService != null) {
					// 2. Display users by using UserServive
					displayUsers(userService);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				try {
					if (!super.isInterrupted())
						sleep(TIMER);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	private UserService getUserService() {
		System.out.println("--- Get UserService from OSGi Spring context ---");
		// UserService userService = (UserService)
		// userServiceTracker.getService();
		if (userService != null) {
			return userService;
		}
		System.out.println(" Cannot get UserService=> UserService is null!");
		return null;
	}

	private void displayUsers(UserService userService) {
		Collection<User> users = userService.findAllUsers();

		for (User user : users) {
			boolean woven = (user instanceof PersistenceWeaved);
			System.out.println("User [login=" + user.getLogin() + ", password="
					+ user.getPassword() + "], woven=" + woven);
		}
	}

	@Override
	public synchronized void start() {
		System.out.println("Start Thread FindAllUsersThread");
		super.start();
	}

	@Override
	public void interrupt() {
		System.out.println("Interrupt Thread FindAllUsersThread");
		super.interrupt();
	}
}
