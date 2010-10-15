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
package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.internal.ui.JM2TUI;
import org.eclipse.gmt.modisco.jm2t.internal.ui.Messages;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Generator configurations composite to display
 * 
 */
public class GeneratorConfigurationsComposite extends AbstractTableComposite {

	protected IGeneratorConfiguration selection;
	protected IGeneratorConfiguration defaultGeneratorLaunchConfiguration;
	protected GeneratorLaunchConfigurationSelectionListener listener;

	public interface GeneratorLaunchConfigurationSelectionListener {
		public void generatorConfigurationSelected(
				IGeneratorConfiguration generatorConfiguration);
	}

	class GeneratorLaunchConfigurationViewerSorter extends ViewerSorter {
		boolean sortByName;

		public GeneratorLaunchConfigurationViewerSorter(boolean sortByName) {
			this.sortByName = sortByName;
		}

		public int compare(final Viewer viewer, final Object e1, final Object e2) {
			IGeneratorConfiguration r1 = (IGeneratorConfiguration) e1;
			IGeneratorConfiguration r2 = (IGeneratorConfiguration) e2;
			if (sortByName)
				return getComparator().compare(notNull(r1.getName()),
						notNull(r2.getName()));

			return getComparator().compare(notNull(r1.getName()),
					notNull(r2.getName()));
			// if (r1.getGeneratorLaunchConfigurationType() == null)
			// return -1;
			// if (r2.getGeneratorLaunchConfigurationType() == null)
			// return 1;
			// return
			// getComparator().compare(notNull(r1.getGeneratorLaunchConfigurationType().getName()),
			// notNull(r2.getGeneratorLaunchConfigurationType().getName()));
		}

		protected String notNull(String s) {
			if (s == null)
				return "";
			return s;
		}
	}

	public GeneratorConfigurationsComposite(final IJM2TProject project,
			final Composite parent, final int style,
			final GeneratorLaunchConfigurationSelectionListener listener) {
		super(parent, style);
		this.listener = listener;

		TableLayout tableLayout = new TableLayout();
		table.setLayout(tableLayout);
		table.setHeaderVisible(true);

		tableLayout.addColumnData(new ColumnWeightData(0, 160, true));
		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText(Messages.columnName);
		col.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableViewer
						.setSorter(new GeneratorLaunchConfigurationViewerSorter(
								true));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		tableLayout.addColumnData(new ColumnWeightData(0, 125, true));
		col = new TableColumn(table, SWT.NONE);
		col.setText(Messages.columnType);
		col.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableViewer
						.setSorter(new GeneratorLaunchConfigurationViewerSorter(
								false));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		tableViewer
				.setContentProvider(new GeneratorConfigurationsContentProvider(
						project));

		ILabelProvider labelProvider = new GeneratorConfigurationTableLabelProvider();
		labelProvider.addListener(new ILabelProviderListener() {
			public void labelProviderChanged(LabelProviderChangedEvent event) {
				Object[] obj = event.getElements();
				if (obj == null)
					tableViewer.refresh(true);
				else {
					obj = JM2TUI.adaptLabelChangeObjects(obj);
					int size = obj.length;
					for (int i = 0; i < size; i++)
						tableViewer.refresh(obj[i], true);
				}
			}
		});
		tableViewer.setLabelProvider(labelProvider);

		tableViewer.setInput("root");
		tableViewer.setColumnProperties(new String[] { "name", "type" });
		tableViewer
				.setSorter(new GeneratorLaunchConfigurationViewerSorter(true));

		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						Object obj = getSelection(event.getSelection());
						if (obj instanceof IGeneratorConfiguration)
							selection = (IGeneratorConfiguration) obj;
						else
							selection = null;
						listener.generatorConfigurationSelected(selection);
					}
				});

		// table.addKeyListener(new KeyListener() {
		// public void keyPressed(KeyEvent e) {
		// if (e.character == 'l') {
		// try {
		// IGeneratorLaunchConfiguration runtime =
		// getSelectedGeneratorLaunchConfiguration();
		// IGeneratorLaunchConfigurationWorkingCopy wc =
		// runtime.createWorkingCopy();
		// wc.setReadOnly(!runtime.isReadOnly());
		// wc.save(false, null);
		// refresh(runtime);
		// } catch (Exception ex) {
		// // ignore
		// }
		// }
		// }
		//
		// public void keyReleased(KeyEvent e) {
		// // do nothing
		// }
		// });

		// after adding an item do the packing of the table
		if (table.getItemCount() > 0) {
			TableColumn[] columns = table.getColumns();
			for (int i = 0; i < columns.length; i++)
				columns[i].pack();
			table.pack();
		}
	}

	protected void createTable() {
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.SINGLE);
	}

	protected void createTableViewer() {
		tableViewer = new LockedTableViewer(table);
	}

	public IGeneratorConfiguration getSelectedGeneratorLaunchConfiguration() {
		return selection;
	}
}
