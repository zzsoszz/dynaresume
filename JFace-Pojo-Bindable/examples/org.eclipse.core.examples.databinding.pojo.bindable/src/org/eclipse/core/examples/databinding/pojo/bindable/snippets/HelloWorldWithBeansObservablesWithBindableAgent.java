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
import org.eclipse.core.examples.databinding.pojo.bindable.model.PojoPerson;
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
 * Hello, databinding. Bind POJO with {@link BeansObservables}. This programm
 * must be called with JVM Parameters :
 * 
 * -javaagent:lib/org.eclipse.core.databinding.beans.bindable_1.0.0.jar
 * -Dbindable
 * .packages=org.eclipse.core.examples.databinding.beans.bindable.model
 * 
 * See launch\HelloWorldWithBeansObservablesWithBindableAgent.launch.
 * 
 * Model change update UI.
 */
public class HelloWorldWithBeansObservablesWithBindableAgent {
	public static void main(String[] args) {
		Display display = new Display();
		final ViewModel viewModel = new ViewModel();

		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
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

	// The View's model--the root of our Model graph for this particular GUI.
	//
	// Typically each View class has a corresponding ViewModel class.
	// The ViewModel is responsible for getting the objects to edit from the
	// DAO. Since this snippet doesn't have any persistent objects to
	// retrieve, this ViewModel just instantiates a model object to edit.
	static class ViewModel {
		// The model to bind
		private PojoPerson person = new PojoPerson();

		public PojoPerson getPerson() {
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
			PojoPerson person = viewModel.getPerson();

			bindingContext.bindValue(SWTObservables.observeText(name,
					SWT.Modify), BeansObservables.observeValue(person, "name"));

			changeModelButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					PojoPerson person = viewModel.getPerson();
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
