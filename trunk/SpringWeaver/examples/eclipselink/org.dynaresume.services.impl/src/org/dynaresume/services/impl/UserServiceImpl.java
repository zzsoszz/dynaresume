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
package org.dynaresume.services.impl;

import java.util.Collection;

import org.dynaresume.dao.UserDAO;
import org.dynaresume.domain.User;
import org.dynaresume.services.UserService;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public Collection<User> findAllUsers() {
		return userDAO.findAllUsers();
	}

	@Transactional
	public User createUser(String login, String password) {
		User user = new User(login, password);
		return userDAO.saveUser(user);
	}
}