package com.fferr10.melichallenge.solar.system.persistence.period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class MostRainyDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer day;
    private BigDecimal intensity;

    public MostRainyDay() {
    }

    public MostRainyDay(Integer day, BigDecimal intensity) {
        this.day = day;
        this.intensity = intensity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public BigDecimal getIntensity() {
        return intensity;
    }

    public void setIntensity(BigDecimal intensity) {
        this.intensity = intensity;
    }
}
