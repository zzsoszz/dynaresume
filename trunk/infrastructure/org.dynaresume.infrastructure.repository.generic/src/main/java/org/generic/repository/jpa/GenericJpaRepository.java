package org.generic.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.generic.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Required;

/**
 * JPA implementation of the GenericRepository. Note that this implementation
 * also expects Hibernate as JPA implementation. That's because we like the
 * Criteria API.
 * 
 * @author Jurgen Lust
 * @author $LastChangedBy: jlust $
 * 
 * @version $LastChangedRevision: 257 $
 * 
 * @param <T>
 *            The persistent type
 * @param <ID>
 *            The primary key type
 */
public class GenericJpaRepository<T, ID extends Serializable> implements
		GenericRepository<T, ID> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(GenericJpaRepository.class);

	// ~ Instance fields
	// --------------------------------------------------------

	private final Class<T> persistentClass;
	private EntityManager entityManager;

	// ~ Constructors
	// -----------------------------------------------------------

	@SuppressWarnings("unchecked")
	public GenericJpaRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public GenericJpaRepository(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	// ~ Methods
	// ----------------------------------------------------------------

//	/**
//	 * @see be.bzbit.framework.domain.repository.GenericRepository#countAll()
//	 */
//	
//	public int countAll() {
//		return countByCriteria();
//	}
//
//	/**
//	 * @see be.bzbit.framework.domain.repository.GenericRepository#countByExample(java.lang.Object)
//	 */
//	
//	public int countByExample(final T exampleInstance) {
//		Session session = (Session) getEntityManager().getDelegate();
//		Criteria crit = session.createCriteria(getEntityClass());
//		crit.setProjection(Projections.rowCount());
//		crit.add(Example.create(exampleInstance));
//
//		return (Integer) crit.list().get(0);
//	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findAll()
	 */
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		logger.debug("findAll() - start"); //$NON-NLS-1$
		
		
			Query q=getEntityManager().createQuery("from "+persistentClass.getSimpleName());
			

		List<T> returnList = q.getResultList();
		logger.debug("findAll() - end"); //$NON-NLS-1$
			return returnList;
			
		
	}

//	/**
//	 * @see be.bzbit.framework.domain.repository.GenericRepository#findByExample(java.lang.Object)
//	 */
//	@SuppressWarnings("unchecked")
//	public List<T> findByExample(final T exampleInstance) {
//		Session session = (Session) getEntityManager().getDelegate();
//		Criteria crit = session.createCriteria(getEntityClass());
//		final List<T> result = crit.list();  
//		return result; 
//	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#findById(java.io.Serializable)
	 */
	public T findById(final ID id) {
		logger.debug("findById(ID) - start"); //$NON-NLS-1$

		final T result = getEntityManager().find(persistentClass, id);

		logger.debug("findById(ID) - end"); //$NON-NLS-1$
		return result;
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository
         * #findByNamedQuery(java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(final String name, Object... params) {
		logger.debug("findByNamedQuery(String, Object) - start"); //$NON-NLS-1$

		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		final List<T> result = (List<T>) query.getResultList();

		logger.debug("findByNamedQuery(String, Object) - end"); //$NON-NLS-1$
		return result;
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository
         * #findByNamedQueryAndNamedParams(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ? extends Object> params) {
		logger.debug("findByNamedQueryAndNamedParams(String, Map<String,? extends Object>) - start"); //$NON-NLS-1$

		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<T> result = (List<T>) query.getResultList();

		logger.debug("findByNamedQueryAndNamedParams(String, Map<String,? extends Object>) - end"); //$NON-NLS-1$
		return result;
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#getEntityClass()
	 */
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	/**
	 * set the JPA entity manager to use.
	 *
	 * @param entityManager
	 */
	@Required
	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

//	/**
//	 * Use this inside subclasses as a convenience method.
//	 */
//	protected List<T> findByCriteria(final Criterion... criterion) {
//		return findByCriteria(-1, -1, criterion);
//	}
//
//	private Session getSession()
//	{
//		
//		return (Session)getEntityManager().getDelegate();
//		//getEntityManager().getDelegate()
//	}
//	/**
//	 * Use this inside subclasses as a convenience method.
//	 */
//	@SuppressWarnings("unchecked")
//	protected List<T> findByCriteria(final int firstResult,
//			final int maxResults, final Criterion... criterion) {
//		Session session = (Session) getEntityManager().getDelegate();
//		Criteria crit = session.createCriteria(getEntityClass());
//
//		for (final Criterion c : criterion) {
//			crit.add(c);
//		}
//
//		if (firstResult > 0) {
//			crit.setFirstResult(firstResult);
//		}
//
//		if (maxResults > 0) {
//			crit.setMaxResults(maxResults);
//		}
//
//		final List<T> result = crit.list();
//		return result;
//	}
//
//	protected int countByCriteria(Criterion... criterion) {
//		Session session = (Session) getEntityManager().getDelegate();
//		Criteria crit = session.createCriteria(getEntityClass());
//		crit.setProjection(Projections.rowCount());
//
//		for (final Criterion c : criterion) {
//			crit.add(c);
//		}
//
//		return (Integer) crit.list().get(0);
//	}


	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository#delete(java.lang.Object)
	 */
	public void delete(T entity) {
		logger.debug("delete(T) - start"); //$NON-NLS-1$

		getEntityManager().remove(entity);

		logger.debug("delete(T) - end"); //$NON-NLS-1$
	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository
         * #save(java.lang.Object)
	 */
	public T save(T entity) {
		logger.debug("save(T) - start"); //$NON-NLS-1$

		final T savedEntity = getEntityManager().merge(entity);

		logger.debug("save(T) - end"); //$NON-NLS-1$
		return savedEntity;
	}
}