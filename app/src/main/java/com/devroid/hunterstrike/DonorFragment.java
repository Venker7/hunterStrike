package com.devroid.hunterstrike;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


public class DonorFragment extends Fragment {
String sBaseUrl = "https://api.instantwebtools.net/v1/";
   RecyclerView recyclerView;
   Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_donor, container, false);
        recyclerView = view.findViewById(R.id.donRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Bundle bundle = getArguments();

        if (bundle != null) {
            String donor_name = bundle.getString("name");
            String donor_blood_type = bundle.getString("blood_type");
            String donor_contact = bundle.getString("contact");
            String donor_address = bundle.getString("address");
            // Find the TextViews in the CardView
            TextView DonorName = view.findViewById(R.id.donor_name);
            TextView DonorType = view.findViewById(R.id.donor_blood_group);
            TextView DonorContact = view.findViewById(R.id.donor_phone_no);
            TextView DonorAddress = view.findViewById(R.id.donor_address);
            // Set the data to the TextViews
            DonorName.setText(donor_name);
            DonorType.setText(donor_blood_type);
            DonorContact.setText(donor_contact);
            DonorAddress.setText(donor_address);

        }

            return view;


    }
}