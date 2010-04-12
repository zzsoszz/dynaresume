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
package org.eclipse.core.databinding.pojo.bindable.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * Bindable interface to set if method are bindable or not. This annotation
 * works only if {@link BindableStrategy#isDebugMode()} equals to true.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bindable {

	boolean value() default true;
}
