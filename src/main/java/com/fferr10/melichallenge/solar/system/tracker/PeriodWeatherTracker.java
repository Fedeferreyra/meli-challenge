package com.fferr10.melichallenge.solar.system.tracker;

import com.fferr10.melichallenge.solar.system.Satellite;
import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.observer.Observer;
import com.fferr10.melichallenge.solar.system.persistence.period.MostRainyDay;
import com.fferr10.melichallenge.solar.system.persistence.period.RainyPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriodRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class PeriodWeatherTracker implements Observer<DailyWeatherResult> {

    private final WeatherPeriodRepository weatherPeriodRepository;

    public PeriodWeatherTracker(Satellite satellite, WeatherPeriodRepository weatherPeriodRepository) {
        satellite.subscribe(this);
        this.weatherPeriodRepository = weatherPeriodRepository;
    }

    @Override
    @Transactional
    public void update(DailyWeatherResult dailyWeatherResult) {
        weatherPeriodRepository.getByWeatherType(dailyWeatherResult.getWeatherResult().getWeatherType())
                .map(weatherPeriod -> updateWeatherPeriod(weatherPeriod, dailyWeatherResult))
                .or(() -> Optional.of(createWeatherPeriod(dailyWeatherResult)))
                .ifPresent(weatherPeriodRepository::save);
    }

    private WeatherPeriod updateWeatherPeriod(WeatherPeriod weatherPeriod, DailyWeatherResult dailyWeatherResult) {
        weatherPeriod.setCount(weatherPeriod.getCount() + 1);
        if(weatherPeriod.getWeatherType() == WeatherResult.WeatherType.RAINY){
            RainyWeatherResult rainyWeatherResult = (RainyWeatherResult) dailyWeatherResult.getWeatherResult();
            RainyPeriod rainyPeriod = (RainyPeriod) weatherPeriod;
            if (rainyPeriod.getMostRainyDay().getIntensity() < rainyWeatherResult.getIntensity()) {
                rainyPeriod.getMostRainyDay().setDay(dailyWeatherResult.getDay());
                rainyPeriod.getMostRainyDay().setIntensity(rainyWeatherResult.getIntensity());
            }
        }
        return weatherPeriod;
    }

    private WeatherPeriod createWeatherPeriod(DailyWeatherResult dailyWeatherResult) {
        WeatherResult weatherResult = dailyWeatherResult.getWeatherResult();
        if (weatherResult.getWeatherType() == WeatherResult.WeatherType.RAINY) {
            Integer day = dailyWeatherResult.getDay();
            Double intensity = ((RainyWeatherResult) weatherResult).getIntensity();
            return new RainyPeriod(weatherResult.getWeatherType(), 1, new MostRainyDay(day, intensity));
        }
        return new WeatherPeriod(weatherResult.getWeatherType(), 1);
    }
}
