package org.eclipse.gmt.modisco.jm2t.generator.acceleo3.internal.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

public class Acceleo3Generator extends AbstractGenerator {

	@Override
	protected void generate(Object model, IPath templatePath,
			IPath targetContainerPath, IGeneratorConfiguration configuration) {
		// Get the EMF Object
		EObject emfModel = getEObject(model);

		System.out.println("Acceleo3");
		System.out.println(model);

	}

	private EObject getEObject(Object model) {
		if (model instanceof EObject) {
			return (EObject) model;
		}
		if (model instanceof Resource) {
			return ((Resource) model).getContents().get(0);
		}
		return null;
	}

}
