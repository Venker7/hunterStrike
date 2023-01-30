package com.devroid.hunterstrike;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.ReceiverUser;
import com.devroid.hunterstrike.ModelResponse.fetchReceiveUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecieverFragment extends Fragment {


    RecyclerView recyclerView;
    List<ReceiverUser> receiverUserList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reciever, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.receiverRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //retrofit
        Call<fetchReceiveUserResponse> call=retrofitClient.getInstance().getApi().fetchreceivers();

        call.enqueue(new Callback<fetchReceiveUserResponse>() {
            @Override
            public void onResponse(Call<fetchReceiveUserResponse> call, Response<fetchReceiveUserResponse> response) {
                if (response.isSuccessful()){
                    receiverUserList=response.body().getReceiverUserList();
                    recyclerView.setAdapter(new receiverAdapter(getActivity(),receiverUserList));
                }
                else{
                    Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<fetchReceiveUserResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}