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

import java.util.List;

import org.dynaresume.dto.criteria.BaseCriteria;




public interface IBaseDAO<T> {

	public Object findByPrimaryKey(Class clazz, Integer id);
	
	/**
	 * Find Collection of Business Object BaseBO By Criteria.
	 * @param criteria Specific criteria depends on Business Object
	 * @param criteria Specific criteria depends on Business Object
	 */
	public List<T> findByCriteria(final BaseCriteria criteria);

//	public List findByCriteria(final BaseCriteria criteria, final Sort sort);
			
//	public IPage findPageByCriteria(final BaseCriteria criteria, final Sort sort, int pageNumber, int pageSize);
	
	/**
	 * Find All Collection of Business Object BaseBO.
	 * @return Collection of Business Object BaseBO 
	 */	
	public List<T> findAll();
	
//	public List findAll(final Sort sort);
	
//	public IPage findAll(final Sort sort, int pageNumber, int pageSize);
	

	
	/**
	 * Find First BaseBO of List Find By Criteria 
	 * @param criteria  Specific criteria depends on Business Object
	 * @return BaseBO Business Object or null if list is empty 
	 */
	public T findFirstByCriteria(BaseCriteria criteria);
	
	public void create(T o);
	
	public void update(T o);
}
