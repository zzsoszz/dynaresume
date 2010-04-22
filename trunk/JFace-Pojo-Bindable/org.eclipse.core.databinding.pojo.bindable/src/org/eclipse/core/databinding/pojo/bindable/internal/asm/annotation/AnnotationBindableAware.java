/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.ClassBindable;

/**
 * Interface which support {@link Bindable} annotation (see
 * {@link ClassBindable} and {@link SetterMethodBindable}).
 */
public interface AnnotationBindableAware {

	/**
	 * Set the value of the property 'value' of the {@link Bindable} annotation.
	 * 
	 * @param bindableValue
	 */
	void setBindableAnnotationValue(boolean bindableValue);

	/**
	 * Set the value of the property 'dependsOn' of the
	 * {@link Bindable} annotation.
	 * 
	 * @param dependsOn
	 */
	void setBindableAnnotationDependsOn(String[] dependsOn);
	
	/**
	 * Set the value of the property 'fireEvents' of the
	 * {@link Bindable} annotation.
	 * 
	 * @param dependsOn
	 */
	void setBindableAnnotationFireEvents(String[] fireEvents);
}
