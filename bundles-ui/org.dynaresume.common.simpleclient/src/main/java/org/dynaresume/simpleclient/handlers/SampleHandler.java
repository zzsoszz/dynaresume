package org.dynaresume.simpleclient.handlers;

import org.dynaresume.common.service.AgenceService;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
@Component(value = "sampleHandler")
@Scope("prototype")
public class SampleHandler extends AbstractHandler {
	@Autowired
	private AgenceService agenceService;

	
	

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		System.out.println(agenceService);
		MessageDialog.openInformation(window.getShell(), "Simpleclient",
				"Hello, Eclipse world " + agenceService.getAllCountries());
		return null;
	}
}
