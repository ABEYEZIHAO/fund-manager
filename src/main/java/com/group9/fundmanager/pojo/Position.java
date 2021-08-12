package com.group9.fundmanager.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "security_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Security security;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private LocalDate date;

    public Position() {
    }

    public Position(Long id, Security security, int quantity, LocalDate date) {
        this.id = id;
        this.security = security;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getPositionId() {
        return id;
    }

    public void setPositionId(Long id) {
        this.id = id;
    }

    public Long getSecurityId() {
        return security.getId();
    }

    public void setSecurityId(Long securityId) {
        this.security.setId(securityId);
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
