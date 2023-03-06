package com.devroid.hunterstrike;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.event;
import com.devroid.hunterstrike.ModelResponse.fetchEventResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//importort com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    List<event> eventList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.home_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.eventRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<fetchEventResponse> call=retrofitClient.getInstance().getApi().fetchEvents();

        call.enqueue(new Callback<fetchEventResponse>() {
            @Override
            public void onResponse(Call<fetchEventResponse> call, Response<fetchEventResponse> response) {
                if(response.isSuccessful()){
                    eventList=response.body().getUsers();
                    recyclerView.setAdapter(new eventAdapter(eventList,getActivity()));
                }
                else{
                    Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<fetchEventResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.message:
                Intent message=new Intent(Intent.ACTION_VIEW);
                message.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+919863059696"+ "&text="));
                startActivity(message);
                Toast.makeText(getActivity(), "You have press message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"check this cool app");
                intent.putExtra(Intent.EXTRA_TEXT,"Application link here:- https://www.youtube.com/watch?v=ovD8zJKkgZ4");
                startActivity(Intent.createChooser(intent,"Share via"));
                break;

            case R.id.help:
                break;

            case R.id.feedback:
                Intent intent1 =new Intent(Intent.ACTION_SENDTO);
                String Uritext ="mailto:"+ Uri.encode("konthoujamlanthoiba@gmail.com")+"?subject="+
                        Uri.encode("Feedback ");
                Uri uri=Uri.parse(Uritext);
                intent1.setData(uri);
                startActivity(Intent.createChooser(intent1,"send email"));
                Toast.makeText(getActivity(), "You have press feedback", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;



        }
        return super.onOptionsItemSelected(item);
    }


}