package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchange")
public class CurrencyUSD {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date exchangedate;
    private double rate;

    public CurrencyUSD() {

    }

    public CurrencyUSD(Date exchangedate, double rate) {
        this.exchangedate = exchangedate;
        this.rate = rate;
    }

    public Date getExchangedate() {
        return exchangedate;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "CurrencyUSD{" +
                "date=" + exchangedate +
                ", rate=" + rate +
                '}';
    }
}


