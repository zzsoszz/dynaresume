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

package org.dynaresume;

import java.util.Locale;


public class GestcvUser
//extends UserDTO 
{

	private Locale userLocale = null;
	
	private String login;
	
	private String nom;
//	public GestcvUser(UserDTO userDTO) {
//		super(userDTO);	
//	}
	
	/**
	 * 
	 * @return
	 */
	public Locale getUserLocale() {
		if (userLocale != null)
			return userLocale;
//		if (getLanguage() != null) {
//			userLocale = new Locale(getLanguage());
//		}				
		return userLocale;
	} 
	
	/**
	 * 
	 * @param userLocale
	 */
	public void setUserLocale(Locale userLocale) {
		this.userLocale = userLocale;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}	
}
