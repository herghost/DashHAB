package com.herghost.dashhab.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.herghost.dashhab.app.Common.Callbacks.WeatherCallback;
import com.herghost.dashhab.app.Common.Config;
import com.herghost.dashhab.app.Common.Results.OpenWeatherResult;
import com.herghost.dashhab.app.Common.Tasks.GetWeather;

public class MainActivity extends Activity implements WeatherCallback {

    Config c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupConfig();
        new GetWeather(MainActivity.this,c).execute();

    }


    public void setupConfig()
    {
        c = new Config();
        //Open HAB
        c._OpenHab.setURL("http://192.168.0.3");
        c._OpenHab.setPort("8080");

        //OpenWeather
        c._OpenWeather.setApiKey("");
        c._OpenWeather.setCityID(2633352);
    }

    @Override
    public void WeatherFetched(OpenWeatherResult owr) {

    }
}
