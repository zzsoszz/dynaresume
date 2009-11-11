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

package org.akrogen.commons.exception;

public class CommonsErrorException extends Exception {
	
	public static final long serialVersionUID = 1L;
	
    private String key = null;
	private String args[] = null;

	/**
	 * Constructeur CommonsErrorException avec la cle du message.
	 * @param cle String
	 */
	public CommonsErrorException(String key) {
		this.key = key;
	}

	/**
	 * Constructeur CommonsErrorException avec la cle du message et ses parametres.
	 * @param key String
	 * @param args String[]
	 */
	public CommonsErrorException(String key, String args[]) {
		this.key = key;
		this.args = args;
	}

	/**
	 * Constructeur CommonsErrorException avec la cle du message et un parametre.
	 * @param key String
	 * @param arg0 String
	 */
	public CommonsErrorException(String key, String arg0) {
		this.key = key;
		String[] args = new String[1];
		args[0] = arg0;
		this.args = args;
	}

	/**
	 * Constructeur CommonsErrorException avec la cle du message et 2 parametres.
	 * @param key String
	 * @param arg0 String
	 * @param arg1 String
	 */
	public CommonsErrorException(String key, String arg0, String arg1) {
		this.key = key;
		String[] args = new String[2];
		args[0] = arg0;
		args[1] = arg1;
		this.args = args;
	}

	/**
	 * Constructeur MhsdException avec la cle du message et 3 parametres.
	 * @param key String
	 * @param arg0 String
	 * @param arg1 String
	 * @param arg2 String
	 */
	public CommonsErrorException(String key, String arg0, String arg1, String arg2) {
		this.key = key;
		String[] args = new String[3];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;
		this.args = args;
	}


	/**
	 * Constructeur MhsdException avec la cle du message et 4 parametres.
	 * @param key String
	 * @param arg0 String
	 * @param arg1 String
	 * @param arg2 String
	 * @param arg3 String
	 */
	public CommonsErrorException(String key, String arg0, String arg1, String arg2, String arg3) {
		this.key = key;
		String[] args = new String[4];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;
		args[3] = arg3;
		this.args = args;
	}


	/**
	 * Methode getkey retourne la key du message.
	 * @return String
	 */
	public String getkey()
	{		
		return key;
	}

	/**
	 * Methode getArgs retourne les parametres du message.
	 * @return String[]
	 */
	public String[] getArgs()
	{
		return args;
	}
	
	public String getMessage(){
		return "";//StringUtil.messageFormat(this.key,this.args); 
	}
}
