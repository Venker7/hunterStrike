package com.devroid.hunterstrike;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.home_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.message:
                Intent message=new Intent(Intent.ACTION_VIEW);
                message.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+919863059696"+ "&text="));
                startActivity(message);
                Toast.makeText(getActivity(), "You have press message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"check this cool app");
                intent.putExtra(Intent.EXTRA_TEXT,"Application link here:- https://www.youtube.com/watch?v=ovD8zJKkgZ4");
                startActivity(Intent.createChooser(intent,"Share via"));
                break;

            case R.id.help:
                break;

            case R.id.feedback:
                Intent intent1 =new Intent(Intent.ACTION_SENDTO);
                String Uritext ="mailto:"+ Uri.encode("konthoujamlanthoiba@gmail.com")+"?subject="+
                        Uri.encode("Feedback ");
                Uri uri=Uri.parse(Uritext);
                intent1.setData(uri);
                startActivity(Intent.createChooser(intent1,"send email"));
                Toast.makeText(getActivity(), "You have press feedback", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;



        }
        return super.onOptionsItemSelected(item);
    }
}