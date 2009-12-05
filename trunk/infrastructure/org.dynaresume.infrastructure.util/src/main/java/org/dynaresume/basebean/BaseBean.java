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
package org.dynaresume.basebean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4621270018596086154L;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		// PLQ : do not fire if there's no listener
		if (propertyChangeSupport.hasListeners(propertyName))
			propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {

		propertyChangeSupport.addPropertyChangeListener(listener);

	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {

		propertyChangeSupport.removePropertyChangeListener(listener);

	}

	
//	@Column( length = 50, updatable = false)
//	private String createUser;
//	@Column( updatable = false)
//	private Date createTime;
//	@Column( length = 50)
//	private String lastUpdateUser;
//	@Column
//	private Date lastUpdateTime;

	

	
}
