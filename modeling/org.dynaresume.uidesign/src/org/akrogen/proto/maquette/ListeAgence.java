package org.akrogen.proto.maquette;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.layout.FillLayout;

public class ListeAgence extends ViewPart {

	public static final String ID = "org.akrogen.proto.maquette.ListeAgence"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	public ListeAgence() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			Composite composite = new Composite(container, SWT.NONE);
			toolkit.adapt(composite);
			toolkit.paintBordersFor(composite);
			composite.setLayout(new TreeColumnLayout());
			{
				TreeViewer treeViewer = new TreeViewer(composite, SWT.BORDER);
				treeViewer.setColumnProperties(new String[] {"Nom agence"});
				Tree agences = treeViewer.getTree();
				agences.setHeaderVisible(true);
				agences.setLinesVisible(true);
				toolkit.paintBordersFor(agences);
			}
		}

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
