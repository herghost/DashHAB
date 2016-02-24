package com.herghost.dashhab.app.Common.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.herghost.dashhab.app.Common.Callbacks.Callbacks;
import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hergh on 22/02/2016.
 * Gets upcoming birthdays from my Birthday calander
 */
public class GetBirthdays extends AsyncTask<Void,Void,Void> {


    Context ctx;
    Callbacks cb;
    List<Event> bEvents;

    public GetBirthdays(Context _ctx, Callbacks _cb)
    {
        this.ctx = _ctx;
        this.cb = _cb;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        List<Calendar> calendars = new ArrayList<>();
        CalendarProvider calendarProvider = new CalendarProvider(ctx);
        try {
             calendars = calendarProvider.getCalendars().getList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for(Calendar r : calendars)
        {
            if(r.displayName.equals("TabBirthdays") && r.accountName.equals("mygmail"))
            {
              bEvents  = calendarProvider.getEvents(r.id).getList();
            }
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        cb.BirthDaysFetched(bEvents);
    }
}
