package com.example.moviesapp;

import android.content.Context;

import com.example.moviesapp.Model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Constants {
    private Context context;
    public String API_KEY = "ac87886f08a70ee0cd8a7c1531eed881";
    public String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    ArrayList<MovieModel> arrayList = new ArrayList<>();

    public Constants(Context context){
        this.context = context;
    }


    public ArrayList<MovieModel> convertResponse(String result){
        JSONObject jsonObject2;
        String movieName,release_date, imgUrl, conImageURL;
        double voteNum;
        int movie_id;
        MovieModel movieModel;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray all_Data = jsonObject.getJSONArray("results");

            for (int i = 0 ; i < all_Data.length() ; i++){
                jsonObject2 = all_Data.getJSONObject(i);
                movieName = jsonObject2.getString("original_title");
                release_date = jsonObject2.getString("release_date");
                voteNum = jsonObject2.getDouble("vote_average");
                movie_id = jsonObject2.getInt("id");

                imgUrl = jsonObject2.getString("poster_path");
                conImageURL = IMAGE_BASE_URL+imgUrl;
                movieModel = new MovieModel(movieName, voteNum, release_date, conImageURL, movie_id);
                arrayList.add(movieModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
