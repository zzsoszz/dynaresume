package org.eclipse.gmt.modisco.jm2t.generator.acceleo2.internal.core;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.jm2t.core.generator.AbstractGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.GeneratorException;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

import fr.obeo.acceleo.ecore.factories.FactoryException;
import fr.obeo.acceleo.gen.template.TemplateSyntaxExceptions;
import fr.obeo.acceleo.gen.template.eval.ENodeException;
import fr.obeo.acceleo.gen.template.scripts.IScript;

public class Acceleo2Generator extends AbstractGenerator {

	@Override
	protected void generate(Object model, IPath templatePath,
			IPath targetContainerPath, IGeneratorConfiguration configuration) throws GeneratorException {	
		try {
			// Get the EMF Object
			EObject emfModel = getEObject(model);

			// Get the container where files must be generated
			IContainer targetContainer = super.toContainer(targetContainerPath);
			
			// Create Acceleo 2 Script from template path.
			File templateFile = super.toFile(templatePath);			
			IScript script = Acceleo2Utils.createScript(templateFile);
			
			IProgressMonitor progressMonitor = new NullProgressMonitor();						
			boolean recursive = true;
			Map object2Eval = new HashMap();
			Acceleo2Utils.generate(script, targetContainer, emfModel, recursive,
					object2Eval, progressMonitor);
		} catch (TemplateSyntaxExceptions e) {
			throw new GeneratorException(e);
		} catch (FactoryException e) {
			throw new GeneratorException(e);
		} catch (ENodeException e) {
			throw new GeneratorException(e);
		} catch (CoreException e) {
			throw new GeneratorException(e);
		}
	}
		
	private EObject getEObject(Object model) {
		if (model instanceof EObject) {
			return (EObject)model;
		}
		if (model instanceof Resource) {
			return ((Resource)model).getContents().get(0);
		}
		return null;
	}

}
