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

package org.akrogen.dynaresume.repository.hibernate;

import java.util.List;

import org.akrogen.dynaresume.domain.TableReference;
import org.akrogen.dynaresume.dto.criteria.BaseCriteria;
import org.akrogen.dynaresume.dto.criteria.TableReferenceCriteria;
import org.akrogen.dynaresume.repository.ITableReferenceDAO;
import org.springframework.stereotype.Repository;
/**
 * 
 * Description : TableReference Hibernate DAO.
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Repository("tableReferenceDAO")
public class TableReferenceHibernateDAO extends AbstractDynaResumeDAO implements ITableReferenceDAO {

	protected String getHQLQuery(BaseCriteria c) {
		TableReferenceCriteria criteria = (TableReferenceCriteria)c;
//		 Prepare HQL Query
		StringBuffer query = new StringBuffer("");
		query.append("from TableReference");
		if (criteria != null) {
			String login = criteria.getType();
			// WHERE
			query.append(" where 1=1 ");	
			if (login != null)
				query.append(" and type = :type ");
									
		}
		return query.toString();
	}
	
	public List findAll() {
		// TODO Auto-generated method stub
		return super.findAll(TableReference.class);
	}

}
