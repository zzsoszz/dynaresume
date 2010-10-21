package org.eclipse.gmt.modisco.jm2t.generator.acceleo2.internal.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import fr.obeo.acceleo.ecore.factories.FactoryException;
import fr.obeo.acceleo.gen.template.Template;
import fr.obeo.acceleo.gen.template.TemplateSyntaxExceptions;
import fr.obeo.acceleo.gen.template.eval.ENode;
import fr.obeo.acceleo.gen.template.eval.ENodeException;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;
import fr.obeo.acceleo.gen.template.eval.merge.MergeTools;
import fr.obeo.acceleo.gen.template.scripts.IScript;
import fr.obeo.acceleo.gen.template.scripts.SpecificScript;
import fr.obeo.acceleo.tools.resources.Resources;

public class Acceleo2Utils {

	public static IScript createScript(File file)
			throws TemplateSyntaxExceptions {
		SpecificScript specificGenSettings = new SpecificScript(file, null,
				null);
		specificGenSettings.reset();
		// settings.setScript(specificGenSettings);
		return specificGenSettings;
	}

	public static List generate(IScript script, IContainer targetContainer,
			EObject object, boolean recursive, Map object2Eval,
			IProgressMonitor progressMonitor) throws FactoryException,
			ENodeException, CoreException {
		List result = new ArrayList();
		if (script.isGenerated(object)) {
			IPath path = script.getFilePath(object, true);
			// path != null => Generate file
			if (path != null) {
				StringBuffer report = new StringBuffer(""); //$NON-NLS-1$
				long startTime = System.currentTimeMillis();
				ENode eval = evaluate(script, object,
						LaunchManager.create("run", true), object2Eval); //$NON-NLS-1$
				if (eval.log().hasError()) {
					report.append("->"); //$NON-NLS-1$
					//report.append(AcceleoGenUIMessages.getString("AcceleoSourceEditor.GenerationError", new Object[] { path })); //$NON-NLS-1$
					report.append('\n');
					report.append(eval.log().toString());
					report.append('\n');
				}
				StringBuffer buffer = new StringBuffer(eval.asString());
				StringBuffer oldBuffer = Resources.getFileContent(
						targetContainer.getFile(path), false);
				if (oldBuffer.length() > 0) {
					String lostCode = MergeTools.merge(
							targetContainer.getFile(path), buffer, oldBuffer,
							MergeTools.DEFAULT_USER_BEGIN,
							MergeTools.DEFAULT_USER_END);
					if (lostCode.length() > 0) {
						lostCode = '['
								+ "AcceleoSourceEditor.LostCode" + "] " + new Date().toString() + '\n' + lostCode + '\n'; //$NON-NLS-1$ //$NON-NLS-2$
						Resources
								.appendFile(
										targetContainer,
										path.addFileExtension(MergeTools.LOST_FILE_EXTENSION),
										lostCode, progressMonitor).setDerived(
										true);
					}
				}
				IFile generated = Resources.createFile(targetContainer, path,
						buffer.toString(), progressMonitor);
				long endTime = System.currentTimeMillis();
				// SyncPhantom.createPhantomExtensionPoint(generated,
				// getReflective().getFile(), script.getFile(), targetContainer,
				// null, eval, endTime - startTime, progressMonitor);
				result.add(generated);
				if (report.length() > 0) {
					//AcceleoEcoreGenUiPlugin.getDefault().log(AcceleoGenUIMessages.getString("AcceleoSourceEditor.GenerationReport", new Object[] { path.toString() }) + '\n' + report.toString(), true); //$NON-NLS-1$
				}
			}
		}
		if (recursive)
			result.addAll(generateSub(script, targetContainer, object,
					object2Eval, progressMonitor));
		return result;
	}

	private static List generateSub(IScript script, IContainer targetContainer,
			EObject object, Map object2Eval, IProgressMonitor progressMonitor)
			throws FactoryException, ENodeException, CoreException {
		List result = new ArrayList();
		Iterator contents = object.eContents().iterator();
		while (contents.hasNext()) {
			EObject content = (EObject) contents.next();
			result.addAll(generate(script, targetContainer, content, true,
					object2Eval, progressMonitor));
		}
		return result;
	}

	/**
	 * Generate text for given object.
	 * 
	 * @param evalEObject
	 *            is an object which can be generated
	 * @param mode
	 *            is the mode
	 * @return a node of generation
	 * @throws FactoryException
	 * @throws ENodeException
	 */
	public static ENode evaluate(IScript script, EObject evalEObject,
			LaunchManager mode, Map object2Eval) throws FactoryException,
			ENodeException {
		ENode eval = (ENode) object2Eval.get(evalEObject);
		if (eval == null) {
			Template template = script.getRootTemplate(evalEObject, true);
			if (template != null) {
				boolean withComment = script.isDefault()
						|| !script.hasFileTemplate();
				if (withComment) {
					eval = template.evaluateWithComment(evalEObject, mode);
				} else {
					eval = template.evaluate(evalEObject, mode);
				}
				object2Eval.put(evalEObject, eval);
			} else {
				eval = new ENode(ENode.EMPTY, evalEObject, Template.EMPTY, true);
			}
		}
		return eval;
	}
}
