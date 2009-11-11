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

package org.akrogen.dynaresume.util;

import java.util.Locale;

import org.akrogen.dynaresume.GestcvUser;


public class UserUtil {

private static ThreadLocal thread = new ThreadLocal();
	
	public static void setGestcvUser(GestcvUser gestcvUser) {			
		// Save this safari user in thread local
		thread.set(gestcvUser);		
	}	
	
	public static GestcvUser getGestcvUser() {
		//System.out.println("Hashcode th courant : "+Thread.currentThread().hashCode());
		//System.out.println("Nom : "+Thread.currentThread().getName());
		return (GestcvUser)(thread.get());
	}
	
	public static String getNom() {
		GestcvUser user =  getGestcvUser();
		if (user != null)
			return user.getNom();
		return "";
	}
	
	public static String getLogin() {
		GestcvUser user =  getGestcvUser();
		if (user != null)
			return user.getLogin();
		return "";
	}	
	
	public static Locale getUserLocale() {
		GestcvUser user =  getGestcvUser();
		if (user != null) {
			return user.getUserLocale();
		}
		// ERROR
		return null;
	}
	
	public static void setUserLocale(Locale userLocale) {
		GestcvUser user =  getGestcvUser();
		if (user != null) {
			user.setUserLocale(userLocale);
		}		
	}	
}
