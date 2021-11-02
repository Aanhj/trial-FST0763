package com.example.trial_fst0763;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fst_t0763.R;


public class MovieDetail extends Fragment {
    FrameLayout frameLayout;
    Integer positionID;
    ImageView Movieimage;
    TextView MovieTitle,MovieDescription;
    Button BookTicket;

    public MovieDetail(Integer positionID) {
        this.positionID = positionID;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Movieimage=v.findViewById(R.id.Movie_icon);
        MovieTitle=v.findViewById(R.id.text_movie_title);
        MovieDescription=v.findViewById(R.id.text_movie_description);
        BookTicket=v.findViewById(R.id.book_ticket_button);
        frameLayout=v.findViewById(R.id.movie_detail_frame);
        setData(positionID);
        AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();

        BookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.removeAllViews();
                NumberOfTicket numberOfTicket=new NumberOfTicket();

                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_frame,numberOfTicket).addToBackStack(null)
                        .commit();

            }
        });

        return v;
    }



    private void setData(Integer positionID) {

        switch (positionID){

            case 0:
                Movieimage.setImageResource(R.drawable.m1);
                MovieTitle.setText("Phir Hera Pheri");
                MovieDescription.setText(getString(R.string.m1_description));
                break;

            case 1:
                Movieimage.setImageResource(R.drawable.m2);
                MovieTitle.setText("Golmaal");
                MovieDescription.setText(getString(R.string.m2_description));
                break;
            case 2:
                Movieimage.setImageResource(R.drawable.m3);
                MovieTitle.setText("The Dictator");
                MovieDescription.setText(getString(R.string.m3_description));
                break;

            case 3:
                Movieimage.setImageResource(R.drawable.m4);
                MovieTitle.setText("Ace Ventura");
                MovieDescription.setText(getString(R.string.m4_description));
                break;
            case 4:
                Movieimage.setImageResource(R.drawable.m5);
                MovieTitle.setText("Inception");
                MovieDescription.setText(getString(R.string.m5_description));
                break;

        }

    }


}