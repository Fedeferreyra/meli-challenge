package com.fferr10.melichallenge.solar.system.controller.dto;

public class DailyWeatherDTO {
    private Integer dia;
    private String clima;

    public DailyWeatherDTO(Integer dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public Integer getDia() {
        return dia;
    }

    public String getClima() {
        return clima;
    }
}
