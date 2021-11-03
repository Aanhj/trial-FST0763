package com.example.adapters;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fst_t0763.R;
import com.example.trial_fst0763.MovieDetail;

import java.util.ArrayList;
import java.util.List;

public class Movies_Adapter extends RecyclerView.Adapter<Movies_Adapter.viewHolder> {

    Context context;
    ArrayList<String> MovieTitles;
    List<Integer> MoviesIcons;

    public Movies_Adapter(Context context, ArrayList<String> movieTitles, List<Integer> moviesIcons) {
        this.context = context;
        MovieTitles = movieTitles;
        MoviesIcons = moviesIcons;
    }

    @NonNull
    @Override
    public Movies_Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Movies_Adapter.viewHolder holder, int position) {

        holder.Movies_text.setText(MovieTitles.get(position));
        holder.Movies_image.setImageResource(MoviesIcons.get(position));

        holder.Movies_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Integer positionID= holder.getAdapterPosition();
                MovieDetail movieDetail=new MovieDetail(positionID);
                AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.movie_frame_layout,movieDetail).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return MoviesIcons.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        FrameLayout frm;
        ImageView Movies_image;
        TextView Movies_text;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            frm=itemView.findViewById(R.id.frame_movie);
            Movies_image=itemView.findViewById(R.id.movie_image);
            Movies_text=itemView.findViewById(R.id.movie_name);




        return;

        }
    }
}