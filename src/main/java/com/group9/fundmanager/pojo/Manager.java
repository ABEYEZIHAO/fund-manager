package com.group9.fundmanager.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "managers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Manager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    /**
     * Cannot set [fetch = FetchType.EAGER], otherwise the "delete" function will not work
     *
     * The default type of fetch:
     *  - @OneToMany: FetchType.LAZY
     *  - @ManyToOne: FetchType.EAGER
     */
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Fund> funds;

    public Manager() {
    }

    public Manager(String firstName, String lastName, List<Fund> funds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
        this.fullName = firstName + ' ' + lastName;
    }

    public Manager(Long id, String firstName, String lastName, List<Fund> funds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
        this.fullName = firstName + ' ' + lastName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long managerId) {
        this.id = managerId;
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

    public String getManagerName() {
        return this.fullName;
    }

    public List<Fund> getFunds() {
        return funds;
    }
    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

}
