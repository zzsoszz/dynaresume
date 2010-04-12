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
package org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;

@Bindable(value = true)
// All methods must be transformed with firePropertyChange. This annotation is
// not required, it's the default valiue.
public class UserBindable {

	private String name = null;

	private String login = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	@Bindable(value = false)
	// This method must not be transformed with firePropertyChange.
	public void setLogin(String login) {
		this.login = login;
	}

}
