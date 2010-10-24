package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class AbstractTableListComposite<T> extends AbstractTableComposite {

	private List<T> elements = new ArrayList<T>();

	public AbstractTableListComposite(Composite parent, int style) {
		super(parent, style);
	}

	public List<T> getElements() {
		return elements;
	}

	/**
	 * Adds an element at the end of the list.
	 */
	public boolean addElement(T element) {
		return addElement(element, elements.size());
	}

	/**
	 * Adds an element at a position.
	 */
	public boolean addElement(T element, int index) {
		if (elements.contains(element)) {
			return false;
		}
		elements.add(index, element);
		if (isOkToUse(table)) {
			tableViewer.refresh();
			tableViewer.setSelection(new StructuredSelection(element));
		}

		return true;
	}

	/**
	 * Replaces an element.
	 */
	public void replaceElement(T oldElement, T newElement)
			throws IllegalArgumentException {
		int idx = elements.indexOf(oldElement);
		if (idx != -1) {
			elements.set(idx, newElement);
			if (isOkToUse(table)) {
				List<T> selected = getSelectedElements();
				if (selected.remove(oldElement)) {
					selected.add(newElement);
				}
				tableViewer.refresh();
				selectElements(new StructuredSelection(selected));
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void selectElements(ISelection selection) {
		if (isOkToUse(table)) {
			tableViewer.setSelection(selection, true);
		}
	}

	/**
	 * Returns the selected elements.
	 */
	public List<T> getSelectedElements() {
		List<T> result = new ArrayList<T>();
		if (isOkToUse(table)) {
			ISelection selection = tableViewer.getSelection();
			if (selection instanceof IStructuredSelection) {
				@SuppressWarnings("unchecked")
				Iterator<T> iter = ((IStructuredSelection) selection)
						.iterator();
				while (iter.hasNext()) {
					result.add(iter.next());
				}
			}
		}
		return result;
	}

	/**
	 * Removes elements from the list.
	 */
	public void removeElements(List<T> elements) {
		if (elements.size() > 0) {
			this.elements.removeAll(elements);
			if (isOkToUse(table)) {
				tableViewer.remove(elements.toArray());
			}
		}
	}
	/**
	 * Tests is the control is not <code>null</code> and not disposed.
	 */
	protected final boolean isOkToUse(Control control) {
		return (control != null) && (Display.getCurrent() != null)
				&& !control.isDisposed();
	}

}
