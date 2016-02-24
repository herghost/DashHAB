package com.herghost.dashhab.app.Common.Adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.herghost.dashhab.app.R;
import me.everything.providers.android.calendar.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hergh on 24/02/2016.
 */
public class ArsenalFixturesAdaptor extends RecyclerView.Adapter<ArsenalFixturesAdaptor.ContactViewHolder> {

    private List<Event> fixtures;


    public ArsenalFixturesAdaptor(List<Event> _fixtures) {
        this.fixtures = _fixtures;

    }


    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {

        Event e = fixtures.get(i);
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, HH:mm");
        String dateString = formatter.format(new Date(e.dTStart));
        contactViewHolder.Fixture.setText(e.title.replace("(H)", "").replace("(A)", ""));
        contactViewHolder.yDate.setText(dateString);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.arsenal_fixtures, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        protected TextView yDate;
        protected TextView Fixture;


        public ContactViewHolder(View v) {
            super(v);


            yDate = (TextView) v.findViewById(R.id.tvDate);
            Fixture = (TextView) v.findViewById(R.id.tvFixture);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


