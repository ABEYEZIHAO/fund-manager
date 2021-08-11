package com.dennis.springboot.pojo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Securities")
public class Securities
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private long s_id;

    @Column(name = "symbol")
    private String symbol;


    public long s_id() {
        return s_id;
    }
    public void setS_id(long s_id) {
        this.s_id = s_id;
    }
    public long getS_id() {
        return s_id;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
