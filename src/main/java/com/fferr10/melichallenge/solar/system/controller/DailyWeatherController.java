package com.fferr10.melichallenge.solar.system.controller;

import com.fferr10.melichallenge.solar.system.controller.dto.DailyWeatherDTO;
import com.fferr10.melichallenge.solar.system.service.DailyWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clima")
public class DailyWeatherController {

    private final DailyWeatherService dailyWeatherService;

    public DailyWeatherController(DailyWeatherService dailyWeatherService) {
        this.dailyWeatherService = dailyWeatherService;
    }

    @GetMapping
    public DailyWeatherDTO getByDay(@RequestParam("dia") Integer day){
        return dailyWeatherService.findByDay(day);
    }
}
