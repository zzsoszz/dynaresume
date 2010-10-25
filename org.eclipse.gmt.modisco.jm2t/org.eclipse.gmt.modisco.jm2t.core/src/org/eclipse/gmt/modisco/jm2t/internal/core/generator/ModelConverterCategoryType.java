package org.eclipse.gmt.modisco.jm2t.internal.core.generator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterCategoryType;

public class ModelConverterCategoryType implements IModelConverterCategoryType {

	private IConfigurationElement element;

	/**
	 * ModelConverterCategoryType constructor comment.
	 * 
	 * @param element
	 *            a configuration element
	 */
	public ModelConverterCategoryType(IConfigurationElement element) {
		super();
		this.element = element;
	}

	/**
	 * Returns the id of this factory.
	 * 
	 * @return java.lang.String
	 */
	public String getId() {
		try {
			return element.getAttribute("id");
		} catch (Exception e) {
			return null;
		}
	}

	public String getName() {
		try {
			return element.getAttribute("name");
		} catch (Exception e) {
			return null;
		}
	}

	public String getDescription() {
		try {
			return element.getAttribute("description");
		} catch (Exception e) {
			return null;
		}
	}

	public void dispose() {
		element = null;
	}
}
