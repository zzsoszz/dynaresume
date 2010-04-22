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

import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindableAware;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Method wich defines @Bindable(fireEvents={"property1", "property2"}) must
 * generated method to get old values of property1, property2 and fire events
 * changes for property1, property2.
 * 
 */
public class FireEventsMethodBindable extends AdviceAdapter implements Opcodes,
		BindableSignatureConstants, AnnotationBindableAware {

	private static final String BINDABLE_BEFORE_FIRE_EVENTS_METHOD_SUFFIX = "_bindable_beforeFireEvents_";
	private static final String BINDABLE_AFTER_FIRE_EVENTS_METHOD_SUFFIX = "_bindable_afterFireEvents_";

	// Owvner class bindable
	private ClassBindable classBindable;

	// Method name
	private String methodName;

	// Bindable value of property 'dependsOn' of Bindable annotation.
	private String[] dependsOn = null;

	// true if method must be transformed and false otherwise.
	private boolean bindableAnnotationValue = true;

	// Bindable value of property 'dependsOn' of Bindable annotation.
	private String[] fireEvents = null;

	public FireEventsMethodBindable(ClassBindable classBindable,
			MethodVisitor mv, int access, String methodName, String desc) {
		super(mv, access, methodName, desc);

		this.methodName = methodName;
		this.classBindable = classBindable;

		if (classBindable.getBindableAnnotationValue() != null) {
			// Initialize the bindable annotation value of the method with
			// Bindable
			// value of the Class.
			setBindableAnnotationValue(classBindable
					.getBindableAnnotationValue());
		}
		if (classBindable.getBindableAnnotationDependsOn() != null) {
			// Initialize the bindable annotation dependsOn of the
			// method with
			// Bindable
			// value of the Class.
			setBindableAnnotationDependsOn(classBindable
					.getBindableAnnotationDependsOn());
		}

	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		ClassBindable classBindable = getClassBindable();
		if (classBindable.getBindableStrategy().isUseAnnotation()) {
			if (B_SIGNATURE.equals(desc)) {
				// It's Bindable annotation, visit it.
				return new AnnotationBindable(
						mv.visitAnnotation(desc, visible), this);
			}
		}
		return super.visitAnnotation(desc, visible);
	}

	@Override
	protected final void onMethodEnter() {
		if (!canTransformToBindableMethod())
			return;

		addCodeOnMethodEnter();

	}

	protected void addCodeOnMethodEnter() {
		if (fireEvents != null) {
			// Call methods wich get the old values of fireEvents
			addBeforeFireEventsMethodsIfNeeded();
		}
	}

	@Override
	protected final void onMethodExit(int opcode) {
		if (!canTransformToBindableMethod())
			return;
		/*
		 * Setter methods return nothing, so we have to cater only if the opcode
		 * value is RETURN We do not fire property change if there is any
		 * exception
		 */
		if (opcode == RETURN) {
			addCodeOnMethodExit();
		}

	}

	// ----------------- @Bindable#dependsOn

	protected void addCodeOnMethodExit() {
		if (fireEvents != null) {
			// Call methods wich fire events for each fireEvents
			addAfterFireEventsMethodsIfNeeded();
		}
	}

	/**
	 * Return name of the method which get old values of fireEvents
	 * "_bindable_beforeFireEvents_<methodName>".
	 * 
	 * @return
	 */
	public String getBeforeFireEventsMethodName() {
		return BINDABLE_BEFORE_FIRE_EVENTS_METHOD_SUFFIX + getMethodName();
	}

	/**
	 * Return name of the method which fire event for fireEvents
	 * "_bindable_afterFireEvents_<methodName>".
	 * 
	 * @return
	 */
	public String getAfterFireEventsMethodName() {
		return BINDABLE_AFTER_FIRE_EVENTS_METHOD_SUFFIX + getMethodName();
	}

	/**
	 * Call the method "_bindable_beforeFireEvents_<methodName>" to get old
	 * values of fireEvents. This method will be generated into
	 * {@link ClassBindable#addBeforeFireEvents
	 */
	private void addBeforeFireEventsMethodsIfNeeded() {
		ClassBindable classBindable = getClassBindable();
		if (!classBindable.isFireEventsSupported())
			return;

		// Add this method bindable to owner class bindable
		// to generate dependsOn method bindable.
		classBindable.addFireEventsMethodBindable(this);

		// Generate _bindable_beforeFireEvents_<methodName>();
		// ex for setValue method, it will generate
		// public void setValue(String value) {
		// _bindable_beforeFireEvents_setValue(); ...
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, classBindable.getClassName(),
				getBeforeFireEventsMethodName(), "()V");

	}

	/**
	 * Call the method "_bindable_afterFireEvents_<methodName>" to fire event
	 * for each fireEvents. Methods is used to get values because here we cannot
	 * know the Type of the fireEvents because method can be not parsed. The
	 * method "_bindable_afterFireEvents_<methodName>" will be generated into
	 * {@link ClassBindable#addAfterFireEventsMethod(SetterMethodBindable)}.
	 */
	private void addAfterFireEventsMethodsIfNeeded() {
		ClassBindable classBindable = getClassBindable();
		if (!classBindable.isFireEventsSupported())
			return;

		// Generate _bindable_afterFireEvents_<methodName>();
		// ex for setValue method, it will generate
		// public void setValue(String value) {
		// _bindable_beforeFireEvents_setValue(); ...
		// ...
		// _bindable_afterDependsOn_setValue(); ...
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, classBindable.getClassName(),
				getAfterFireEventsMethodName(), "()V");

	}

	/**
	 * Return true if method must be transformed and false otherwise.
	 * 
	 * @return
	 */
	protected boolean canTransformToBindableMethod() {
		return isBindableAnnotationValue() && fireEvents != null;
	}

	/**
	 * 
	 * Set dependsOn array declared into Bindable annotation.
	 */
	public void setBindableAnnotationDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;
	}

	/**
	 * Returns dependsOn array declared into Bindable annotation.
	 * 
	 * @return
	 */
	public String[] getBindableAnnotationDependsOn() {
		return dependsOn;
	}

	/**
	 * 
	 * Set value declared into Bindable annotation.
	 */
	public void setBindableAnnotationValue(boolean bindableAnnotationValue) {
		this.bindableAnnotationValue = bindableAnnotationValue;
	}

	/**
	 * 
	 * Returns value declared into Bindable annotation.
	 * 
	 * @return
	 */
	public boolean isBindableAnnotationValue() {
		return bindableAnnotationValue;
	}

	/**
	 * 
	 * Set fireEvents array declared into Bindable annotation.
	 */
	public void setBindableAnnotationFireEvents(String[] fireEvents) {
		this.fireEvents = fireEvents;
	}

	/**
	 * Returns fireEvents array declared into Bindable annotation.
	 * 
	 * @return
	 */
	public String[] getBindableAnnotationFireEvents() {
		return fireEvents;
	}

	/**
	 * Return owner Class Bindable.
	 * 
	 * @return
	 */
	public ClassBindable getClassBindable() {
		return classBindable;
	}

	/**
	 * Return method name.
	 * 
	 * @return
	 */
	public String getMethodName() {
		return methodName;
	}
}
