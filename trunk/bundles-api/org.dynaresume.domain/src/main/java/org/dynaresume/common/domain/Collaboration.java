package org.dynaresume.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.dynaresume.basebean.BaseBean;
@Entity
@Table(name = "T_COLLABORATION",schema="common")
public class Collaboration extends BaseBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	  private long id;
//	  @Id
//	  private long projectId;
	
	  @Column
	private Date start;
	  @Column
	private Date end;
	
	

	  
	  @ManyToOne
	  @PrimaryKeyJoinColumn(referencedColumnName="ID")
	  private NaturalPerson employee;
	  @ManyToOne
	  @PrimaryKeyJoinColumn( referencedColumnName="ID")
	  private Agency agency;
	
}
