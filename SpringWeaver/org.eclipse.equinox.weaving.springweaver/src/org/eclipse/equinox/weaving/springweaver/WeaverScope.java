/*******************************************************************************
 * Copyright (c) 2009 Martin Lippert and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Martin Lippert                   initial implementation      
 *   Angelo ZERR                      manage springweaver into Jpa context
 *******************************************************************************/
package org.eclipse.equinox.weaving.springweaver;

/**
 * Weaver Scope :
 * 
 * <ul>
 * <li>
 * {@link WeaverScope#APPLICATION} : the weaving is done for the whole classes
 * of each bundles.</li>
 * <li>{@link WeaverScope#BUNDLE} : the weaving is done only for the classes belong
 * to the bundle which declare Spring bean {@link EquinoxAspectsLoadTimeWeaver}.
 * </li>
 * </li>
 * </ul>
 * 
 */
public enum WeaverScope {

	APPLICATION, BUNDLE
}
