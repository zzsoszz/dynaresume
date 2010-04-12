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
package org.eclipse.core.examples.databinding.pojo.bindable.model;

/**
 * POJO model Person.
 *
 */
public class PojoPerson {

	String name = "HelloWorld";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
