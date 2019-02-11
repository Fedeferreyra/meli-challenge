package com.fferr10.melichallenge.solar.system.tracker.period;

import com.fferr10.melichallenge.solar.fixture.TestFixture;
import com.fferr10.melichallenge.solar.system.interpreter.result.RainyWeatherResult;
import com.fferr10.melichallenge.solar.system.interpreter.result.WeatherResult;
import com.fferr10.melichallenge.solar.system.persistence.period.LastPeriod;
import com.fferr10.melichallenge.solar.system.persistence.period.LastPeriodRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PeriodAggregatorTest extends TestFixture {

    private PeriodAggregator periodAggregator;
    @Mock
    private LastPeriodRepository lastPeriodRepository;
    @Captor
    private ArgumentCaptor<LastPeriod> argumentCaptor;


    @Before
    public void setUp() {
        periodAggregator = new PeriodAggregator(lastPeriodRepository);
    }

    @Test
    public void shouldCreateNewPeriodAndReturnEmptyWhenIsTheFirstOne() {
        when(lastPeriodRepository.getByLastUpdated(Mockito.anyInt())).thenReturn(Optional.empty());


        Optional<LastPeriod> maybeLastPeriod = periodAggregator.aggregate(droughtDailyWeatherResult);

        assertTrue(!maybeLastPeriod.isPresent());
        verify(lastPeriodRepository, times(1)).save(argumentCaptor.capture());
        LastPeriod lastPeriod = argumentCaptor.getValue();

        assertEquals(WeatherResult.WeatherType.DROUGHT, lastPeriod.getWeatherType());
    }

    @Test
    public void shouldUpdateAndReturnEmptyWhenLastPeriodAndDailyWeatherAreSameWeatherType(){
        when(lastPeriodRepository.getByLastUpdated(Mockito.anyInt()))
                .thenReturn(Optional.of(droughtLastPeriod));

        Optional<LastPeriod> aggregate = periodAggregator.aggregate(droughtDailyWeatherResult);

        assertTrue(!aggregate.isPresent());
        verify(lastPeriodRepository, times(1)).save(argumentCaptor.capture());
        LastPeriod lastPeriod = argumentCaptor.getValue();

        assertEquals(lastPeriod.getLastUpdated(), droughtDailyWeatherResult.getDay());

    }

    @Test
    public void shouldUpdateMostRainyDayAndReturnEmptyWhenIsCalledWithRainyWeatherAndHigherIntensity(){
        when(lastPeriodRepository.getByLastUpdated(anyInt())).thenReturn(Optional.of(rainyLastPeriod));

        Optional<LastPeriod> aggregate = periodAggregator.aggregate(rainyDailyWeatherResult);

        assertTrue(!aggregate.isPresent());
        verify(lastPeriodRepository, times(1)).save(argumentCaptor.capture());
        LastPeriod lastPeriod = argumentCaptor.getValue();

        assertEquals(lastPeriod.getIntensity(), ((RainyWeatherResult) rainyDailyWeatherResult.getWeatherResult()).getIntensity());
    }

    @Test
    public void shouldCreateNewLastPeriodAndDeleteThePreviousOneWhenIsCalledWithDifferentWeatherThanLastDay(){
        when(lastPeriodRepository.getByLastUpdated(anyInt())).thenReturn(Optional.of(rainyLastPeriod));

        Optional<LastPeriod> aggregate = periodAggregator.aggregate(droughtDailyWeatherResult);

        assertTrue(aggregate.isPresent());

        verify(lastPeriodRepository, times(1)).delete(argumentCaptor.capture());
        LastPeriod deletedLastPeriod = argumentCaptor.getValue();
        assertEquals(rainyLastPeriod, deletedLastPeriod);

        verify(lastPeriodRepository, times(1)).save(argumentCaptor.capture());
        LastPeriod createdLastPeriod = argumentCaptor.getValue();

        assertEquals(WeatherResult.WeatherType.DROUGHT, createdLastPeriod.getWeatherType());
    }
}