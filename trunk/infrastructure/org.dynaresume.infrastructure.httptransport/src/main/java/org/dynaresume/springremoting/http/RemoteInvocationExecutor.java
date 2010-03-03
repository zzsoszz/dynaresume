/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.springremoting.http;

import java.lang.reflect.InvocationTargetException;

import net.sf.beanlib.hibernate.HibernateBeanReplicator;
import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;
import net.sf.beanlib.hibernate3.LazyHibernateCustomBeanTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.support.DefaultRemoteInvocationExecutor;
import org.springframework.remoting.support.RemoteInvocation;

public class RemoteInvocationExecutor extends DefaultRemoteInvocationExecutor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(RemoteInvocationExecutor.class);
	
	private final HibernateBeanReplicator replicator = new Hibernate3BeanReplicator().initCustomTransformerFactory(new LazyHibernateCustomBeanTransformer.Factory());;
//	private final HibernateBeanReplicator replicator = new Hibernate3BeanReplicator().initCustomTransformerFactory(new Hibernate3JavaBeanReplicator());
	

	@Override
	public Object invoke(RemoteInvocation invocation, Object targetObject)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
//		Hibernate3JavaBeanReplicator.Factory f= Hibernate3JavaBeanReplicator.getFactory();
//		Hibernate3BeanReplicator replicator = new Hibernate3BeanReplicator();
//		CustomBeanTransformerSpi toto;
		//replicator.initCustomTransformerFactory(new )
		logger.debug("invoke(RemoteInvocation, Object) - start"); //$NON-NLS-1$

//		System.out.println(invocation);
		Object fromBean =null;
		try{
		 fromBean = super.invoke(invocation, targetObject);
		}catch (Throwable t){
			logger.error("invoke(RemoteInvocation, Object)", t); //$NON-NLS-1$

			
		}
		
		Object toBean = replicator.copy(fromBean);

		logger.debug("invoke(RemoteInvocation, Object) - end"); //$NON-NLS-1$
		return toBean;
	}
}
