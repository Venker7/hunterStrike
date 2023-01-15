package com.devroid.hunterstrike;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class ProfileFragment extends Fragment {
Activity context;
Button edit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
       LinearLayout logout = view.findViewById(R.id.logout_layout);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(loginPage.PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       context = getActivity();
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context,loginPage.class);editor.putBoolean("hasLoggedIn",false);
               editor.commit();
               startActivity(intent);
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


}