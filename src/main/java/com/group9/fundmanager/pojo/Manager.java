package com.group9.fundmanager.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "managers")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long managerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Fund> funds;

    public Manager() {
    }

    public Manager(Long managerId, String firstName, String lastName, List<Fund> funds) {
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
    }

    public Long getId() {
        return managerId;
    }
    public void setId(Long managerId) {
        this.managerId = managerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Fund> getFunds() {
        return funds;
    }
    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

}
