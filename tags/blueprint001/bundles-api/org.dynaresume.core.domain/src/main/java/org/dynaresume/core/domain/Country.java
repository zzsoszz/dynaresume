/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_COUNTRY", schema = "core")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1896189210454791831L;
	@Id
	private String iso3;

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {

		this.iso3 = iso3;

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {

		this.label = label;

	}

	@Column
	private String label;
}
