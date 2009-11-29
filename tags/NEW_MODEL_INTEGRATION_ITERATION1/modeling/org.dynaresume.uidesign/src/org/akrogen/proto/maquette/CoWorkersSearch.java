package org.akrogen.proto.maquette;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class CoWorkersSearch extends ViewPart {

	public static final String ID = "org.akrogen.proto.maquette.CoWorkersSearch"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text;
	private Text text_1;
	private Text text_2;

	public CoWorkersSearch() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		container.setLayout(gridLayout);
		{
			Label lblAgence = new Label(container, SWT.NONE);
			lblAgence.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			toolkit.adapt(lblAgence, true, true);
			lblAgence.setText("Agence :");
		}
		{
			Combo combo = new Combo(container, SWT.NONE);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			toolkit.adapt(combo);
			toolkit.paintBordersFor(combo);
		}
		{
			Label lblRtfModel = new Label(container, SWT.NONE);
			lblRtfModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			toolkit.adapt(lblRtfModel, true, true);
			lblRtfModel.setText("RTF Model :");
		}
		{
			Combo combo = new Combo(container, SWT.NONE);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			toolkit.adapt(combo);
			toolkit.paintBordersFor(combo);
		}
		{
			Label lblPrnom = new Label(container, SWT.NONE);
			lblPrnom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			toolkit.adapt(lblPrnom, true, true);
			lblPrnom.setText("Prénom :");
		}
		{
			text = new Text(container, SWT.BORDER);
			text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			toolkit.adapt(text, true, true);
		}
		{
			Label lblNom = new Label(container, SWT.NONE);
			lblNom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			toolkit.adapt(lblNom, true, true);
			lblNom.setText("Nom :");
		}
		{
			text_1 = new Text(container, SWT.BORDER);
			text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			toolkit.adapt(text_1, true, true);
		}
		{
			Label lblCompetenz = new Label(container, SWT.NONE);
			lblCompetenz.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			toolkit.adapt(lblCompetenz, true, true);
			lblCompetenz.setText("Competenz :");
		}
		{
			text_2 = new Text(container, SWT.BORDER);
			text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			toolkit.adapt(text_2, true, true);
		}
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

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
