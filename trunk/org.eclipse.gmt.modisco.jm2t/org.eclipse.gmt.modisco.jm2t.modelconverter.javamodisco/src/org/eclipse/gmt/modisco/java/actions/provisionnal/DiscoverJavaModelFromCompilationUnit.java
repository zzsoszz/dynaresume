/*******************************************************************************
 * Copyright (c) 2010 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.java.actions.provisionnal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.infra.discoverymanager.Discoverer;
import org.eclipse.gmt.modisco.infra.discoverymanager.DiscoveryParameter;
import org.eclipse.gmt.modisco.java.IModelReader;
import org.eclipse.gmt.modisco.java.JavaActivator;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.actions.DefaultDiscoverer;
import org.eclipse.gmt.modisco.java.io.java.JavaReader;
import org.eclipse.gmt.modisco.jm2t.modelconverter.internal.javamodisco.JavaModiscoPlugin;
import org.eclipse.gmt.modisco.jm2t.modelconverter.internal.javamodisco.Messages;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;

/**
 * Discover Java model action from a JDT {@link ICompilationUnit}.
 * 
 * This class sould be belongs to the org.eclipse.gmt.modisco.java.discoverer
 * Plug-Ins.
 */
public class DiscoverJavaModelFromCompilationUnit extends DefaultDiscoverer {

	private static List<DiscoveryParameter> parametersKeys = null;

	/**
	 * @see Discoverer#isApplicableTo
	 */
	public final boolean isApplicableTo(final Object source) {
		boolean result = false;
		if (source instanceof ICompilationUnit) {
			result = true;
		}
		return result;
	}

	/**
	 * Discovers a Java model.
	 * 
	 * @param source
	 *            a {@link IJavaProject} instance
	 * @param parameters
	 *            <ul>
	 *            <li>SILENT_MODE : IN parameter; if not set or set to
	 *            <code>false</code>, the discovered model will be opened in an
	 *            editor.
	 *            <li>TARGET_RESOURCE : OUT parameter; the discovered model is
	 *            stored with this key.
	 *            </ul>
	 */
	public void discoverElement(final Object source,
			final Map<DiscoveryParameter, Object> parameters) {
		setResourceResult(null);

		final ICompilationUnit compilationUnit = (ICompilationUnit) source;

		if (compilationUnit.getJavaProject() == null) {
			return;
		}

		final IPath path = compilationUnit.getJavaProject().getProject()
				.getLocation().addTrailingSeparator()
				.append(compilationUnit.getElementName())
				.addFileExtension("javaxmi"); //$NON-NLS-1$

		// discovering
		Job job = new Job(
				Messages.DiscoverJavaModelFromCompilationUnit_jobLabel) {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				IStatus result = null;
				monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
				try {
					URI sourceURI = URI.createFileURI(path.toString());
					Resource resource = getResource("", compilationUnit //$NON-NLS-1$
							.getJavaProject());

					Model model = getEFactory().createModel();
					resource.getContents().add(model);
					IModelReader reader = getCompilationUnitReader();
					reader.readModel(compilationUnit, model, monitor);

					if (monitor.isCanceled()) {
						result = Status.CANCEL_STATUS;
						return result;
					}
					reader.terminate(monitor);
					saveResource(sourceURI, path, resource, monitor);

					if (monitor.isCanceled()) {
						result = Status.CANCEL_STATUS;
						return result;
					}
					result = Status.OK_STATUS;
					DiscoverJavaModelFromCompilationUnit.this
							.setResourceResult(resource);
				} catch (Exception e) {
					result = Status.CANCEL_STATUS;
					IStatus status = new Status(IStatus.ERROR,
							JavaActivator.PLUGIN_ID, "Unkown error.", e); //$NON-NLS-1$
					//JavaActivator.getDefault().getLog().log(status);
					JavaModiscoPlugin.getDefault().getLog().log(status);
				} finally {
					monitor.done();
					try {
						// refresh the folder
						compilationUnit
								.getJavaProject()
								.getCorrespondingResource()
								.refreshLocal(IResource.DEPTH_ONE,
										new NullProgressMonitor());
						if (monitor.isCanceled()) {
							// if monitor is canceled, delete the model file
							IWorkspaceRoot root = ResourcesPlugin
									.getWorkspace().getRoot();
							IFile ifile = root.getFileForLocation(path);
							if (ifile.exists()) {
								ifile.delete(true, new NullProgressMonitor());
							}
						}
					} catch (Exception e1) {
						IStatus status = new Status(IStatus.ERROR,
								JavaActivator.PLUGIN_ID, e1.getMessage(), e1);
						JavaModiscoPlugin.getDefault().getLog().log(status);
						//JavaActivator.getDefault().getLog().log(status);
					}
				}
				return result;
			}
		};
		scheduleEditorOpening(ResourcesPlugin.getWorkspace().getRoot()
				.getFileForLocation(path), job, parameters);

		job.schedule();
		try {
			job.join();
		} catch (InterruptedException e) {
			IStatus status = new Status(IStatus.ERROR, JavaActivator.PLUGIN_ID,
					e.getMessage(), e);
			//JavaActivator.getDefault().getLog().log(status);
			JavaModiscoPlugin.getDefault().getLog().log(status);
		}

		if (parameters != null) {
			parameters.put(PARAMETER_TARGET_RESOURCE, getResourceResult());
		}
	}

	protected IModelReader getCompilationUnitReader() {
		return new JavaReader(getEFactory());
	}

	@Override
	public String toString() {
		return Messages.DiscoverJavaModelFromCompilationUnit_title;
	}

	public List<DiscoveryParameter> getDiscovererParameters() {
		if (DiscoverJavaModelFromCompilationUnit.parametersKeys == null) {
			DiscoverJavaModelFromCompilationUnit.parametersKeys = new ArrayList<DiscoveryParameter>();
			DiscoverJavaModelFromCompilationUnit.parametersKeys
					.add(PARAMETER_SILENT_MODE);
			DiscoverJavaModelFromCompilationUnit.parametersKeys
					.add(PARAMETER_TARGET_RESOURCE);
		}
		return DiscoverJavaModelFromCompilationUnit.parametersKeys;
	}
}
