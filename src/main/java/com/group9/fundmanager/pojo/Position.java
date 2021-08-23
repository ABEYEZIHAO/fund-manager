package com.group9.fundmanager.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Tinsley
 */
@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "security_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Security security;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Fund fund;

    public Position() {
    }

    public Position(Security security, int quantity, LocalDate date, Fund fund) {
        this.security = security;
        this.quantity = quantity;
        this.date = date;
        this.fund = fund;
    }

    public Position(Long id, Security security, int quantity, LocalDate date, Fund fund) {
        this.id = id;
        this.security = security;
        this.quantity = quantity;
        this.date = date;
        this.fund = fund;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
