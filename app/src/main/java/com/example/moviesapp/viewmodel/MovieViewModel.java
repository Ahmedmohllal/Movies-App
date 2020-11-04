package com.example.moviesapp.viewmodel;

import android.content.Context;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.Constants;
import com.example.moviesapp.Model.MovieModel;
import com.example.moviesapp.Network.MovieAPI;
import com.example.moviesapp.UI.PopularFragment;
import com.example.moviesapp.repo.Repository;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MovieViewModel extends ViewModel {
    Context context;
    ArrayList<MovieModel> arrayList = new ArrayList<>();
    public Repository repository;
    Constants constants = new Constants(context);
    MovieAPI movieAPI;

    @ViewModelInject
    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    MutableLiveData<ArrayList<MovieModel>> popularMutableData = new MutableLiveData<>();
    MutableLiveData<ArrayList<MovieModel>> ratedMutableData = new MutableLiveData<>();
    MutableLiveData<ArrayList<MovieModel>> upcomingMutableData = new MutableLiveData<>();
    MutableLiveData<ResponseBody> responseBodyMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ResponseBody> getResponseBodyMutableLiveData() {
        return responseBodyMutableLiveData;
    }

    public MutableLiveData<ArrayList<MovieModel>> getPopularMutableData() {
        return popularMutableData;
    }

    public MutableLiveData<ArrayList<MovieModel>> getRatedMutableData() {
        return ratedMutableData;
    }

    public MutableLiveData<ArrayList<MovieModel>> getUpcomingMutableData() {
        return upcomingMutableData;
    }


    //get data from API
    public void GetPopular(){
        Observable<ResponseBody> observable = repository.getAllMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    arrayList = constants.convertResponse(result);
                    popularMutableData.setValue(arrayList);
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getTopRated(){

        Observable<ResponseBody> observable = repository.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    arrayList = constants.convertResponse(result);
                    ratedMutableData.setValue(arrayList);
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getUpComingMovies(){
        Observable<ResponseBody> observable = repository.getUpComing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    arrayList = constants.convertResponse(result);
                    upcomingMutableData.postValue(arrayList);
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void getMovieDetails(int id){
        Single<ResponseBody> single = repository.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<ResponseBody> observer = new SingleObserver<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ResponseBody responseBody) {
                responseBodyMutableLiveData.setValue(responseBody);

            }

            @Override
            public void onError(Throwable e) {

            }
        };
        single.subscribe(observer);
    }



}
