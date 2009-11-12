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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dynaresume.domain.Competence;
import org.dynaresume.domain.Rubrique;

@Entity

@Table(name = "T_COLLABORATEUR_COMPETENCE")
public class CollaborateurCompetence extends Rubrique {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9145604507372360450L;
	@Column(name = "CCO_NIVEAU_N",length=10)
	private Integer niveau;
	@ManyToOne(optional=false)
	@JoinColumn(name="CCO_ID_COMPETENCE_N")
	private Competence competence;
	public Competence getCompetence() {
		return competence;
	}
	public void setCompetence(Competence competence) {
		this.competence = competence;
	}
	public Integer getNiveau() {
		return niveau;
	}
	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}
	
	
}
