package org.example;

import javax.persistence.Id;
import java.util.Date;


public class CurrencyRate {
    @Id
    private Integer id;
    private Date exchangedate;
    private Integer r030;
    private String cc;
    private String txt;
    private String enname;
    private double rate;
    private int units;
    private double rate_per_unit;
    private String group;
    private Date calcdate;

    public CurrencyRate() {
    }

    public CurrencyRate(Date exchangedate, double rate) {
        this.exchangedate = exchangedate;
        this.rate = rate;
    }

    public Date getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(Date exchangedate) {
        this.exchangedate = exchangedate;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getRate_per_unit() {
        return rate_per_unit;
    }

    public void setRate_per_unit(double rate_per_unit) {
        this.rate_per_unit = rate_per_unit;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getCalcdate() {
        return calcdate;
    }

    public void setCalcdate(Date calcdate) {
        this.calcdate = calcdate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "exchangedate=" + exchangedate +
                ", r030=" + r030 +
                ", cc='" + cc + '\'' +
                ", txt='" + txt + '\'' +
                ", enname='" + enname + '\'' +
                ", rate=" + rate +
                ", units=" + units +
                ", rate_per_unit=" + rate_per_unit +
                ", group='" + group + '\'' +
                ", calcdate=" + calcdate +
                '}';
    }
}
