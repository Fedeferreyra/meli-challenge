package com.fferr10.melichallenge.solar.system.persistence.period;

import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherPeriodRepository extends JpaRepository<WeatherPeriod, Integer> {

    Optional<WeatherPeriod> getByWeatherType(WeatherResult.WeatherType weatherType);
}
