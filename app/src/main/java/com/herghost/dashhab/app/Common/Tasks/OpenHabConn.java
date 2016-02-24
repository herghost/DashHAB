package com.herghost.dashhab.app.Common.Tasks;

import android.os.AsyncTask;
import android.os.Handler;
import com.herghost.dashhab.app.Common.Callbacks.Callbacks;
import com.herghost.dashhab.app.Common.Results.OHResults;

import java.util.ArrayList;

/**
 * Created by hergh on 21/02/2016.
 */
public class OpenHabConn extends AsyncTask<Void,Void,Void> {

    String Group;
    Callbacks cb;
    ArrayList<OHResults> result;

    public OpenHabConn(Callbacks _cb, String _group)
    {
        this.cb = _cb;
        this.Group = _group;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        result = new ArrayList<>();






        return null;
    }
}
