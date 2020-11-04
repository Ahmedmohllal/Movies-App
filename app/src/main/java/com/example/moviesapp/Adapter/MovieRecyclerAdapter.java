package com.example.moviesapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Model.MovieModel;
import com.example.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {
    ArrayList<MovieModel> arrayList = new ArrayList<>();
    MovieRecyclerClickListener movieRecyclerClickListener;

    public MovieRecyclerAdapter( MovieRecyclerClickListener movieRecyclerClickListener) {
        this.movieRecyclerClickListener = movieRecyclerClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.movieName.setText(arrayList.get(position).getMovieName());
        holder.releaseDate.setText(arrayList.get(position).getRelease_date());
        double x = arrayList.get(position).getVoteNum()*10;
        int y = (int)x;
        holder.userScore.setText(y+"%");

        Picasso.get().load(arrayList.get(position).getImgUrl()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setArrayList(ArrayList<MovieModel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieName,releaseDate,userScore;
        ImageView moviePoster;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movie_name);
            releaseDate = itemView.findViewById(R.id.release_date_View);
            userScore = itemView.findViewById(R.id.user_score_View);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieRecyclerClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
