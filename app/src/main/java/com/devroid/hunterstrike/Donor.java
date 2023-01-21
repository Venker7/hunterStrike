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

public class Donor extends AppCompatActivity {
String[] type = {"A+","A-","B+","B-","AB+","AB-","0+","O-"};
String type2;
AutoCompleteTextView autoCompleteTextView;
ArrayAdapter<String> adapterTypes;
EditText Donor_name,Donor_contact,Donor_address,Donor_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        Button post = findViewById(R.id.post_donor);
        Donor_name = findViewById(R.id.donor_name);
        Donor_address = findViewById(R.id.donor_address);
        Donor_contact = findViewById(R.id.donor_contact);
        Donor_message = findViewById(R.id.donor_message);
        autoCompleteTextView = findViewById(R.id.auto_text);
        adapterTypes = new ArrayAdapter<String>(this,R.layout.blood_group,type);
        autoCompleteTextView.setAdapter(adapterTypes);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                type2 = adapterTypes.getItem(i).toString();

            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donor_name = Donor_name.getText().toString();
                String donor_address= Donor_address.getText().toString();
                String donor_contact = Donor_contact.getText().toString();
                String donor_message = Donor_message.getText().toString();

                if(donor_name.isEmpty())
                {
                    Toast.makeText(Donor.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if(type2.isEmpty())
                    {
                        Toast.makeText(Donor.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        if(donor_address.isEmpty())
                        {
                            Toast.makeText(Donor.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            if(donor_contact.isEmpty())
                            {
                                Toast.makeText(Donor.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else {

                            }

                        }
                    }
                }
            }
        });


    }
}