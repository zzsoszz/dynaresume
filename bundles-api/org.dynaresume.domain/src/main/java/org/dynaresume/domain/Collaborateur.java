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

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dynaresume.domain.Agence;
import org.dynaresume.domain.BaseBean;
import org.dynaresume.domain.CollaborateurCompetence;
import org.dynaresume.domain.TableReference;
@Entity
@Table(name = "T_COLLABORATEUR")
public class Collaborateur extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2036594915575699652L;
	@Id
	@Column(name = "COL_ID_N")
	private Integer id;
	@Column(name="COL_NOM_C",length=100,nullable=false)
	private String nom;
	@Column(name="COL_PRENOM_C",length=100,nullable=false)
	private String prenom;
	@Column(name="COL_DATENAISSANCE_D",length=23)
	private Date dateNaissance;
	@Column(name="COL_LIEUNAISSANCE_C",length=100)
	private String lieuNaissance;
	@Column(name="COL_PERMISB_B")
	private Boolean permisB;
	@Column(name="COL_EMAIL_C",length=50)
	private String email;
	@Column(name="COL_DATE_DEBUTCARRIERE_D",length=23)
	private Date dateDebutCarriere;
	@ManyToOne(optional=false)
	@JoinColumn(name="COL_ID_AGENCE_N")
	private Agence agence;
	@Column(name="COL_TITRE_C",length=50)
	private String titre;
	@OneToOne(fetch=FetchType.EAGER) 
	 @JoinColumn(name="COL_ID_SITUATION_FAMILIALE_N")
	private TableReference situationFamiliale; 
	
	// Liste des competences en mode lazy
	@OneToMany
	private Collection<CollaborateurCompetence> collaborateurCompetences;
	
	public Date getDateDebutCarriere() {
		return dateDebutCarriere;
	}
	public void setDateDebutCarriere(Date dateDebutCarriere) {
		this.dateDebutCarriere = dateDebutCarriere;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Boolean getPermisB() {
		return permisB;
	}
	public void setPermisB(Boolean permisB) {
		this.permisB = permisB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
//	public Collection<CollaborateurCompetence> getCollaborateurCompetences() {
//		return collaborateurCompetences;
//	}
//	public void setCollaborateurCompetences(Collection<CollaborateurCompetence> collaborateurCompetences) {
//		this.collaborateurCompetences = collaborateurCompetences;
//	}
	public TableReference getSituationFamiliale() {
		return situationFamiliale;
	}
	public void setSituationFamiliale(TableReference situationFamiliale) {
		this.situationFamiliale = situationFamiliale;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

}
