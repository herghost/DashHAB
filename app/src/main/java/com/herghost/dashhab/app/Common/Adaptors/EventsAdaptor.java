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
public class EventsAdaptor extends RecyclerView.Adapter<EventsAdaptor.ContactViewHolder> {

    private List<Event> fixtures;


    public EventsAdaptor(List<Event> _fixtures) {
        this.fixtures = _fixtures;

    }


    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {

        Event e = fixtures.get(i);
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d");
        String dateString = formatter.format(new Date(e.dTStart));
        contactViewHolder.yDate.setText(dateString);
        contactViewHolder.Summary.setText(e.title);
        contactViewHolder.Details.setText(e.description);

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.events_event, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        protected TextView yDate;
        protected TextView Summary;
        protected TextView Details;


        public ContactViewHolder(View v) {
            super(v);


            yDate = (TextView) v.findViewById(R.id.tvDate);
            Summary = (TextView) v.findViewById(R.id.tvSummary);
            Details = (TextView)v.findViewById(R.id.tvDetails);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


