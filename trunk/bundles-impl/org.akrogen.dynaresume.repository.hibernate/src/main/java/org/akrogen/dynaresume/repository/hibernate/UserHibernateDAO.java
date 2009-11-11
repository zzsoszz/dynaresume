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

import org.akrogen.dynaresume.domain.User;
import org.akrogen.dynaresume.dto.criteria.BaseCriteria;
import org.akrogen.dynaresume.dto.criteria.UserCriteria;
import org.akrogen.dynaresume.repository.IUserDAO;
import org.springframework.stereotype.Repository;

/**
 * 
 * Description : User Hibernate DAO.
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Repository("userDAO")
public class UserHibernateDAO extends AbstractDynaResumeDAO implements IUserDAO {

	
	
	protected String getHQLQuery(BaseCriteria c) {
		UserCriteria criteria = (UserCriteria)c;		
		// Prepare HQL Query
		StringBuffer query = new StringBuffer("");
		query.append("from User");
		if (criteria != null) {
			String login = criteria.getLogin();
			// WHERE
			query.append(" where 1=1 ");	
			if (login != null)
				query.append(" and login = :login ");
									
		}
		return query.toString();
	}
	public List findAll() {
		// TODO Auto-generated method stub
		return super.findAll(User.class);
	}
}
