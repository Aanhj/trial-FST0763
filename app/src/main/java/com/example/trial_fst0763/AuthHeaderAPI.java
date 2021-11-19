package com.example.trial_fst0763;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fst_t0763.R;


public class AuthHeaderAPI extends Fragment {

Button AuthApiButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_auth_header_api, container, false);
       AuthApiButton=v.findViewById(R.id.auth_header_button);
       AuthApiButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sendRequest();
           }
       });

        return v;
    }

    private void sendRequest() {

    }
}