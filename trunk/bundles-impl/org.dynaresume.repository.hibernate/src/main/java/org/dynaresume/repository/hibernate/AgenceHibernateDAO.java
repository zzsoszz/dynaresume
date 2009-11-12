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

package org.dynaresume.repository.hibernate;

import java.util.List;

import org.dynaresume.repository.hibernate.AbstractDynaResumeDAO;
import org.dynaresume.domain.Agence;
import org.dynaresume.repository.IAgenceDAO;
import org.springframework.stereotype.Repository;

@Repository("agenceDAO")
public class AgenceHibernateDAO<T> extends AbstractDynaResumeDAO<T> implements IAgenceDAO<T> {
	
	
	
	
//	protected String getHQLQuery(BaseCriteria c) {
//		AgenceCriteria criteria = (AgenceCriteria)c;		
//		// Prepare HQL Query
//		StringBuffer query = new StringBuffer("");
//		query.append("from Agence");
//		if (criteria != null) {
//			// WHERE
//			query.append(" where 1=1 ");	
//									
//		}
//		return query.toString();
//	}

	
	public List<T> findAll() {
		
		return super.findAll(Agence.class);
	}

}
