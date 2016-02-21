package com.herghost.dashhab.app.Common.Results;

public class forecast {
    String dt;
    OpenWeatherResult.main _main;
    OpenWeatherResult.weather _weather;
    OpenWeatherResult.clouds _clouds;
    OpenWeatherResult.wind _wind;
    OpenWeatherResult.rain _rain;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public OpenWeatherResult.main get_main() {
        return _main;
    }

    public void set_main(OpenWeatherResult.main _main) {
        this._main = _main;
    }

    public OpenWeatherResult.weather get_weather() {
        return _weather;
    }

    public void set_weather(OpenWeatherResult.weather _weather) {
        this._weather = _weather;
    }

    public OpenWeatherResult.clouds get_clouds() {
        return _clouds;
    }

    public void set_clouds(OpenWeatherResult.clouds _clouds) {
        this._clouds = _clouds;
    }

    public OpenWeatherResult.wind get_wind() {
        return _wind;
    }

    public void set_wind(OpenWeatherResult.wind _wind) {
        this._wind = _wind;
    }

    public OpenWeatherResult.rain get_rain() {
        return _rain;
    }

    public void set_rain(OpenWeatherResult.rain _rain) {
        this._rain = _rain;
    }






}
