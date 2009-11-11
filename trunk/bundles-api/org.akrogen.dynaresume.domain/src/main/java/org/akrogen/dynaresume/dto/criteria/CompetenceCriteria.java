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
*******************************************************************************/

package org.akrogen.dynaresume.dto.criteria;

import org.akrogen.dynaresume.dto.criteria.BaseCriteria;


public class CompetenceCriteria extends BaseCriteria {

	private String nomLike;

	public String getNomLike() {
		if (nomLike != null)
			return "%" + nomLike + "%";
		return null;
	}

	public void setNomLike(String nomLike) {
		this.nomLike = nomLike;
	}


	

}
