package com.group9.fundmanager.pojo;

import javax.persistence.*;
import java.sql.ClientInfoStatus;
import java.util.List;

@Entity
@Table(name = "funds")
public class Fund {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "m_id")
	private Long managerId;

	@Column(name = "positions")
	private List<Long> positions;

	public Fund() {
	}

	public Fund(Long id, String name, Long managerId, List<Long> positions) {
		this.id = id;
		this.name = name;
		this.managerId = managerId;
		this.positions = positions;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {

		this.name = name;
	}

	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public List<Long> getPositions() {
		return positions;
	}
	public void setPositions(List<Long> positions) {
		this.positions = positions;
	}
}