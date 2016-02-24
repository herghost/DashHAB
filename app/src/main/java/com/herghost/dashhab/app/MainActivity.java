package com.herghost.dashhab.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.herghost.dashhab.app.Common.Adaptors.ArsenalFixturesAdaptor;
import com.herghost.dashhab.app.Common.Adaptors.EventsAdaptor;
import com.herghost.dashhab.app.Common.Callbacks.Callbacks;
import com.herghost.dashhab.app.Common.Config;
import com.herghost.dashhab.app.Common.Tasks.GetArsenalFixtures;
import com.herghost.dashhab.app.Common.Tasks.GetBirthdays;
import com.herghost.dashhab.app.Common.Results.OpenWeatherResult;
import com.herghost.dashhab.app.Common.Tasks.GetWeather;
import me.everything.providers.android.calendar.Event;

import java.text.SimpleDateFormat;
import java.util.*;

import static br.com.zbra.androidlinq.Linq.stream;

public class MainActivity extends Activity implements Callbacks {

    Config c;
    TextView ShowDate;
    private final static int INTERVAL = 1000 * 60 * 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d yyyy");
        String dateString = formatter.format(new Date(System.currentTimeMillis()));
        ShowDate = (TextView) findViewById(R.id.tvDate);
        ShowDate.setText(dateString);

        setupConfig();

        final Handler handle15 = new Handler();
        handle15.postDelayed(new Runnable() {
            @Override
            public void run() {


                    new GetWeather(MainActivity.this,c).execute();
                new GetBirthdays(getApplicationContext(), MainActivity.this).execute();
                new GetArsenalFixtures(getApplicationContext(),MainActivity.this).execute();

                handle15.postDelayed(this, INTERVAL); // Runs every 10 minutes
            }
        }, INTERVAL);

    }





    public void setupConfig()
    {
        c = new Config();
        //Open HAB
        c._OpenHab.setURL("http://192.168.0.3");
        c._OpenHab.setPort("8080");

        //OpenWeather
        c._OpenWeather.setApiKey("myapikey");
        c._OpenWeather.setCityID(2633352);
        c._OpenWeather.setDisplayWeather(true);

        if(c._OpenWeather.getDisplayWeather()) {
            new GetWeather(MainActivity.this, c).execute();
        }

        new GetBirthdays(this,MainActivity.this).execute();
        new GetArsenalFixtures(this,MainActivity.this).execute();

    }

    @Override
    public void WeatherFetched(OpenWeatherResult owr) {

    }

    @Override
    public void BirthDaysFetched(List<Event> bEvents) {
        List<Event> upcoming = new ArrayList<>();
        if(bEvents!=null)
        {
          upcoming =  stream(bEvents).where(c -> (c.lastDate / 1000L) + 31556926 > System.currentTimeMillis() / 1000L).orderBy(c -> c.lastDate).take(5).toList();
        }

        RecyclerView eVe = (RecyclerView) findViewById(R.id.events);
        eVe.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        eVe.setLayoutManager(llm);
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.events_header);
        try {
            header.attachTo(eVe);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        EventsAdaptor afa = new EventsAdaptor(upcoming);
        eVe.setAdapter(afa);

    }

    @Override
    public void FixturesFetched(List<Event> bEvents) {
        List<Event> upcoming = new ArrayList<>();
        if(bEvents!=null)
        {
            upcoming =  stream(bEvents).where(c -> (c.dTStart / 1000L)  > System.currentTimeMillis() / 1000L).orderBy(c -> c.dTStart).take(8).toList();
        }

        RecyclerView aFix = (RecyclerView) findViewById(R.id.arse_fix);
        aFix.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        aFix.setLayoutManager(llm);
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(this, R.layout.arsenal_header);
        try {
           header.attachTo(aFix);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ArsenalFixturesAdaptor afa = new ArsenalFixturesAdaptor(upcoming);
        aFix.setAdapter(afa);

    }


}
