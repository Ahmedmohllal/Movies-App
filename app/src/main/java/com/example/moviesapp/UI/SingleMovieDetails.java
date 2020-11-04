package com.example.moviesapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesapp.Constants;
import com.example.moviesapp.Network.MovieAPI;
import com.example.moviesapp.R;
import com.example.moviesapp.viewmodel.MovieViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.ResponseBody;

@AndroidEntryPoint
public class SingleMovieDetails extends AppCompatActivity {


    @BindView(R.id.movie_title)
    TextView movieName_view;
    @BindView(R.id.movie_tagline)
    TextView movieTageLine;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.movie_rating)
    TextView movie_rating;
    @BindView(R.id.movie_status)
    TextView movieStatus;
    @BindView(R.id.movie_overview)
    TextView movie_overView;
    @BindView(R.id.iv_movie_poster)
    ImageView movieImage;
    Constants constants;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie_details);
        ButterKnife.bind(this);
        Toolbar mToolbar = findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Movie Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String movie_id = getIntent().getStringExtra("movie_id");
        int id = Integer.parseInt(movie_id);
        Toast.makeText(this,""+id,Toast.LENGTH_LONG).show();

        constants = new Constants(this);
        fetchDataFromViewModel(id);

    }
    public void fetchDataFromViewModel(int id){
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovieDetails(id);
        movieViewModel.getResponseBodyMutableLiveData().observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                getData(responseBody);

            }
        });
    }

    private void getData(ResponseBody responseBody){
        try {
            String result = responseBody.string();
            JSONObject jsonObject = new JSONObject(result);
            String posterImage = jsonObject.getString("poster_path");
            String title = jsonObject.getString("original_title");
            String overview = jsonObject.getString("overview");
            String status = jsonObject.getString("status");
            String tagLine = jsonObject.getString("tagline");
            String releaseDate = jsonObject.getString("release_date");
            double vote = jsonObject.getDouble("vote_average");
            int rate = (int)vote;
            String imgUrl = constants.IMAGE_BASE_URL+posterImage;
            Picasso.get().load(imgUrl).into(movieImage);
            movieName_view.setText(title);
            movieTageLine.setText(tagLine);
            movie_overView.setText(overview);
            movie_rating.setText(rate+"%");
            movieReleaseDate.setText(releaseDate);
            movieStatus.setText(status);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logOut){
            //Do something
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(SingleMovieDetails.this, LoginActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}