package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

public class Reciever extends AppCompatActivity {
    String[] type = {"A+","A-","B+","B-","AB+","AB-","0+","O-"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);
        autoCompleteTextView = findViewById(R.id.auto_text);
        adapterTypes = new ArrayAdapter<String>(this,R.layout.blood_group,type);
        autoCompleteTextView.setAdapter(adapterTypes);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterTypes.getItem(i).toString();

            }
        });
        ImageView arrow = findViewById(R.id.arrow_back);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reciever.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}