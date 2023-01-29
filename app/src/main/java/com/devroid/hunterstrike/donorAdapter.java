package com.devroid.hunterstrike;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devroid.hunterstrike.ModelResponse.DonorUser;

import java.util.List;

public class donorAdapter extends RecyclerView.Adapter<donorAdapter.ViewHolder> {

    List<DonorUser> donorUserList;
    Context context;

    public donorAdapter(Context context, List<DonorUser> donorUserList) {
        this.context = context;
        this.donorUserList=donorUserList;
    }

    @NonNull
    @Override
    public donorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view= layoutInflater.from(context).inflate(R.layout.donor_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull donorAdapter.ViewHolder holder, int position) {

        holder.donorName.setText(donorUserList.get(position).getName());
        holder.donorBlood.setText(donorUserList.get(position).getBloodgroup());
        holder.donorNumber.setText(donorUserList.get(position).getNumber());
        holder.donorAddress.setText(donorUserList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return donorUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView donorName,donorBlood,donorNumber,donorAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            donorName=itemView.findViewById(R.id.donor_name);
            donorBlood=itemView.findViewById(R.id.donor_blood_group);
            donorNumber=itemView.findViewById(R.id.donor_phone_no);
            donorAddress=itemView.findViewById(R.id.donor_address);
        }
    }
}