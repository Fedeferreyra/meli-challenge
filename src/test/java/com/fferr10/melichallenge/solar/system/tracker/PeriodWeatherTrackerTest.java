package com.fferr10.melichallenge.solar.system.tracker;

import com.fferr10.melichallenge.solar.system.Satellite;
import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.model.DailyWeatherResult;
import com.fferr10.melichallenge.solar.system.persistence.period.LastPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.RainyPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.WeatherPeriodRepository;
import com.fferr10.melichallenge.solar.system.tracker.period.PeriodAggregator;
import com.fferr10.melichallenge.solar.system.tracker.period.PeriodWeatherTracker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.fferr10.melichallenge.solar.system.utils.BigDecimalFormatter.toBigDecimal;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.util.Assert.isInstanceOf;

@RunWith(MockitoJUnitRunner.class)
public class PeriodWeatherTrackerTest {

    private PeriodWeatherTracker periodWeatherTracker;
    @Mock
    private Satellite satellite;
    @Mock
    private WeatherPeriodRepository weatherPeriodRepository;
    @Mock
    private PeriodAggregator periodAggregator;
    @Captor
    private ArgumentCaptor<WeatherPeriod> argumentCaptor;

    @Before
    public void setUp() {
        when(periodAggregator.aggregate(any()))
                .thenReturn(Optional.of(new LastPeriod(WeatherResult.WeatherType.DROUGHT, 1)));
        periodWeatherTracker = new PeriodWeatherTracker(satellite, weatherPeriodRepository, periodAggregator);
    }

    @Test
    public void verifyRepositoryGetByWeatherTypeIsCalledOnce() {
        when(weatherPeriodRepository.getByWeatherType(any())).thenReturn(Optional.empty());
        when(weatherPeriodRepository.save(any())).thenReturn(any());

        periodWeatherTracker.update(new DailyWeatherResult(1, new WeatherResult(WeatherResult.WeatherType.DROUGHT)));

        verify(weatherPeriodRepository, times(1)).getByWeatherType(any());
    }

    @Test
    public void verifyRepositorySaveIsCalledWithValueWhenGetByWeatherReturnEmpty() {
        when(weatherPeriodRepository.getByWeatherType(any())).thenReturn(Optional.empty());

        periodWeatherTracker.update(new DailyWeatherResult(1, new WeatherResult(WeatherResult.WeatherType.DROUGHT)));
        verify(weatherPeriodRepository, times(1)).save(argumentCaptor.capture());

        assertEquals(WeatherResult.WeatherType.DROUGHT, argumentCaptor.getValue().getWeatherType());
    }

    @Test
    public void shouldCreateRainyPeriodWhenDailyWeatherIsRainyAndGetWeatherByTypeReturnEmpty() {
        when(periodAggregator.aggregate(any()))
                .thenReturn(Optional.of(new LastPeriod(WeatherResult.WeatherType.RAINY,
                        2, toBigDecimal(9), 2)));
        when(weatherPeriodRepository.getByWeatherType(any())).thenReturn(Optional.empty());

        DailyWeatherResult dailyWeatherResult = new DailyWeatherResult(1, new RainyWeatherResult(toBigDecimal(9)));
        periodWeatherTracker.update(dailyWeatherResult);

        verify(weatherPeriodRepository, times(1)).save(argumentCaptor.capture());

        isInstanceOf(RainyPeriod.class, argumentCaptor.getValue());
        RainyPeriod rainyPeriod = (RainyPeriod) argumentCaptor.getValue();
        assertEquals(WeatherResult.WeatherType.RAINY, rainyPeriod.getWeatherType());
        assertEquals(toBigDecimal(9), rainyPeriod.getMostRainyDay().getIntensity());
    }
}