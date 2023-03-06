package com.devroid.hunterstrike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devroid.hunterstrike.ModelResponse.event;

import java.util.List;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {

    List<event> eventList;
    Context context;

    public eventAdapter(List<event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.from(context).inflate(R.layout.eventcardview,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull eventAdapter.ViewHolder holder, int position) {
        holder.eventTtopic.setText(eventList.get(position).getEvent());
        holder.eventMmessage.setText(eventList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventTtopic,eventMmessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTtopic=itemView.findViewById(R.id.eventtopic);
            eventMmessage=itemView.findViewById(R.id.eventMsg);
        }
    }
}
