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

package org.akrogen.commons.spring.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.akrogen.commons.repository.IBaseDAO;
import org.akrogen.dynaresume.dto.criteria.BaseCriteria;
import org.springframework.stereotype.Repository;


@Repository
public abstract class AbstractBaseDAO<T>  implements IBaseDAO<T> {
//
//	
//	@Resource
//	protected SessionFactory sessionFactory;

	
	@PersistenceContext
	private EntityManager em;
	
	public Object findByPrimaryKey(Class clazz, Integer id) {
		
		//Session session = getSession();
		return em.find(clazz, id);
		//return em.get( clazz, id);
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
//	protected Session getSession(){
//		
//	//	return sessionFactory.openSession();
//		return sessionFactory.getCurrentSession();
//	}
	
	public List<T> findByCriteria(final BaseCriteria criteria) {
		Query q = getQuery(criteria);
		return q.getResultList();
	}
	
//	public List findByCriteria(final BaseCriteria criteria, final Sort sort) {
//		Query q = getQuery(criteria, sort);
//		return q.list();
//	}	

	/////////// PAGINATION
//	public IPage findPageByCriteria(final BaseCriteria criteria, final Sort sort, int pageNumber) {
//		return findPageByCriteria(criteria, null, pageNumber, IPage.DEFAULT_PAGE_SIZE);
//	}
	

	
	// QUERY HIBERNATE
	private Query getQuery(final BaseCriteria criteria) {
	String query = getHQLQuery(criteria);
//	Session session = getSession();
	//Execute Query
	
	Query q = em.createQuery(query);
	if (criteria != null)
		// Prepare Statement for values of criteria
		//q.setProperties(criteria);
		q.setParameter("TODO","TODO");
	return q;
}


	
	/**
	 * Find All Collection of Business Object BaseBO.
	 * @return Collection of Business Object BaseBO  
	 */	
	public List<T> findAll(Class aClass) {
		Query q=em.createQuery("from "+aClass.getSimpleName());
//		Criteria criteria = getSession().createCriteria(aClass);
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return q.getResultList();
	}
//	public List findAll(final Sort sort) {
//		return findByCriteria(null, sort);
//	}
//	
//	public IPage findAll(final Sort sort, int pageNumber, int pageSize) {
//		return findPageByCriteria(null, sort, pageNumber, pageSize);
//	}
	
	/**
	 * Find First BaseBO of List Returned by method findByCriteria.
	 * ATTENTION : If Business Object contains getter 
	 * with lazy mode, DAO must extends findFirstByCriteria
	 * and call getter in order to force load list lazy mode.  
	 * @param criteria  Specific criteria depends on Business Object
	 * @return BaseBO Business Object or null if list is empty  
	 */
	public T findFirstByCriteria(BaseCriteria criteria) {
		// Get list findByCriteria
		List<T> items = findByCriteria(criteria);
		for (Iterator<T> iter = items.iterator(); iter.hasNext();) {
			return iter.next();	
		}
		return null;
	}	

	


//	protected String getHQLQueryWithOrderBy(BaseCriteria c, Sort sort) {
//		StringBuffer query = new StringBuffer("");
//		query.append(getHQLQuery(c));
//		if (sort != null) {
//			// Le tri doit s'effectuer
//			// Recuperation de l'alias (si il y en a un)
//			// du champs a trier (utilise pour les tris
//			// du type c.agence.nom
//			String alias = getHQLAlias(sort.getOrderColumn());
//			if (alias != null && alias.length() > 0) {
//				if (!alias.endsWith(".")) {
//					// Ajout de "."
//					alias+=".";
//				}
//			}
//			query.append(sort.getStringOrderBy(alias));
//		}
//		return query.toString();
//	}
//	
	
	/**
	 * Method to implement by Hibernate DAO 
	 * This methode must construct HBM Query 
	 * by using criteria <c>. 
	 * @param criteria Specific criteria depends on Business Object 
	 * is opened (By SPRING Mecanism) for execute Query
	 * @return HQL query 
	 */
	protected abstract String getHQLQuery(BaseCriteria c);
//	{
//		//Is this method should be abstract ???
//		throw new UnsupportedOperationException("UnImplemented");
//	}
	
	/**
	 * Methode a redefinir si un alias est utilise dans la requete HQL 
	 * Utilise dans les Tris
	 * @return
	 */
	protected String getHQLAlias(String field) {
		return "";
	}
	
	public void create(Object o) {
		//Session session = getSession();
		//session.save(o);
		em.persist(o);
	}

	public void update(Object o) {
//		Session session = getSession();
//		session.update(o);
		em.persist(o);
	}
}
