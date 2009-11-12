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

import java.util.Date;

import org.commons.spring.dao.hibernate.AbstractBaseDAO;
import org.dynaresume.domain.BaseBean;
import org.dynaresume.dto.criteria.BaseCriteria;
import org.dynaresume.util.UserUtil;
import org.springframework.stereotype.Repository;

/**
 * 
 * Description : 
 * @version 1.0.0 
 * @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
 *
 */
@Repository
public abstract class AbstractDynaResumeDAO<T> extends AbstractBaseDAO<T> {

	
	public void create(Object o) {
		if (o instanceof BaseBean) {
			BaseBean bo = (BaseBean)o;
			bo.setUtilisateurCreation(UserUtil.getLogin());
			bo.setDateCreation(new Date());
		}
		super.create(o);
	}

	public void update(Object o) {
		if (o instanceof BaseBean) {
			BaseBean bo = (BaseBean)o;
			bo.setUtilisateurModification(UserUtil.getLogin());
			bo.setDateModification(new Date());
		}
		super.update(o);
	}
	
	@Override
	protected String getHQLQuery(BaseCriteria arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
