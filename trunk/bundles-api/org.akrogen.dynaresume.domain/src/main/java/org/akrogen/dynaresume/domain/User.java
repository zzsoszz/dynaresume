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

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.akrogen.dynaresume.domain.Collaborateur;
import org.akrogen.dynaresume.domain.User;



public class User extends Collaborateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6817811856157925233L;
	private String login;
	private String password;
	private Collection roles;
	
	public User() {
		
	}
	
	public User(User user) {
		this.login = user.login;
		this.password = user.password;
		this.setDateCreation(user.getDateCreation());
		this.setDateDebutCarriere(user.getDateDebutCarriere());
		this.setDateModification(user.getDateModification());
		this.setDateNaissance(user.getDateNaissance());
		this.setId(user.getId());
		this.setLieuNaissance(user.getLieuNaissance());
		this.setNom(user.getNom());
		this.setPassword(user.getPassword());
		this.setPermisB(user.getPermisB());
		this.setPrenom(user.getPrenom());
		this.setUtilisateurCreation(user.getUtilisateurCreation());
		this.setUtilisateurModification(user.getUtilisateurModification());
		this.setRoles(user.getRoles());
		this.setAgence(user.getAgence());
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection getRoles() {
		return roles;
	}
	public void setRoles(Collection roles) {
		this.roles = roles;
	}
}
