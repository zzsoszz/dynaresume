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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dynaresume.domain.Agence;
import org.dynaresume.domain.BaseBean;

@Entity
@Table(name="T_MODELERTF")
public class ModeleRTF extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3019152333372853352L;
	@Id
	@Column(name="MDL_ID_N")
	private Integer id;
	@Column(name="MDL_NOM_C",nullable=false,length=50)
	private String nomModele;
	@Column(name="MDL_VERSION_C",length=20)
	private String versionModele;
	@Column(name="MDL_CHEMIN_FICHIER_C",length=100)
	private String cheminFichier;
	@ManyToOne(optional=false)
	@JoinColumn(name="MDL_ID_AGENCE_N")
	private Agence agence;
	
	public String getNomModele() {
		return nomModele;
	}
	public void setNomModele(String nomModele) {
		this.nomModele = nomModele;
	}
	public String getVersionModele() {
		return versionModele;
	}
	public void setVersionModele(String versionModele) {
		this.versionModele = versionModele;
	}
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCheminFichier() {
		return cheminFichier;
	}
	public void setCheminFichier(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}
	
	
}
