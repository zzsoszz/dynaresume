package org.dynaresume.common.agency.editor;

import org.dynaresume.core.service.CoreService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component(value = "agencyEditor")
@Scope("prototype")
public class AgencyEditor extends FormEditor {
	@Autowired
	private CoreService coreService;
	
	public static final String ID = "org.dynaresume.common.agency.editor.AgencyEditor";
	//FIXME : handle dirty mode
	private boolean dirty=true;
	@Override
	protected void addPages() {
		AgencyFormPage pages1 = new AgencyFormPage(this,"Agency","Agency");
		try {
			addPage(pages1);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pages1.setAgency(((AgencyEditorInput)getEditorInput()).getAgency());
		//coreService.getAllCountries();
		pages1.setCountries(coreService.getAllCountries());
	}
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site, input);
	}
	
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

}
