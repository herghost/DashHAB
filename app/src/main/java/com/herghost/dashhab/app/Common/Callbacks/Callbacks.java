package com.herghost.dashhab.app.Common.Callbacks;

import com.herghost.dashhab.app.Common.Results.OpenWeatherResult;
import me.everything.providers.android.calendar.Event;

import java.util.List;

/**
 * Created by hergh on 21/02/2016.
 */
public interface Callbacks {
    void WeatherFetched(OpenWeatherResult owr);

    void BirthDaysFetched(List<Event> bEvents);

    void FixturesFetched(List<Event> bEvents);
}
