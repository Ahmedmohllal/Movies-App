package com.example.moviesapp.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesapp.Adapter.MovieRecyclerClickListener;
import com.example.moviesapp.Constants;
import com.example.moviesapp.Model.MovieModel;
import com.example.moviesapp.Adapter.MovieRecyclerAdapter;
import com.example.moviesapp.viewmodel.MovieViewModel;
import com.example.moviesapp.R;

import java.util.ArrayList;

public class PopularFragment extends Fragment implements MovieRecyclerClickListener {

    RecyclerView recyclerView;
    MovieRecyclerAdapter movieRecyclerAdapter;
    ArrayList<MovieModel> arrayList;
    Constants constants ;
    MovieViewModel movieViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.popular_rec);

        movieRecyclerAdapter = new MovieRecyclerAdapter(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();


        getData();
        return view;
    }


    private void getData(){
        movieViewModel =  new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.GetPopular();
        movieViewModel.getPopularMutableData().observe(getActivity(), new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> movieModels) {
                arrayList = movieModels;
                movieRecyclerAdapter.setArrayList(movieModels);
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        int id = arrayList.get(position).getMovie_id();
        //Toast.makeText(getActivity(), "id: "+id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),SingleMovieDetails.class);
        intent.putExtra("movie_id", String.valueOf(id));
        startActivity(intent);
    }
}