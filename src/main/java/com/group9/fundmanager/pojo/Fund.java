<<<<<<< HEAD
package com.group9.fundmanager.pojo;

import javax.persistence.*;
=======
mport javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
>>>>>>> 1bf7ef9c790e85c6a0b57150123dbe165961dc88
import java.sql.ClientInfoStatus;
import java.util.List;

@Entity
@Table(name = "funds")
public class Fund {

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
	private int id;
    
    @Column(name = "name")
>>>>>>> 1bf7ef9c790e85c6a0b57150123dbe165961dc88
	private String name;

	@Column(name = "m_id")
	private long m_id;

	@Column(name = "positions")
	private List<Long> positions;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {

		this.name = name;
	}

	public long getM_id() {
		return m_id;
	}
	public void setM_id(long m_id) {
		this.m_id = m_id;
	}

	public List<Long> getPositions() {
		return positions;
	}
	public void setPositions(List<Long> positions) {
		this.positions = positions;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 1bf7ef9c790e85c6a0b57150123dbe165961dc88
