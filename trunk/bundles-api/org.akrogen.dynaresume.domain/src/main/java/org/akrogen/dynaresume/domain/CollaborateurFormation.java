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
package org.akrogen.dynaresume.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.akrogen.dynaresume.domain.Rubrique;
@Entity
@Table(name="T_COLLABORATEUR_FORMATION")
public class CollaborateurFormation extends Rubrique {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6948016469995384089L;
	@Column(name="FRM_ORGANISME_C",length=100)
	private String organisme;
	
	@Column(name="FRM_DIPLOME_C",length=100)
	private String diplome;
	@Column(name="FRM_DATE_DIPLOME_D",length=23)
	private Date dateDiplome;
	@Column(name="FRM_LIEU_C",length=100)
	private String lieu;
	
	public Date getDateDiplome() {
		return dateDiplome;
	}
	public void setDateDiplome(Date dateDiplome) {
		this.dateDiplome = dateDiplome;
	}
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getOrganisme() {
		return organisme;
	}
	public void setOrganisme(String organisme) {
		this.organisme = organisme;
	}	
}
