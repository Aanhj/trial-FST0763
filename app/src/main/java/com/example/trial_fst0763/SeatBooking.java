package com.example.trial_fst0763;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adapters.SeatBooking_Adapter;
import com.example.fst_t0763.R;

import java.util.ArrayList;

public class SeatBooking extends Fragment {
    int TotalSeats, checker = 0 ;

    public SeatBooking(Integer totalSeats) {
        this.TotalSeats = totalSeats;
    }
    GridView seatGrid;
    Button pay;
    boolean SELECTOR = true;
    //String[] selected_seat=new String[TotalSeats];
    ArrayList<Integer> selected_seat=new ArrayList<Integer>();

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

        seatGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView img = view.findViewById(R.id.one_seat);
                img.setImageResource(R.drawable.after_booking);

                checker++;
                if(checker >=TotalSeats){
                    selected_seat.add(position);
                    for (int i=0;i<=selected_seat.size();i++)
                    {
                        for (int j=0;j<=selected_seat.size();j++){
                            if (selected_seat.get(i)==selected_seat.get(j))
                            {
                                Toast.makeText(getContext(), "Cannot select seat 2 times", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                    seatGrid.setEnabled(false);
                    Toast.makeText(getContext()," Successful",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getContext()," Select more ",Toast.LENGTH_SHORT).show();

                }

            }
        });







        return v;
    }

}