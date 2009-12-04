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

package org.dynaresume.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * Description : Date Utilitaire. 
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
public class DateUtil {

	/**
	 * Retourne l'age en utilisant la date de naissance.
	 * @param dateNaissance
	 * @return
	 */
	public static Integer getAge(Date dateNaissance) {
		Integer ageToReturn = null;
		if (dateNaissance != null) {
			// Create a calendar object with the date of birth
		    Calendar dateOfBirth = Calendar.getInstance();
		    dateOfBirth.setTime(dateNaissance);
		    
		    // Create a calendar object with today's date
		    Calendar today = Calendar.getInstance();
		    
		    // Get age based on year
		    int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		    
		    // Add the tentative age to the date of birth to get this year's birthday
		    dateOfBirth.add(Calendar.YEAR, age);
		    
		    // If this year's birthday has not happened yet, subtract one from age
		    if (today.before(dateOfBirth)) {
		        age--;
		    }
		    ageToReturn = age;
		}
		return ageToReturn;
	}
	
	/**
	 * Retourne le nombre d'annee par rapport a la date du jour.
	 * @param date
	 * @return
	 */
	public static Integer getYearNumber(Date date) {
		Integer yearNumber = null;
		if (date != null) {
			// Create a calendar object with the date of birth
		    Calendar dateYear = new GregorianCalendar();
		    dateYear.setTime(date);
			
			// Create a calendar object with today's date
		    Calendar today = Calendar.getInstance();
		    int year = today.get(Calendar.YEAR) - dateYear.get(Calendar.YEAR); 
		    yearNumber = year;
		}
		return yearNumber;
	}
	
	/**
	 * retourne l'anne de la date
	 * @param date
	 * @return
	 */
	public static Integer getYear(Date date) {
		Integer yearNumber = null;
		if (date != null) {
			Calendar dateYear = new GregorianCalendar();
		    dateYear.setTime(date);
		    yearNumber = dateYear.get(Calendar.YEAR);
		}
		return yearNumber;
	}
	
}
