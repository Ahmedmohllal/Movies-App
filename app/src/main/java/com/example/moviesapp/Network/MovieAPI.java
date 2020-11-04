package com.example.moviesapp.Network;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("movie/popular")
    Observable<ResponseBody> getAllMovie(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<ResponseBody> getTopRatedMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Observable<ResponseBody> getUpComingMovie(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Single<ResponseBody> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey);

}
