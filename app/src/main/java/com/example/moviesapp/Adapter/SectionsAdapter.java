package com.example.moviesapp.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moviesapp.UI.IncomingFragment;
import com.example.moviesapp.UI.PopularFragment;
import com.example.moviesapp.UI.TopRatedFragment;

public class SectionsAdapter extends FragmentPagerAdapter {
    public SectionsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PopularFragment popularFragment = new PopularFragment();
                return popularFragment;
            case 1:
                TopRatedFragment topRatedFragment = new TopRatedFragment();
                return topRatedFragment;
            case 2:
                IncomingFragment incomingFragment = new IncomingFragment();
                return incomingFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Popular";
            case 1:
                return "Top-Rated";
            case 2:
                return "Incoming";
            default:
                return null;
        }
    }
}
