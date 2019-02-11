package com.fferr10.melichallenge.solar.system.tracker.period;

import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.persistence.period.LastPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.LastPeriodRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class PeriodAggregator {

    private final LastPeriodRepository lastPeriodRepository;

    public PeriodAggregator(LastPeriodRepository lastPeriodRepository) {
        this.lastPeriodRepository = lastPeriodRepository;
    }

    public Optional<LastPeriod> aggregate(DailyWeatherResult dailyWeatherResult) {
        Optional<LastPeriod> byLastUpdated = lastPeriodRepository.getByLastUpdated(dailyWeatherResult.getDay() - 1);

        if (byLastUpdated.isPresent()) {
            return upsertPeriod(dailyWeatherResult, byLastUpdated.get());
        } else {
            createNewLastPeriod(dailyWeatherResult);
            return Optional.empty();
        }
    }

    private Optional<LastPeriod> upsertPeriod(DailyWeatherResult dailyWeatherResult, LastPeriod lastPeriod) {
        if (dailyWeatherResult.getWeatherResult().getWeatherType().equals(lastPeriod.getWeatherType())) {
            return update(dailyWeatherResult, lastPeriod);
        } else {
            return cleanInsertPeriod(dailyWeatherResult, lastPeriod);
        }
    }

    private Optional<LastPeriod> cleanInsertPeriod(DailyWeatherResult dailyWeatherResult, LastPeriod lastPeriod) {
        lastPeriodRepository.delete(lastPeriod);
        createNewLastPeriod(dailyWeatherResult);
        return Optional.of(lastPeriod);
    }

    private void createNewLastPeriod(DailyWeatherResult dailyWeatherResult) {
        WeatherResult weatherResult = dailyWeatherResult.getWeatherResult();
        LastPeriod newLastPeriod = new LastPeriod(weatherResult.getWeatherType(), dailyWeatherResult.getDay());
        checkRainyPeriod(dailyWeatherResult, newLastPeriod);
        lastPeriodRepository.save(newLastPeriod);
    }

    private Optional<LastPeriod> update(DailyWeatherResult dailyWeatherResult, LastPeriod lastPeriod) {
        lastPeriod.setLastUpdated(dailyWeatherResult.getDay());
        checkRainyPeriod(dailyWeatherResult, lastPeriod);
        lastPeriodRepository.save(lastPeriod);
        return Optional.empty();
    }

    private void checkRainyPeriod(DailyWeatherResult dailyWeatherResult, LastPeriod lastPeriod) {
        if (lastPeriod.getWeatherType().equals(WeatherResult.WeatherType.RAINY)) {
            RainyWeatherResult rainyWeatherResult = (RainyWeatherResult) dailyWeatherResult.getWeatherResult();
            if (!Objects.nonNull(lastPeriod.getIntensity()) ||
                    lastPeriod.getIntensity().compareTo(rainyWeatherResult.getIntensity()) < 0) {
                lastPeriod.setMostRainyDay(dailyWeatherResult.getDay());
                lastPeriod.setIntensity(rainyWeatherResult.getIntensity());
            }
        }
    }
}
