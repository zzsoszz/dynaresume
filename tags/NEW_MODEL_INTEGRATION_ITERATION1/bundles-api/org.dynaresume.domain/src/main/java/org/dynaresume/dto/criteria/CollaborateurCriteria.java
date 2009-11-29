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

package org.dynaresume.dto.criteria;

import org.dynaresume.dto.criteria.BaseCriteria;


public class CollaborateurCriteria extends BaseCriteria {
	
	private String nom;
	private String prenom;
	private String nomLike;
	private String prenomLike;
	private Integer idNot;
	
	private Integer idAgence;
	private String nomCompetenceLike;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(Integer idAgence) {
		this.idAgence = idAgence;
	}

	public String getNomLike() {
		if (nomLike != null)
			return "%" + nomLike + "%";
		return null;
	}

	public void setNomLike(String nomLike) {
		this.nomLike = nomLike;
	}

	public String getPrenomLike() {
		if (prenomLike != null)
			return "%" + prenomLike + "%";
		return null;
	}

	public void setPrenomLike(String prenomLike) {
		this.prenomLike = prenomLike;
	}

	public String getNomCompetenceLike() {
		if (nomCompetenceLike != null)
			return "%" + nomCompetenceLike + "%";
		return null;
	}

	public void setNomCompetenceLike(String nomCompetenceLike) {
		this.nomCompetenceLike = nomCompetenceLike;
	}

	public Integer getIdNot() {
		return idNot;
	}

	public void setIdNot(Integer idNot) {
		this.idNot = idNot;
	}


	
}
