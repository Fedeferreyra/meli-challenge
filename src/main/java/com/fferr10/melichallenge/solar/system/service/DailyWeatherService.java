package com.fferr10.melichallenge.solar.system.service;

import com.fferr10.melichallenge.solar.system.controller.dto.DailyWeatherDTO;
import com.fferr10.melichallenge.solar.system.persistence.day.DailyWeather;
import com.fferr10.melichallenge.solar.system.persistence.day.DailyWeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class DailyWeatherService {

    private final DailyWeatherRepository dailyWeatherRepository;

    public DailyWeatherService(DailyWeatherRepository dailyWeatherRepository) {
        this.dailyWeatherRepository = dailyWeatherRepository;
    }

    public DailyWeatherDTO findByDay(Integer day){
        DailyWeather dailyWeather = dailyWeatherRepository.findByDay(day);

        return new DailyWeatherDTO(dailyWeather.getDay(), dailyWeather.getWeatherType().getSpanishWeather());
    }
}
