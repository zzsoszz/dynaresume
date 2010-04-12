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

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

/**
 * An empty {@link AnnotationVisitor AnnotationVisitor} that delegates to
 * another {@link AnnotationVisitor AnnotationVisitor}. This class can be used
 * as a super class to quickly implement usefull annotation adapter classes,
 * just by overriding the necessary methods.
 */
public class AnnotationAdapter implements AnnotationVisitor {

	/**
	 * The {@link MethodVisitor} to which this adapter delegates calls.
	 */
	protected AnnotationVisitor av;

	/**
	 * Constructs a new {@link MethodAdapter} object.
	 * 
	 * @param mv
	 *            the code visitor to which this adapter must delegate calls.
	 */
	public AnnotationAdapter(final AnnotationVisitor av) {
		this.av = av;
	}

	/**
	 * Visits a primitive value of the annotation.
	 * 
	 * @param name
	 *            the value name.
	 * @param value
	 *            the actual value, whose type must be {@link Byte},
	 *            {@link Boolean}, {@link Character}, {@link Short},
	 *            {@link Integer}, {@link Long}, {@link Float}, {@link Double},
	 *            {@link String} or {@link Type}. This value can also be an
	 *            array of byte, boolean, short, char, int, long, float or
	 *            double values (this is equivalent to using {@link #visitArray
	 *            visitArray} and visiting each array element in turn, but is
	 *            more convenient).
	 */
	public void visit(String name, Object value) {
		av.visit(name, value);
	}

	/**
	 * Visits an enumeration value of the annotation.
	 * 
	 * @param name
	 *            the value name.
	 * @param desc
	 *            the class descriptor of the enumeration class.
	 * @param value
	 *            the actual enumeration value.
	 */
	public void visitEnum(String name, String desc, String value) {
		av.visitEnum(name, desc, value);
	}

	/**
	 * Visits a nested annotation value of the annotation.
	 * 
	 * @param name
	 *            the value name.
	 * @param desc
	 *            the class descriptor of the nested annotation class.
	 * @return a visitor to visit the actual nested annotation value, or
	 *         <tt>null</tt> if this visitor is not interested in visiting this
	 *         nested annotation. <i>The nested annotation value must be fully
	 *         visited before calling other methods on this annotation
	 *         visitor</i>.
	 */
	public AnnotationVisitor visitAnnotation(String name, String desc) {
		return av.visitAnnotation(name, desc);
	}

	/**
	 * Visits an array value of the annotation. Note that arrays of primitive
	 * types (such as byte, boolean, short, char, int, long, float or double)
	 * can be passed as value to {@link #visit visit}. This is what
	 * {@link ClassReader} does.
	 * 
	 * @param name
	 *            the value name.
	 * @return a visitor to visit the actual array value elements, or
	 *         <tt>null</tt> if this visitor is not interested in visiting these
	 *         values. The 'name' parameters passed to the methods of this
	 *         visitor are ignored. <i>All the array values must be visited
	 *         before calling other methods on this annotation visitor</i>.
	 */
	public AnnotationVisitor visitArray(String name) {
		return av.visitArray(name);
	}

	/**
	 * Visits the end of the annotation.
	 */
	public void visitEnd() {
		av.visitEnd();
	}
}
