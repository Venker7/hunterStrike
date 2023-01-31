package com.devroid.hunterstrike;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class ProfileFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
Activity context;
private TextView  name,email,number;
private GoogleApiClient googleApiClient;
private GoogleSignInOptions gso;
Button edit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
       LinearLayout logout = view.findViewById(R.id.logout_layout);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(loginPage.PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        email=view.findViewById(R.id.email_text_user);
        number=view.findViewById(R.id.phone_no_user);
        name=view.findViewById(R.id.name);
        context = getActivity();
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(getActivity()).enableAutoManage(getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                   @Override
                   public void onResult(@NonNull Status status) {
                       if(status.isSuccess()){
                           gotoLoginActivity();
                           editor.putBoolean("hasLoggedIn",false);
                           editor.commit();
                       }
                       else
                           Toast.makeText(context, "Log out Failed", Toast.LENGTH_SHORT).show();
                   }

               });

           }


       });
      

       LinearLayout eligibility = view.findViewById(R.id.eligibility_layout);
       eligibility.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context,Eligibility.class);
               startActivity(intent);
           }
       });
        LinearLayout compatibility = view.findViewById(R.id.compatibility_layout);
        compatibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Compatibility.class);
                startActivity(intent);
            }
        });
        LinearLayout bloodfact = view.findViewById(R.id.blood_facts_layout);
        bloodfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Blood_Facts.class);
                startActivity(intent);
            }
        });
        edit = view.findViewById(R.id.edit_profile);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditProfile.class);
                startActivity(intent);
            }
        });
        return  view;



    }
    private void gotoLoginActivity() {
        startActivity(new Intent(context,loginPage.class));
        getActivity().finish();
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }
    private  void  handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            name.setText(account.getGivenName());
            email.setText(account.getEmail());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}