package org.eclipse.gmt.modisco.jm2t.core.generator;

public interface IModelConverterCategoryType {

	/**
	 * Returns the id of this model converter category type. Each known model
	 * converter category type has a distinct id. Ids are intended to be used
	 * internally as keys; they are not intended to be shown to end users.
	 * 
	 * @return the model converter category type id
	 */
	String getId();

	/**
	 * Returns the displayable name for this model converter category type.
	 * <p>
	 * Note that this name is appropriate for the current locale.
	 * </p>
	 * 
	 * @return a displayable name for this model converter category type
	 */
	String getName();

	/**
	 * Returns the displayable description for this model converter category
	 * type.
	 * <p>
	 * Note that this description is appropriate for the current locale.
	 * </p>
	 * 
	 * @return a displayable description for this model converter category type
	 */
	String getDescription();
}
