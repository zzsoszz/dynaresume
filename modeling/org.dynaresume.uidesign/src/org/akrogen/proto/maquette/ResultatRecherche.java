package org.akrogen.proto.maquette;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ColumnPixelData;

public class ResultatRecherche extends ViewPart {

	public static final String ID = "org.akrogen.proto.maquette.ResultatRecherche"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	public ResultatRecherche() {
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
			TableColumnLayout tableColumnLayout = new TableColumnLayout();
			composite.setLayout(tableColumnLayout);
			{
				TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
				table = tableViewer.getTable();
				table.setHeaderVisible(true);
				table.setLinesVisible(true);
				toolkit.paintBordersFor(table);
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn tblclmnAgence = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(tblclmnAgence, new ColumnPixelData(150, true, true));
					tblclmnAgence.setText("Agence");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn tblclmnNom = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(tblclmnNom, new ColumnPixelData(150, true, true));
					tblclmnNom.setText("Nom");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn tblclmnPrnom = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(tblclmnPrnom, new ColumnPixelData(150, true, true));
					tblclmnPrnom.setText("Prénom :");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn tblclmnProfil = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(tblclmnProfil, new ColumnPixelData(150, true, true));
					tblclmnProfil.setText("Profil :");
				}
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
