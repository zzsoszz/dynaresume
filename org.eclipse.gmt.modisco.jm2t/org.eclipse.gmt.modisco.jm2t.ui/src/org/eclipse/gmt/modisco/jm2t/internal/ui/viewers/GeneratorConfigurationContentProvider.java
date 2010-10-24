package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;

public class GeneratorConfigurationContentProvider extends BaseContentProvider {
	
	private List<IGeneratorConfiguration> generatorConfigurations;
	
	/**
	 * GeneratorConfigurationContentProvider constructor comment.
	 */
	public GeneratorConfigurationContentProvider(List<IGeneratorConfiguration> generatorConfigurations) {
		super();
		this.generatorConfigurations =generatorConfigurations;
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
//		List<IGeneratorConfiguration> list = new ArrayList<IGeneratorConfiguration>();
//		IGeneratorConfiguration[] runtimes = null;// ServerCore.getGeneratorConfigurations();
//		if (runtimes != null) {
//			int size = runtimes.length;
//			for (int i = 0; i < size; i++) {
//				list.add(runtimes[i]);
//			}
//		}
//		return list.toArray();
		return generatorConfigurations.toArray(IGeneratorConfiguration.EMPTY);
	}

}
