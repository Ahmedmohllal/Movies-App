package com.example.moviesapp.Model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {

    @SerializedName("original_title")
    String movieName;

    @SerializedName("vote_average")
    double voteNum;

    @SerializedName("overview")
    String overview;

    @SerializedName("poster_path")
    String imgUrl;

    @SerializedName("release_date")
    String release_date;

    @SerializedName("tagline")
    String tagLine;

    @SerializedName("status")
    String status;

    @SerializedName("id")
    int movie_id;


    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public MovieModel(String movieName, double voteNum, String overview, String imgUrl, String release_date, String tagLine, String status) {
        this.movieName = movieName;
        this.voteNum = voteNum;
        this.overview = overview;
        this.imgUrl = imgUrl;
        this.release_date = release_date;
        this.tagLine = tagLine;
        this.status = status;
    }

    public MovieModel(String movieName, double voteNum, String release_date, String imgUrl, int movie_id) {
        this.movieName = movieName;
        this.voteNum = voteNum;
        this.release_date = release_date;
        this.imgUrl = imgUrl;
        this.movie_id = movie_id;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public double getVoteNum() {
        return voteNum;
    }

    public String getOverview() {
        return overview;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
