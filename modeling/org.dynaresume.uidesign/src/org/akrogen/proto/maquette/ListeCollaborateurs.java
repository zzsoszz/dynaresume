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

public class ListeCollaborateurs extends ViewPart {

	public static final String ID = "org.akrogen.proto.maquette.ListeCollaborateurs"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	public ListeCollaborateurs() {
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
					TableColumn nomTC = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(nomTC, new ColumnPixelData(150, true, true));
					nomTC.setText("Nom");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn prenomTC = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(prenomTC, new ColumnPixelData(150, true, true));
					prenomTC.setText("Prenom");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn villeTC = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(villeTC, new ColumnPixelData(150, true, true));
					villeTC.setText("Ville");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					TableColumn emailTC = tableViewerColumn.getColumn();
					tableColumnLayout.setColumnData(emailTC, new ColumnPixelData(150, true, true));
					emailTC.setText("E-mail");
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
