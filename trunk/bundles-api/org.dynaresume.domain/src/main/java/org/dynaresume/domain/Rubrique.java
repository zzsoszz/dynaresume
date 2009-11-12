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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dynaresume.domain.BaseBean;
import org.dynaresume.domain.RubriqueType;

/**
 * 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "T_RUBRIQUE")
public class Rubrique extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2112733997156619474L;
	@Id
	@Column(name = "RUB_ID_N")
	private Integer id;
	//private Integer idCollaborateur;
	@Column(name = "RUB_DESCRIPTION_C",length=500)
	private String description;
	@ManyToOne(optional=false)
	@JoinColumn(name="RUB_ID_TYPE_N")
	private RubriqueType rubriqueType;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Integer getIdCollaborateur() {
//		return idCollaborateur;
//	}
//	public void setIdCollaborateur(Integer idCollaborateur) {
//		this.idCollaborateur = idCollaborateur;
//	}
	public RubriqueType getRubriqueType() {
		return rubriqueType;
	}
	public void setRubriqueType(RubriqueType rubriqueType) {
		this.rubriqueType = rubriqueType;
	}
	

}
