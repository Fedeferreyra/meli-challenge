package com.fferr10.melichallenge.solar.system.controller;

import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriodRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/period")
public class WeatherPeriodController {

    private final WeatherPeriodRepository weatherPeriodRepository;

    public WeatherPeriodController(WeatherPeriodRepository weatherPeriodRepository) {
        this.weatherPeriodRepository = weatherPeriodRepository;
    }

    @GetMapping
    public List<WeatherPeriod> getAll(){
        return weatherPeriodRepository.findAll();
    }
}
