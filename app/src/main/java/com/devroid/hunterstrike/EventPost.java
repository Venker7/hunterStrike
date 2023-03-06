package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.createEventResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPost extends AppCompatActivity {


    EditText eventName,eventMessage;
    Button postEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_post);
        eventName=findViewById(R.id.eventName);
        eventMessage=findViewById(R.id.eventMessage);
        postEvent=findViewById(R.id.eventPost);

        postEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEvent();
            }
        });


    }
    public void createEvent(){
        String EName=eventName.getText().toString();
        String EMessage=eventMessage.getText().toString();

        if(EName.isEmpty()){
            eventName.requestFocus();
            eventName.setError("Please enter your event");
            return;
        } if(EMessage.isEmpty()){
            eventMessage.requestFocus();
            eventMessage.setError("Please enter your event Message");
            return;
        }
        Call<createEventResponse> call=retrofitClient
                .getInstance()
                .getApi().createEvent(EName,EMessage);
        call.enqueue(new Callback<createEventResponse>() {
            @Override
            public void onResponse(Call<createEventResponse> call, Response<createEventResponse> response) {
                createEventResponse createEventResponse=response.body();
                if(response.isSuccessful()){
                    Toast.makeText(EventPost.this,createEventResponse.getSuccess(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EventPost.this, createEventResponse.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<createEventResponse> call, Throwable t) {
                Toast.makeText(EventPost.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}