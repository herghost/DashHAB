package com.herghost.dashhab.app.Common.Tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.*;
import com.herghost.dashhab.app.Common.Callbacks.WeatherCallback;
import com.herghost.dashhab.app.Common.Config;
import com.herghost.dashhab.app.Common.Results.OpenWeatherResult;
import com.herghost.dashhab.app.Common.Results.forecast;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.ArrayList;

import static com.herghost.dashhab.app.Common.Results.OpenWeatherResult.*;


/**
 * Created by hergh on 21/02/2016.
 * Gets the weather and forecasts from openweathermap
 */
public class GetWeather extends AsyncTask<Void,Void,Void> {


    WeatherCallback cb;
    OpenWeatherResult owr;
    Config c;

    public GetWeather(WeatherCallback _cb, Config _c)
    {
        this.cb = _cb;
        this.c = _c;
    }

    @Override
    protected Void doInBackground(Void... voids) {


        owr = new OpenWeatherResult();

        OkHttpClient client = new OkHttpClient();
        Request r = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?id=" + c._OpenWeather.getCityID() + "&appid=" + c._OpenWeather.getApiKey() + "&units=metric")
                .build();



        ArrayList<forecast> fc = new ArrayList<>();
        Response response;
        Call call = client.newCall(r);

        try
        {
            response = call.execute();

            if(response!=null)
            {
                String s;
                s = response.body().string();
                JsonParser jp = new JsonParser();
                JsonObject jo = jp.parse(s).getAsJsonObject();

                owr.setBase(jo.get("base").toString());
                owr.setDt(Integer.parseInt(jo.get("dt").toString()));
                owr.setId(Integer.parseInt(jo.get("id").toString()));
                owr.setName(jo.get("name").toString());
                owr.setCod(Integer.parseInt(jo.get("cod").toString()));

                JsonObject coords = jo.getAsJsonObject("coord");
                owr._coord.setLon(coords.get("lon").toString());
                owr._coord.setLat(coords.get("lat").toString());

                JsonArray _weather = jo.getAsJsonArray("weather");

                for(JsonElement __weather : _weather) {

                    JsonObject weather = (JsonObject) __weather;
                    owr._weather.setId(Integer.parseInt(weather.get("id").toString()));
                    owr._weather.setMain(weather.get("main").toString());
                    owr._weather.setDescription(weather.get("description").toString());
                    owr._weather.setIcon(weather.get("icon").toString());
                }

                JsonObject main = jo.getAsJsonObject("main");
                owr._main.setTemp(main.get("temp").toString());
                owr._main.setPressure(main.get("pressure").toString());
                owr._main.setHumidity(main.get("humidity").toString());
                owr._main.setTemp_min(main.get("temp_min").toString());
                owr._main.setTemp_max(main.get("temp_max").toString());


                JsonObject wind = jo.getAsJsonObject("wind");
                owr._wind.setSpeed(wind.get("speed").toString());
                owr._wind.setDeg(wind.get("deg").toString());

                JsonObject rain = jo.getAsJsonObject("rain");
                if(rain!=null) {
                    owr._rain.setH3(rain.get("3h").toString());
                }

                JsonObject clouds = jo.getAsJsonObject("clouds");
                if(clouds!=null) {
                    owr._clouds.setAll(clouds.get("all").toString());
                }

                JsonObject sys = jo.getAsJsonObject("sys");
                owr._sys.setMessage(sys.get("message").toString());
                owr._sys.setCountry(sys.get("country").toString());
                owr._sys.setSunrise(Integer.parseInt(sys.get("sunrise").toString()));
                owr._sys.setSunset(Integer.parseInt(sys.get("sunset").toString()));

                //Forecast

                r = new Request.Builder()
                        .url("http://api.openweathermap.org/data/2.5/forecast?id=" + c._OpenWeather.getCityID() + "&appid=" + c._OpenWeather.getApiKey() + "&units=metric")
                        .build();
                call = client.newCall(r);

                try
                {
                    response = call.execute();

                    if(response!=null)
                    {
                        s = response.body().string();
                        jp = new JsonParser();
                        jo = jp.parse(s).getAsJsonObject();

                        JsonArray list = jo.getAsJsonArray("list");
                        for(JsonElement e : list)
                        {
                            jo = e.getAsJsonObject();


                            forecast f = new forecast();
                            f.setDt(jo.get("dt").toString());

                            main = jo.getAsJsonObject("main");
                            OpenWeatherResult.main m = new OpenWeatherResult()._main;
                            m.setTemp(main.get("temp").toString());
                            m.setTemp_min(main.get("temp_min").toString());
                            m.setTemp_max(main.get("temp_max").toString());
                            m.setPressure(main.get("pressure").toString());
                            m.setHumidity(main.get("humidity").toString());
                            f.set_main(m);

                            _weather = jo.getAsJsonArray("weather");
                            for(JsonElement mWeather : _weather) {
                                OpenWeatherResult.weather w = new OpenWeatherResult()._weather;
                                JsonObject weather = (JsonObject) mWeather;
                                w.setId(Integer.parseInt(weather.get("id").toString()));
                                w.setMain(weather.get("main").toString());
                                w.setDescription(weather.get("description").toString());
                                w.setIcon(weather.get("icon").toString());
                                f.set_weather(w);
                            }

                            wind = jo.getAsJsonObject("wind");
                            OpenWeatherResult.wind wi = new OpenWeatherResult()._wind;
                            if(wind!=null) {
                                wi.setSpeed(wind.get("speed").toString());
                                wi.setDeg(wind.get("deg").toString());
                                f.set_wind(wi);
                            }

                            rain = jo.getAsJsonObject("rain");
                            OpenWeatherResult.rain ra = new OpenWeatherResult()._rain;
                            if(rain!=null) {
                                if(rain.toString().length() > 3)
                                {
                                    ra.setH3(rain.get("3h").toString());
                                    f.set_rain(ra);
                                }
                            }


                            clouds = jo.getAsJsonObject("clouds");
                            OpenWeatherResult.clouds c = new OpenWeatherResult()._clouds;
                            if(clouds!=null) {
                                c.setAll(clouds.get("all").toString());
                                f.set_clouds(c);
                            }
                            Log.d("Weather", "Forecast Gathered");
                            fc.add(f);
                        }

                        owr._fc = fc;
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("Weather", "Failed " + e.toString());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Weather", "Failed " + e.toString());
        }

        Log.d("Weather", "Fetched");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        cb.WeatherFetched(owr);
    }
}


