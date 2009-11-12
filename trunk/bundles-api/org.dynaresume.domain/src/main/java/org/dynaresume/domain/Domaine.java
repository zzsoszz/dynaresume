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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dynaresume.domain.BaseBean;
import org.dynaresume.domain.Competence;

/**
 * Domaine de Competence : permet de typer les compétences 
 * (Ex : Langages, Outils, Gestion de Projet...)
 * 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 */
@Entity
@Table(name = "T_DOMAINE")
public class Domaine extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3829290385177724002L;
	@Id
	@Column(name = "DOM_ID_N")
	private Integer id;
	@Column(name="DOM_NOM_C",length=50,nullable=false)
	private String nom;
	@OneToMany(mappedBy="domaine")
	private Set<Competence> competences =new HashSet<Competence>();
	
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
	public Set<Competence> getCompetences() {
		return competences;
	}
	public void setCompetences(Set<Competence> competences) {
		this.competences = competences;
	}

	
	
	
}
