package com.fferr10.melichallenge.solar.system.interpreter.result;

public class WeatherResult {

    private WeatherType weatherType;

    public WeatherResult(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public enum WeatherType {
        RAINY("Lluvia"),
        DROUGHT("Sequia"),
        OPTIMAL("Condiciones Optimas"),
        TRANSITION("En transicion");

        private String spanishWeather;

        WeatherType(String spanishWeather) {
            this.spanishWeather = spanishWeather;
        }

        public String getSpanishWeather() {
            return spanishWeather;
        }
    }
}
