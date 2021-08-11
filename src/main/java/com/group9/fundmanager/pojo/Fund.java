package com.group9.fundmanager.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.jar.Manifest;

@Entity
@Table(name = "funds")
public class Fund {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long fundId;

	@Column(name = "name")
	private String fundName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Manager manager;

	@OneToMany
	@JoinColumn(name = "p_id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Position> positions;

	public Fund() {
	}

	public Fund(Long fundId, String fundName, Manager manager, List<Position> positions) {
		this.fundId = fundId;
		this.fundName = fundName;
		this.manager = manager;
		this.positions = positions;
	}

	public Long getFundId() {
		return fundId;
	}

	public void setFundId(Long fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
}