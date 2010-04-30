/*******************************************************************************
 * Copyright (c) 2010
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
package org.dynaresume.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dynaresume.dao.UserDAO;
import org.dynaresume.domain.User;

public class UserDAOJpa implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Collection<User> findAllUsers() {
		Query query = entityManager.createQuery("select u from "
				+ User.class.getSimpleName() + " u");
		return query.getResultList();
	}

	public User saveUser(User user) {
		entityManager.persist(user);
		return user;
	}

}