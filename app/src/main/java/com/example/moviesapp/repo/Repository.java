package com.example.moviesapp.repo;

import android.content.Context;

import com.example.moviesapp.Constants;
import com.example.moviesapp.Network.MovieAPI;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public class Repository {

    MovieAPI movieAPI;
    Context context;
    Constants constants = new Constants(context);

    @Inject
    public Repository(MovieAPI movieAPI) {
        this.movieAPI = movieAPI;
    }

    public Observable<ResponseBody> getAllMovie(){
        return movieAPI.getAllMovie(constants.API_KEY);
    }

    public Observable<ResponseBody> getTopRated(){
        return movieAPI.getTopRatedMovie(constants.API_KEY);
    }

    public Observable<ResponseBody> getUpComing(){
        return movieAPI.getUpComingMovie(constants.API_KEY);
    }

    public Single<ResponseBody> getMovieDetails(int id){
        return movieAPI.getMovieDetails(id,constants.API_KEY);
    }
}
