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
	private Long id;

	@Column(name = "name")
	private String name;

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

	public Fund(Long id, String name, Manager manager, List<Position> positions) {
		this.id = id;
		this.name = name;
		this.manager = manager;
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