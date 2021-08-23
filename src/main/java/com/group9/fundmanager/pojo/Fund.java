package com.group9.fundmanager.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tinsley
 */
@Entity
@Table(name = "funds")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Fund implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(optional = false)
	@JsonBackReference
	@JoinColumn(name = "manager_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Manager manager;

	@OneToMany
	@JsonManagedReference
	@JoinColumn(name = "position_id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Position> positions;

	public Fund() {
	}

	public Fund(String fundName, Manager manager, List<Position> positions) {
		this.name = fundName;
		this.manager = manager;
		this.positions = positions;
	}

	public Fund(Long fundId, String fundName, Manager manager, List<Position> positions) {
		this.id = fundId;
		this.name = fundName;
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