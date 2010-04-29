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
package org.dynaresume.services; 
 
import java.util.Collection; 

import org.dynaresume.domain.User;
 
public interface UserService { 
 
  Collection<User> findAllUsers(); 
  
  User createUser(String login, String password);
}