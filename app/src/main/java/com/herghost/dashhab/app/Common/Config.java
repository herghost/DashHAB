package com.herghost.dashhab.app.Common;

import java.lang.annotation.Retention;
import java.util.Objects;

/**
 * Created by hergh on 21/02/2016.
 */
public class Config {

   public OpenHAB _OpenHab = new OpenHAB();
    public OpenWeather _OpenWeather = new OpenWeather();

    public class OpenHAB
    {
        String URL;
        String Port;
        public String getURL() {
            return URL;
        }
        public void setURL(String URL) {
            this.URL = URL;
        }
        public String getPort() {
            return Port;
        }
        public void setPort(String port) {
            Port = port;
        }
    }
    public class OpenWeather
    {
        String ApiKey;
        int CityID;
        Boolean displayWeather;

        public String getApiKey() {
            return ApiKey;
        }

        public void setApiKey(String apiKey) {
            ApiKey = apiKey;
        }

        public int getCityID() {
            return CityID;
        }

        public void setCityID(int cityID) {
            CityID = cityID;
        }

        public Boolean getDisplayWeather() {
            return displayWeather;
        }

        public void setDisplayWeather(Boolean displayWeather) {
            this.displayWeather = displayWeather;
        }
    }

}
