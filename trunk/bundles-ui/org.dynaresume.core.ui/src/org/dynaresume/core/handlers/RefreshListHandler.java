package org.dynaresume.core.handlers;

import org.dynaresume.core.View;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

public class RefreshListHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		View view =(View)HandlerUtil.getActivePartChecked(event);
		view.refresh();
		return null;
	}
}
