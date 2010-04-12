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
package org.eclipse.core.databinding.pojo.bindable.internal.asm;



/**
 * Constants for Object Java Signature used.
 * 
 */
public interface BindableSignatureConstants {

	String PCS_FIELD = "_bindable_propertyChangeSupport";
	String PCS_GETTER = "_bindable_getPropertyChangeSupport";
	String PCS_SHORT_SIGNATURE = "java/beans/PropertyChangeSupport";
	String PCS_SIGNATURE = "L" + PCS_SHORT_SIGNATURE + ";";

	String B_SHORT_SIGNATURE = "org/eclipse/core/databinding/pojo/bindable/annotation/Bindable";
	String B_SIGNATURE = "L" + B_SHORT_SIGNATURE + ";";
	
	String BA_SHORT_SIGNATURE = "org/eclipse/core/databinding/pojo/bindable/BindableAware";
	
	// Signatures for method PropertyChangeSupport#firePropertyChange
	String FPC_DESC_OBJECT = "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V";
	
	// Signature for Boolean, Short... Type
	String BOOLEAN_SHORT_SIGNATURE = "java/lang/Boolean";
	String BOOLEAN_SIGNATURE = "(Z)L" + BOOLEAN_SHORT_SIGNATURE + ";";
	String BYTE_SHORT_SIGNATURE = "java/lang/Byte";
	String BYTE_SIGNATURE = "(B)L" + BYTE_SHORT_SIGNATURE + ";";
	String SHORT_SHORT_SIGNATURE = "java/lang/Short";
	String SHORT_SIGNATURE = "(S)L" + SHORT_SHORT_SIGNATURE + ";";
	String CHARACTER_SHORT_SIGNATURE = "java/lang/Character";
	String CHARACTER_SIGNATURE = "(C)L" + CHARACTER_SHORT_SIGNATURE + ";";
	String INTEGER_SHORT_SIGNATURE = "java/lang/Integer";
	String INTEGER_SIGNATURE = "(I)L" + INTEGER_SHORT_SIGNATURE + ";";
	String LONG_SHORT_SIGNATURE = "java/lang/Long";
	String LONG_SIGNATURE = "(J)L" + LONG_SHORT_SIGNATURE + ";";
	String FLOAT_SHORT_SIGNATURE = "java/lang/Float";
	String FLOAT_SIGNATURE = "(F)L" + FLOAT_SHORT_SIGNATURE + ";";
	String DOUBLE_SHORT_SIGNATURE = "java/lang/Double";
	String DOUBLE_SIGNATURE = "(D)L" + DOUBLE_SHORT_SIGNATURE + ";";
	
	String VALUEOF_METHOD = "valueOf";

}
