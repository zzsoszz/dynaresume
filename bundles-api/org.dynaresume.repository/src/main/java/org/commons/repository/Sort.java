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

package org.commons.repository;

/**
 * @author azerr
 * @deprecated replace this by something more resonable...
 */
public class Sort {

	private String[] columns;
	private boolean ascending;

	public Sort(String column) {
		this(column, true);
	}
	
	public Sort(String column, boolean ascending) {
		this.columns = new String[1];
		this.columns[0] = column;
		this.ascending = ascending;
	}
	
	public Sort(String[] columns, boolean ascending) {
		this.columns = columns;
		this.ascending = ascending;
	}
	
	
	public String getStringOrderBy(String alias) {
		if (this.columns != null && this.columns.length > 0) {
			StringBuffer orderBy = new StringBuffer(" order by ");
			for (int i = 0; i < columns.length; i++) {
				if (i > 0)
					orderBy.append(",");
				orderBy.append(alias+ columns[i]);	
			}		
			if (!ascending)
				orderBy.append(" desc ");
			orderBy.append(" ");
			return orderBy.toString();
		}
		return "";
	}
	
	public String getOrderColumn() {
		if (this.columns != null && this.columns.length == 1) {
			return columns[0];
		}
		return "";
	}
}
