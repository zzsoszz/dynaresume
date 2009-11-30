package org.dynaresume.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_COUNTRY",schema="common")
public class Country extends BaseBean{

	
	@Id
	private String iso3;
	@Column
	private String label;
}
