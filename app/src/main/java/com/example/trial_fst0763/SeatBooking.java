package com.example.trial_fst0763;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SeatBooking extends Fragment {
    int TotalSeats, checker = 1;


    public SeatBooking(Integer totalSeats) {
        this.TotalSeats = totalSeats;
    }

    GridView seatGrid;
    Button pay;
    boolean SELECTOR = true;
    FrameLayout seat_frame;
    Integer pos;

    //String[] selected_seat=new String[TotalSeats];
//    Integer selected_seat [];
    ArrayList<Integer> selected_seat = new ArrayList<>();
    ArrayList<Integer> validator = new ArrayList<>();
    ArrayList<Integer> AllSeats = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_seat_booking, container, false);
        seatGrid = v.findViewById(R.id.seatGrid);
        for (int i = 0; i <= 49; i++) {
            AllSeats.add(i);
        }


        seatGrid.setAdapter(new SeatBooking_Adapter());
        seat_frame = v.findViewById(R.id.seatBooking_frame);
        pay = v.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_seat.size() < TotalSeats | selected_seat.size() > TotalSeats ) {
                    Toast.makeText(getContext(), "Please select appropriate no of seats", Toast.LENGTH_SHORT).show();

                } else if (selected_seat.contains(pos) != seatGrid.getChildAt(pos).isEnabled()) {
                    seatGrid.getChildAt(pos).setClickable(false);
                } else {


                    for (int i = 0; i < selected_seat.size(); i++) {
                        validator.add(selected_seat.get(i));
                    }
                    //Toast.makeText(getContext(), validator.toString(), Toast.LENGTH_SHORT).show();

/*
                    seat_frame.removeAllViews();
                    *//*Integer tickets=TotalSeats;*//*

                    //AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                    Intent intent = new Intent(getContext(), Payments.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) validator);
                    intent.putExtra("TicketData", args);
                    startActivity(intent);*/


                }
            }
        });


/*
        if (selected_seat.contains(pos) != seatGrid.getChildAt(pos).isEnabled()) {
            seatGrid.getChildAt(pos).setClickable(false);
        }
        */




        seatGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = view.findViewById(R.id.one_seat);

                if (selected_seat.contains(position)) {
                    checker--;
                    img.setImageResource(R.drawable.before_booking);
                    selected_seat.remove(selected_seat.indexOf(position));
                } else if (checker == TotalSeats/*|checker>TotalSeats*/) {
                    selected_seat.add(position);
                    img.setImageResource(R.drawable.after_booking);


                    /*if (selected_seat.contains(position) != seatGrid.getChildAt(position).isEnabled()) {
                        seatGrid.getChildAt(position).setClickable(false);
                    }*/

                    Toast.makeText(getContext(), "Completed", Toast.LENGTH_SHORT).show();
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

    private boolean seatAreLess(int totalSeats) {

        return true;
    }


}


