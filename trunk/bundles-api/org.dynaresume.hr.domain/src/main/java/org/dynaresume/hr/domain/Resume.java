package org.dynaresume.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;

@Entity
@Table(name = "T_RESUME",schema="HR")
public class Resume extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407392831377640438L;


	@Id
	private Integer id;

	
	@Column
	private String title;
	
	@Column
	private byte[] picture;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		Object oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		Object oldValue = this.title;
		this.title = title;
		firePropertyChange("title", oldValue, title);
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		Object oldValue = this.picture;
		this.picture = picture;
		firePropertyChange("picture", oldValue, picture);
	}
	
	
}
