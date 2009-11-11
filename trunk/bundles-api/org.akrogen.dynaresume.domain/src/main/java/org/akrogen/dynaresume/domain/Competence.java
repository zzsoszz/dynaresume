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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.akrogen.dynaresume.domain.BaseBean;
import org.akrogen.dynaresume.domain.Domaine;

/**
 * Competence : compétences mise à disposition 
 * lors de la saisie d’un CV, ou la recherche d’un CV. 
 * (Ex : JAVA, Chef de Projet…). 
 * Une compétence appartient à un et un seul domaine 
 * de compétence 
 * (Ex : JAVA appartient au domaine de compétence Langages, 
 * Chef de Projet appartient au domaine de compétence Gestion Projet…).
 * 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 */
@Entity
@Table(name = "T_COMPETENCE")
public class Competence extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 214873855665977190L;
	@Id
	@Column(name = "CPT_ID_N")
	private Integer id;
	
	@Column(name="CPT_NOM_C",length=50,nullable=false)
	private String nom;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CPT_ID_DOMAINE_N")
	private Domaine domaine;
	
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

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}
	
	
	

}
