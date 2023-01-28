package com.devroid.hunterstrike;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.devroid.hunterstrike.ModelResponse.loginResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class loginPage extends AppCompatActivity {
    private Button move1,move2;
    TextInputEditText email,password;
    public static String PREFS_NAME="MyPrefsFile";

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

    private Context login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        SharedPreferences sharedPreferences = getSharedPreferences(loginPage.PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        move2 = findViewById(R.id.Sign_in);
        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=email.getText().toString();
                String userPassword=password.getText().toString();
                if(userEmail.isEmpty()){
                    Toast.makeText(loginPage.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                        Toast.makeText(loginPage.this,"Please Enter valid email ",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(userPassword.isEmpty()){
                            Toast.makeText(loginPage.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            userLogin();
                        }
                    }
                }

            }

            private void userLogin() {
                String userEmail=email.getText().toString();
                String userPassword=password.getText().toString();
                Call<loginResponse> call= retrofitClient.getInstance().getApi().login(userEmail,userPassword);
                call.enqueue(new Callback<loginResponse>() {
                    @Override
                    public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                        loginResponse loginResponse=response.body();
                        if(response.isSuccessful()){
                            editor.putBoolean("hasLoggedIn",true);
                            editor.commit();
                            startActivity(new Intent(loginPage.this,MainActivity.class));
                            Toast.makeText(loginPage.this, "hello gyus", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<loginResponse> call, Throwable t) {
                        Toast.makeText(loginPage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        move1=findViewById(R.id.sign_up);
        move1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginPage.this,Registration.class);
                startActivity(intent);
            }
        });

    googleBtn = findViewById(R.id.google_btn);
    gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    gsc = GoogleSignIn.getClient(this,gso);

    googleBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            signIn();
        }
    });


    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToHomeFragment();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
            }
        }
    }

    void navigateToHomeFragment(){
        finish();
        Intent intent = new Intent(loginPage.this,MainActivity.class);
        startActivity(intent);
    }

}