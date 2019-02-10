package com.fferr10.melichallenge.solar.system.persistence.period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MostRainyDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer day;
    private Double intensity;

    public MostRainyDay() {
    }

    public MostRainyDay(Integer day, Double intensity) {
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

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double Integerensity) {
        this.intensity = Integerensity;
    }
}
