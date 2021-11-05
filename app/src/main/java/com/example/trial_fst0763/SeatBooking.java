package com.example.trial_fst0763;

import android.content.ClipData;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adapters.SeatBooking_Adapter;
import com.example.fst_t0763.R;

import java.util.ArrayList;

public class SeatBooking extends Fragment {
    int TotalSeats//
            , checker = 1;

    public SeatBooking(Integer totalSeats) {
        this.TotalSeats = totalSeats;
    }

    GridView seatGrid;
    Button pay;
    boolean SELECTOR = true;
    FrameLayout seatBooking_frame;

    //String[] selected_seat=new String[TotalSeats];
    ArrayList<Integer> selected_seat = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_seat_booking, container, false);
        seatGrid = v.findViewById(R.id.seatGrid);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        //seatGrid.setLayoutManager(linearLayoutManager);
        seatGrid.setAdapter(new SeatBooking_Adapter());
        seatBooking_frame=v.findViewById(R.id.seatBooking_frame);

        pay=v.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment pay=new Payment();
                AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.seatBooking_frame,pay).commit();

            }
        });


/*

        seatGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView img = view.findViewById(R.id.one_seat);
                img.setImageResource(R.drawable.after_booking);

                checker++;
                if(checker >=TotalSeats){
                    selected_seat.add(position);

                    seatGrid.setEnabled(false);
                    Toast.makeText(getContext()," Successful",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getContext()," Select more ",Toast.LENGTH_SHORT).show();

                }

                seatSelected();




            }
        });

*/

        seatGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = view.findViewById(R.id.one_seat);
                 //3  3
                if (checker > TotalSeats) {

                    seatGrid.setEnabled(false);
                    Toast.makeText(getContext(), " Successful", Toast.LENGTH_SHORT).show();
                } else {
                    checker++;
                    selected_seat.add(position);
                    img.setImageResource(R.drawable.after_booking);
                    Toast.makeText(getContext(), " Select more ", Toast.LENGTH_SHORT).show();
                }

            }


        });


        return v;
    }


}