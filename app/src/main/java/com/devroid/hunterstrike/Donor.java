package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.createDonorResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Donor extends AppCompatActivity {
String[] type1 = {"A+","A-","B+","B-","AB+","AB-","0+","O-"};
AutoCompleteTextView autoCompleteTextView;
String type2;
ArrayAdapter<String> adapterTypes;

TextInputEditText donorName,donorAddress,donorNumber,donorMessage;
TextInputLayout donorBloodGroup;
Button postDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        donorName=findViewById(R.id.d_name);
        donorAddress=findViewById(R.id.d_address);
        donorNumber=findViewById(R.id.d_number);
        donorMessage=findViewById(R.id.d_message);
        donorBloodGroup=findViewById(R.id.donorBlood);
        postDonor=findViewById(R.id.postDonor);
        autoCompleteTextView = findViewById(R.id.auto_text);
        adapterTypes = new ArrayAdapter<String>(this,R.layout.blood_group,type1);
        autoCompleteTextView.setAdapter(adapterTypes);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               type2 = adapterTypes.getItem(i).toString();

            }
        });

        postDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createdonor();
            }
        });




    }
    public void createdonor(){
        String DName = donorName.getText().toString();
        String DBloodGroup = type2;
        String DNumber = donorNumber.getText().toString();
        String DAddress = donorAddress.getText().toString();
        String DMessage = donorMessage.getText().toString();

       if(DName.isEmpty()){
            donorName.requestFocus();
            donorName.setError("Please enter your name");
            return;
       }  if(DBloodGroup.isEmpty()){
            donorBloodGroup.requestFocus();
            donorBloodGroup.setError("Please enter your bloodgroup");
            return;
       }  if(DNumber.isEmpty()){
            donorNumber.requestFocus();
            donorNumber.setError("Please enter your number");
            return;
       }  if(DAddress.isEmpty()){
            donorAddress.requestFocus();
            donorAddress.setError("Please enter your address");
            return;
       }  if(DMessage.isEmpty()){
            donorMessage.requestFocus();
            donorMessage.setError("Please enter your message");
            return;
       }
        Call<createDonorResponse> call=retrofitClient
                .getInstance()
                .getApi().createdonor(DName,DBloodGroup,DAddress,DNumber,DMessage);
       call.enqueue(new Callback<createDonorResponse>() {
           @Override
           public void onResponse(Call<createDonorResponse> call, Response<createDonorResponse> response) {
               createDonorResponse createDonorResponse=response.body();
               if(response.isSuccessful()){
                   Toast.makeText(Donor.this, createDonorResponse.getSuccess(), Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(Donor.this, createDonorResponse.getStatus(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<createDonorResponse> call, Throwable t) {

           }
       });
    }

}