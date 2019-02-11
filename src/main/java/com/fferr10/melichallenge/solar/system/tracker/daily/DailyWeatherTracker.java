package com.fferr10.melichallenge.solar.system.tracker.daily;

import com.fferr10.melichallenge.solar.system.Satellite;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.observer.Observer;
import com.fferr10.melichallenge.solar.system.persistence.day.DailyWeather;
import com.fferr10.melichallenge.solar.system.persistence.day.DailyWeatherRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DailyWeatherTracker implements Observer<DailyWeatherResult> {

    private final DailyWeatherRepository dailyWeatherRepository;

    public DailyWeatherTracker(Satellite satellite, DailyWeatherRepository dailyWeatherRepository) {
        satellite.subscribe(this);
        this.dailyWeatherRepository = dailyWeatherRepository;
    }

    @Override
    @Transactional
    public void update(DailyWeatherResult dailyWeatherResult) {
        DailyWeather dailyWeather = new DailyWeather(dailyWeatherResult.getDay(), dailyWeatherResult.getWeatherResult().getWeatherType());

        dailyWeatherRepository.save(dailyWeather);
    }
}
