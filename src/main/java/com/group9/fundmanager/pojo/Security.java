package com.group9.fundmanager.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author abe
 */
@Entity
@Table(name = "securities")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    public Security() {
    }

    public Security(String symbol) {
        this.symbol = symbol;
    }

    public Security(Long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long securityId) {
        this.id = securityId;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
