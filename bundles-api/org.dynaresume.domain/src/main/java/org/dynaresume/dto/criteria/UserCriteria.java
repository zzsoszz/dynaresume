/*****************************************************************************
* Copyright (c) 2009
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Angelo Zerr <angelo.zerr@gmail.com>
*     Pascal Leclercq <pascal.leclercq@gmail.com>
******************************************************************************/

package org.dynaresume.dto.criteria;

import org.dynaresume.dto.criteria.BaseCriteria;


public class UserCriteria extends BaseCriteria {

	private String login;
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
