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

import com.devroid.hunterstrike.ModelResponse.DonorUser;
import com.devroid.hunterstrike.ModelResponse.FetchDonorUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DonorFragment extends Fragment {


    RecyclerView recyclerView;
    List<DonorUser> donorUserList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.donorRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //retrofit
        Call<FetchDonorUserResponse> call=retrofitClient.getInstance().getApi().fetchdonors();

        call.enqueue(new Callback<FetchDonorUserResponse>() {
            @Override
            public void onResponse(Call<FetchDonorUserResponse> call, Response<FetchDonorUserResponse> response) {
                if(response.isSuccessful()){
                    donorUserList=response.body().getDonorUserList();
                    recyclerView.setAdapter(new donorAdapter(getActivity(),donorUserList));
                }
                else{
                    Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchDonorUserResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}