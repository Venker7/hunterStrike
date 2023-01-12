package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class loginPage extends AppCompatActivity {
    private Button move1,move2;
    TextInputEditText email,password;
    String Email,Password;
    int flag1=1,flag2=1;
    String Correct_Email = "User@123" , Correct_password = "user1234";
    public static String PREFS_NAME="MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        SharedPreferences sharedPreferences = getSharedPreferences(loginPage.PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        move2 = findViewById(R.id.Sign_in);
        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = email.getText().toString();
                Password = password.getText().toString();
                if(Email.isEmpty())
                {
                    Toast.makeText(loginPage.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    flag1 = Correct_Email.compareTo(Email);
                    if(flag1!=0)
                    {
                        Toast.makeText(loginPage.this,"Please Enter valid email ",Toast.LENGTH_SHORT).show();
                    }

                    else{
                        if(Password.isEmpty())
                        {
                            Toast.makeText(loginPage.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                        }

                        else
                        {

                            if(Password.length() < 8)
                            {
                                Toast.makeText(loginPage.this,"Your password length should be at least 8 character",Toast.LENGTH_SHORT).show();
                            }

                            else
                            {
                                flag2 = Correct_password.compareTo(Password);
                                if(flag2!=0)
                                {
                                    Toast.makeText(loginPage.this,"Your password is incorrect",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if(flag1 == 0 & flag2 == 0 )
                                    {
                                        editor.putBoolean("hasLoggedIn",true);
                                        editor.commit();
                                        startActivity(new Intent(loginPage.this,MainActivity.class));
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }
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




    }
}