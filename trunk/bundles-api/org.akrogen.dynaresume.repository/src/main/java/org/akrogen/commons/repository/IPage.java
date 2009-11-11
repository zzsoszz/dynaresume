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

package org.akrogen.commons.repository;

import java.util.List;

public interface IPage {

	public final static int DEFAULT_PAGE_SIZE = 10;
	
	public boolean isFirstPage();

    public boolean isLastPage();

    public boolean hasNextPage();

    public  boolean hasPreviousPage();

    public  int getLastPageNumber();

    public  List getThisPageElements();
    
    public void setThisPageElements(List thisPageElements);

    public  int getTotalNumberOfElements();

	  int getThisPageFirstElementNumber();

	  int getThisPageLastElementNumber();

	  int getNextPageNumber();

	  int getPreviousPageNumber();

	  int getPageSize();

	  int getPageNumber();
}
