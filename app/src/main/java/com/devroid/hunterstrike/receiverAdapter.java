package com.devroid.hunterstrike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.devroid.hunterstrike.ModelResponse.ReceiverUser;

import java.util.List;

public class receiverAdapter extends RecyclerView.Adapter<receiverAdapter.ViewHolder> {


    List<ReceiverUser> receiverUserList;
    Context context;
    public receiverAdapter( Context context, List<ReceiverUser> receiverUserList) {
        this.context=context;
        this.receiverUserList=receiverUserList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.from(context).inflate(R.layout.receiver_card_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull receiverAdapter.ViewHolder holder, int position) {
        holder.receiverName.setText(receiverUserList.get(position).getName());
        holder.receiverBlood.setText(receiverUserList.get(position).getBloodgroup());
        holder.receiverNumber.setText(receiverUserList.get(position).getNumber());
        holder.receiverAddress.setText(receiverUserList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return receiverUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView receiverName,receiverBlood,receiverNumber,receiverAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverName=itemView.findViewById(R.id.receiver_name);
            receiverBlood=itemView.findViewById(R.id.receiver_blood_group);
            receiverNumber=itemView.findViewById(R.id.receiver_phone_no);
            receiverAddress=itemView.findViewById(R.id.receiver_address);
        }
    }
}
