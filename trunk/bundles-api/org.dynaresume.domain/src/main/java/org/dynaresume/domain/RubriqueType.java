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

package org.dynaresume.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.domain.BaseBean;

/**
 * Type de rubrique : (Formation, Expérience, Competence,...).
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 */
@Entity
@Table(name = "T_RUBRIQUE_TYPE")
public class RubriqueType extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7538981403284229425L;
	@Id
	@Column(name = "RBT_ID_N")
	private Integer id;
	@Column(name = "RBT_NOM_C",length=50,nullable=false)
	private String nom;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
