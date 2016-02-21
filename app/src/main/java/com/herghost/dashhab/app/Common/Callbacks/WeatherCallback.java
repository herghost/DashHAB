package com.herghost.dashhab.app.Common.Callbacks;

import com.herghost.dashhab.app.Common.Results.OpenWeatherResult;

/**
 * Created by hergh on 21/02/2016.
 */
public interface WeatherCallback {
    void WeatherFetched(OpenWeatherResult owr);
}
