package com.example.moviesapp.di;

import android.content.Context;

import com.example.moviesapp.Constants;
import com.example.moviesapp.Network.MovieAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {

    public MovieAPI movieAPI ;
    Context context;
    Constants constants = new Constants(context);

    @Singleton
    @Provides
    public static MovieAPI getClient (){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .build()
                .create(MovieAPI.class);
    }
}
