package org.dynaresume.core.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.wizards.IWizardDescriptor;

public class NewAgency extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWizardDescriptor desc= PlatformUI.getWorkbench().getNewWizardRegistry().findWizard("org.dynaresume.common.simpleclient.agencywizard");
	try {
	IWorkbenchWizard wiz=	desc.createWizard();
	wiz.init(PlatformUI.getWorkbench(), (IStructuredSelection)HandlerUtil.getActiveMenuSelection(event));
	WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShellChecked(event), wiz);
    dialog.create();
    dialog.getShell().setSize(
            Math.max(500, dialog.getShell().getSize().x),
            500);
//    activeWorkbenchWindow.getWorkbench().getHelpSystem().setHelp(
//			dialog.getShell(), IWorkbenchHelpContextIds.NEW_WIZARD);
    dialog.open();
	} catch (CoreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//HandlerUtil.getActivePartChecked(event).getSite().get
		return null;
	}

}
