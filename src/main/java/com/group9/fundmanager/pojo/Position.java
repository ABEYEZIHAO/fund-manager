package com.group9.fundmanager.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Positions")
public class Positions {

    @p_id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private long p_id;

    @Column(name = "s_id")
    private long s_id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private Date date;

    public long getp_id() {
        return p_id;
    }
    public void setp_id(long p_id) {
        this.p_id = p_id;
    }
    public long gets_id() {
        return s_id;
    }
    public void sets_id(long s_id) {
        this.s_id = s_id;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
