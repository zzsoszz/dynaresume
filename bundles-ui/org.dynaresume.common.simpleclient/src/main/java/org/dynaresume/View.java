/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume;

import java.util.Collection;

import org.dynaresume.common.agency.editor.AgencyEditorInput;
import org.dynaresume.core.domain.Agency;
import org.dynaresume.core.domain.Group;
import org.dynaresume.core.service.CoreService;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.swtdesigner.ResourceManager;

@Component(value = "myview")
@Scope("prototype")
public class View extends ViewPart {

	public static final String ID = "org.demo.simpleclient.view";

	@Autowired(required = false)
	private CoreService agenceService;

	private TreeViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements ITreeContentProvider {

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			Collection res = (Collection) parent;

			return res.toArray();
		}

		public Object[] getChildren(Object parentElement) {
			Group aGroup = (Group) parentElement;

			return aGroup.getSubsidiaries().toArray();
		}

		public Object getParent(Object element) {
			Agency agency = (Agency) element;
			return agency.getGroup();
		}

		public boolean hasChildren(Object element) {
			// TODO Auto-generated method stub
			if (element instanceof Group) {
				return !((Group) element).getSubsidiaries().isEmpty();
			}
			return false;
		}
	}

	class ViewLabelProvider extends LabelProvider {
		public String getText(Object obj) {
			if (obj instanceof Group) {
				return ((Group) obj).getName();
			}

			Agency entity = (Agency) obj;

			return entity.getName();
		}


		public Image getImage(Object obj) {

			if (obj instanceof Group) {
				return ResourceManager.getPluginImage(
						"org.dynaresume.common.simpleclient",
						"icons/agence_flat_16x16.png");
			}

			// else
			return ResourceManager.getPluginImage(
					"org.dynaresume.common.simpleclient", "icons/branch.png");
		}
	}

	@Override
	public void init(IViewSite site) throws PartInitException {

		super.init(site);

	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());

		// viewer.setInput(agenceService.findAllGroups());
		refresh();
		 viewer.setSorter(new ViewerSorter() {
		 @Override
		 public int compare(Viewer viewer, Object e1, Object e2) {
			 if((e1 instanceof Group) &&  (e2 instanceof Group)){
				 Group group1=(Group)e1;
				 Group group2=(Group)e2;
				 return group1.getName().compareTo(group2.getName());				 
			 }
			 else if((e1 instanceof Agency) &&  (e2 instanceof Agency)){
				 Agency agency1=(Agency)e1;
				 Agency agency2=(Agency)e2;
				 return agency1.getName().compareTo(agency2.getName());				 
			 }
		  
			 return 0;
		 }
		 });

		// context menu creation
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, viewer);
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			public void doubleClick(DoubleClickEvent event) {
				
				IStructuredSelection selection =(IStructuredSelection)event.getSelection();
				Object o=selection.getFirstElement();
				if(o instanceof Agency){
					//FIXME: make a command/handler 
					AgencyEditorInput agencyEditorInput = new AgencyEditorInput((Agency)o);
					try {
						getSite().getWorkbenchWindow().getActivePage().openEditor(agencyEditorInput, "org.dynaresume.common.agency.editor.AgencyEditor");
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void refresh() {
		// XXX Mmmm...
		try {
			viewer.setInput(agenceService.getAllGroups());
		} catch (Exception e) {
			MessageDialog.openError(getSite().getShell(), "Error", e
					.getMessage());
		}

	}
}