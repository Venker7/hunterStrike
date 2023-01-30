package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.createDonorResponse;
import com.devroid.hunterstrike.ModelResponse.createReceiverResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reciever extends AppCompatActivity {
    String[] type = {"A+","A-","B+","B-","AB+","AB-","0+","O-"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterTypes;

    String receiverbloodgroup;
    TextInputEditText receiverName,receiverAddress,receiverNumber,receiverMessage;
    Button postReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);

        receiverName=findViewById(R.id.r_name);
        receiverAddress=findViewById(R.id.r_address);
        receiverNumber=findViewById(R.id.r_number);
        receiverMessage=findViewById(R.id.r_message);
        postReceiver=findViewById(R.id.createReceiver);


        autoCompleteTextView = findViewById(R.id.auto_text);
        adapterTypes = new ArrayAdapter<String>(this,R.layout.blood_group,type);
        autoCompleteTextView.setAdapter(adapterTypes);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                receiverbloodgroup = adapterTypes.getItem(i).toString();
            }
        });

        postReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createReceiver();
            }

        });

    }
    public void createReceiver() {
        String Rname=receiverName.getText().toString();
        String Rbloodgroup=receiverbloodgroup;
        String Rnumber=receiverNumber.getText().toString();
        String Raddress=receiverAddress.getText().toString();
        String Rmessage=receiverMessage.getText().toString();
        if(Rname.isEmpty()){
            receiverName.requestFocus();
            receiverName.setError("Please enter your name");
            return;
        }
        if(Rnumber.isEmpty()){
            receiverNumber.requestFocus();
            receiverNumber.setError("Please enter your name");
            return;
        }
        if(Raddress.isEmpty()){
            receiverAddress.requestFocus();
            receiverAddress.setError("Please enter your name");
            return;
        }
        if(Rmessage.isEmpty()){
            receiverMessage.requestFocus();
            receiverMessage.setError("Please enter your name");
            return;
        }
        Call<createReceiverResponse> call=retrofitClient
                .getInstance()
                .getApi().createreceiver(Rname,Rbloodgroup,Raddress,Rnumber,Rmessage);
        call.enqueue(new Callback<createReceiverResponse>() {
            @Override
            public void onResponse(Call<createReceiverResponse> call, Response<createReceiverResponse> response) {
                createReceiverResponse createReceiverResponse=response.body();
                if (response.isSuccessful()){
                    Toast.makeText(Reciever.this, createReceiverResponse.getSuccess(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Reciever.this, createReceiverResponse.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<createReceiverResponse> call, Throwable t) {
                Toast.makeText(Reciever.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}