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
package org.eclipse.core.databinding.pojo.bindable.initializer;

import static org.eclipse.core.databinding.pojo.bindable.internal.util.StringUtils.isEmpty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.ClassBindable;
import org.eclipse.core.runtime.IStatus;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * Abstract Class wich transform (if class must be transformed) to Pojo Bindable
 * Class.
 * 
 * @see {@link BindableAware}.
 * 
 */
public abstract class AbstractBindableClassTransformer implements
		BindableStrategyProvider {

	public byte[] transform(String className, byte[] classfileBuffer) {

		BindableStrategy bindableStrategy = getBindableStrategy();
		boolean debugMode = bindableStrategy.isDebugMode();
		if (bindableStrategy.isBindableClass(className)) {
			if (debugMode) {
				bindableStrategy.log(IStatus.INFO, "Transform class: "
						+ className, null);
			}

			// Catch all runtime exceptions. Somehow runtime errors are not
			// reported on eclipse console if they are not caught.
			try {
				// Class must be transformed.
				// Create ASM Writer
				ClassWriter cw = new ClassWriter(0);
				// Create ClassVisitor wich change bytecode.
				ClassAdapter ca = new ClassBindable(cw, bindableStrategy);

				// Create ASM Reader
				ClassReader cr = new ClassReader(classfileBuffer);
				// Transform it.
				cr.accept(ca, 0);
				// Returns new content of class transformed.
				String genBaseDir = bindableStrategy.getGenBaseDir();
				if (!isEmpty(genBaseDir)) {
					try {
						String outFileName = genBaseDir + className + ".class";

						// Trace
						bindableStrategy.log(IStatus.INFO,
								"Generate transformed class: " + className
										+ " into file: " + outFileName, null);

						// Create file
						File outFile = new File(outFileName);
						outFile.getParentFile().mkdirs();
						FileOutputStream fs = new FileOutputStream(outFile);
						try {
							fs.write(cw.toByteArray());
							fs.close();

							// Trace
							bindableStrategy.log(IStatus.INFO,
									"Transformed class output written to: "
											+ outFileName, null);
						} catch (IOException e) {
							if (debugMode) {
								bindableStrategy.log(IStatus.ERROR,
										"Impossible to generate class transformed "
												+ className, e);
							}
						}

					} catch (FileNotFoundException e) {
						if (debugMode) {
							bindableStrategy.log(IStatus.ERROR,
									"Impossible to generate class transformed "
											+ className, e);
						}
					}
				}
				return cw.toByteArray();
			} catch (Throwable e) {
				if (debugMode) {
					bindableStrategy.log(IStatus.ERROR,
							"Error while transform class " + className, e);
				}
			}
		}
		// No transformation
		if (debugMode) {
			bindableStrategy.log(IStatus.INFO, "NO Transformation for class: "
					+ className, null);
		}
		return null;
	}

}
