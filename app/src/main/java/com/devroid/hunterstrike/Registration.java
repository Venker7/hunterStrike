package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.registerResponse;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    TextInputEditText username,email,contact,user_password,confirm_password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username=findViewById(R.id.uname);
        email=findViewById(R.id.uemail);
        contact=findViewById(R.id.ucontact);
        user_password=findViewById(R.id.upassword);
        confirm_password=findViewById(R.id.uconfirm);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    private void registerUser() {

        String userName = username.getText().toString();
        String userEmail= email.getText().toString();
        String userContact=contact.getText().toString();
        int number=Integer.parseInt(userContact);
        String userPassword= user_password.getText().toString();
        String userConfirmPassword= confirm_password.getText().toString();

        if(userName.isEmpty()){
            username.requestFocus();
            username.setError("Please enter your name");
            return;
        }
        if(userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please enter your mail");
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Please enter correct email address");
            return;

        }
        if(userPassword.isEmpty()){
            user_password.requestFocus();
            user_password.setError("Please enter your password");
            return;

        }
        if(userPassword.length()<8){
            user_password.requestFocus();
            user_password.setError("Minimum 8 letters");
            return;

        }
        if(userConfirmPassword.isEmpty()){
            confirm_password.requestFocus();
            confirm_password.setError("Please enter your confirm password");
            return;

        }

        Call<registerResponse> call=retrofitClient
                .getInstance()
                .getApi().register(userName,userEmail,number,userPassword,userConfirmPassword);
        call.enqueue(new Callback<registerResponse>() {
            @Override
            public void onResponse(Call<registerResponse>  call, Response<registerResponse> response) {
            registerResponse registerResponse=response.body();
            if(response.isSuccessful()){
                Intent intent=new Intent( Registration.this,loginPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                Toast.makeText(Registration.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Registration.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<registerResponse> call, Throwable t) {
                Toast.makeText(Registration.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}