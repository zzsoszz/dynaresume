/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.examples.databinding.pojo.bindable.snippets;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.examples.databinding.pojo.bindable.model.JavaBeanPerson;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Hello, databinding. Bind changes in a GUI to a Model object but don't worry
 * about propogating changes from the Model to the GUI.
 * <p>
 * Illustrates the basic Model-ViewModel-Binding-View architecture typically
 * used in data binding applications.
 */
public class HelloWorldWithBeansObservables {
	public static void main(String[] args) {
		Display display = new Display();
		final ViewModel viewModel = new ViewModel();

		Realm.runWithDefault(SWTObservables.getRealm(display),
				new Runnable() {
					public void run() {
						final Shell shell = new View(viewModel).createShell();
						// The SWT event loop
						Display display = Display.getCurrent();
						while (!shell.isDisposed()) {
							if (!display.readAndDispatch()) {
								display.sleep();
							}
						}
					}
				});
		// Print the results
		System.out.println("person.getName() = "
				+ viewModel.getPerson().getName());
	}

	// The data model class. This is normally a persistent class of some sort.

	// The View's model--the root of our Model graph for this particular GUI.
	//
	// Typically each View class has a corresponding ViewModel class.
	// The ViewModel is responsible for getting the objects to edit from the
	// DAO. Since this snippet doesn't have any persistent objects to
	// retrieve, this ViewModel just instantiates a model object to edit.
	static class ViewModel {
		// The model to bind
		private JavaBeanPerson person = new JavaBeanPerson();

		public JavaBeanPerson getPerson() {
			return person;
		}
	}

	// The GUI view
	static class View {
		private ViewModel viewModel;
		private Text name;

		public View(ViewModel viewModel) {
			this.viewModel = viewModel;
		}

		public Shell createShell() {
			// Build a UI
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			shell.setLayout(new RowLayout(SWT.VERTICAL));
			name = new Text(shell, SWT.BORDER);

			Button changeModelButton = new Button(shell, SWT.BORDER);
			changeModelButton.setText("Change model");
			
			
			// Bind it
			DataBindingContext bindingContext = new DataBindingContext();
			JavaBeanPerson person = viewModel.getPerson();

			bindingContext.bindValue(SWTObservables.observeText(name,
					SWT.Modify), BeansObservables.observeValue(person, "name"));

			changeModelButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {					
					JavaBeanPerson person = viewModel.getPerson();
					person.setName("HelloWorld");
				}
			});
			
			// Open and return the Shell
			shell.pack();
			shell.open();
			return shell;
		}
	}

}
