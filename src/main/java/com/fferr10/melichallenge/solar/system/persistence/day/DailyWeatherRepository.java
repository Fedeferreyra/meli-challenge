package com.fferr10.melichallenge.solar.system.persistence.day;

import com.fferr10.melichallenge.solar.system.persistence.day.DailyWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyWeatherRepository extends JpaRepository<DailyWeather, Integer> {
    DailyWeather findByDay(Integer day);
}
