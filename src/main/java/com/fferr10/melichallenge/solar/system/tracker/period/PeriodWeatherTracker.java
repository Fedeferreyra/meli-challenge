package com.fferr10.melichallenge.solar.system.tracker.period;

import com.fferr10.melichallenge.solar.system.Satellite;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.observer.Observer;
import com.fferr10.melichallenge.solar.system.persistence.period.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class PeriodWeatherTracker implements Observer<DailyWeatherResult> {

    private final WeatherPeriodRepository weatherPeriodRepository;
    private final PeriodAggregator periodAggregator;


    public PeriodWeatherTracker(Satellite satellite,
                                WeatherPeriodRepository weatherPeriodRepository,
                                PeriodAggregator periodAggregator) {
        satellite.subscribe(this);
        this.weatherPeriodRepository = weatherPeriodRepository;
        this.periodAggregator = periodAggregator;
    }

    @Override
    @Transactional
    public void update(DailyWeatherResult dailyWeatherResult) {
        Optional<LastPeriod> maybeLastPeriod = periodAggregator.aggregate(dailyWeatherResult);

        maybeLastPeriod.map(this::upsertWeatherPeriod);
    }

    private WeatherPeriod upsertWeatherPeriod(LastPeriod lastPeriod) {
        Optional<WeatherPeriod> byWeatherType = weatherPeriodRepository.getByWeatherType(lastPeriod.getWeatherType());
        if (byWeatherType.isPresent()) {
            return updateWeatherPeriod(byWeatherType.get(), lastPeriod);
        } else {
            return createWeatherPeriod(lastPeriod);
        }
    }

    private WeatherPeriod createWeatherPeriod(LastPeriod lastPeriod) {
        WeatherPeriod weatherPeriod;
        if (lastPeriod.getWeatherType().equals(WeatherResult.WeatherType.RAINY)) {
            weatherPeriod = new RainyPeriod(1,
                    new MostRainyDay(lastPeriod.getMostRainyDay(), lastPeriod.getIntensity()));
        } else {
            weatherPeriod = new WeatherPeriod(lastPeriod.getWeatherType(), 1);
        }
        return weatherPeriodRepository.save(weatherPeriod);
    }

    private WeatherPeriod updateWeatherPeriod(WeatherPeriod weatherPeriod, LastPeriod lastPeriod) {
        weatherPeriod.setCount(weatherPeriod.getCount() + 1);
        checkRainyPeriod(weatherPeriod, lastPeriod);
        return weatherPeriodRepository.save(weatherPeriod);
    }

    private void checkRainyPeriod(WeatherPeriod weatherPeriod, LastPeriod lastPeriod) {
        if (lastPeriod.getWeatherType().equals(WeatherResult.WeatherType.RAINY)) {
            RainyPeriod rainyPeriod = (RainyPeriod) weatherPeriod;
            if (rainyPeriod.getMostRainyDay().getIntensity().compareTo(lastPeriod.getIntensity()) < 0) {
                rainyPeriod.getMostRainyDay().setDay(lastPeriod.getMostRainyDay());
                rainyPeriod.getMostRainyDay().setIntensity(lastPeriod.getIntensity());
            }
        }
    }
}
