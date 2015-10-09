package com.foosball.web.bean.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM", schema = "FOOSBALL")
@SequenceGenerator(name="FOOSBALLUSER_SEQUENCE_GENERATOR", sequenceName="SQ_TEAM")
public class TeamBo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator = "FOOSBALLUSER_SEQUENCE_GENERATOR")
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", length = 40)
	private String name;
	
	public TeamBo() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
