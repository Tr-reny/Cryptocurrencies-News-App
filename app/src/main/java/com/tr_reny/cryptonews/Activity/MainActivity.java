package com.tr_reny.cryptonews.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.tr_reny.cryptonews.Adapter.NewsAdapter;
import com.tr_reny.cryptonews.BottomNavigationBehavior;
import com.tr_reny.cryptonews.DarkModePrefManager;
import com.tr_reny.cryptonews.Interface.MboumFinanceAPI;
import com.tr_reny.cryptonews.Model.News;
import com.tr_reny.cryptonews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Created by Tr_reny (Reny Kipkoech) on 4th Sep 2022
     * This is a Crypto News App that Gets Information from Crypto News API
     */
    private BottomNavigationView bottomNavigationView;

    private MboumFinanceAPI mboumFinanceAPI;
    private List<News> newsList;
    private RecyclerView recyclerView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigationMyProfile:
                    return true;
                case R.id.navigationMyCourses:
                    return true;
                case R.id.navigationHome:
                    return true;
                case R.id.navigationSearch:
                    return true;
                case R.id.navigationMenu:
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.openDrawer(GravityCompat.START);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (new DarkModePrefManager(this).isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        bottomNavigationView.setSelectedItemId(R.id.navigationHome);


        newsList = new ArrayList<>();


        //Retrofit
        String url = "https://mboum-finance.p.rapidapi.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mboumFinanceAPI = retrofit.create(MboumFinanceAPI.class);
        getNews();


    }

    private void getNews(){
        Call<List<News>> call = mboumFinanceAPI.getMarketNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Code: " + response.code() ,Toast.LENGTH_LONG);
                    return;
                }
                List<News> newsList1 = response.body();
                for (News news : newsList1){
              /*     String content = "";
                    content += "title: " + news.getTitle() + "\n";
                    content += "Source: " + news.getMsource() + "\n\n";
                    tv_results.append(content);*/

                    newsList.add(news);
            }

                PutDataIntoRecylerView(newsList);

            }


            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage() , Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


    private void PutDataIntoRecylerView(List<News> newsList) {

        NewsAdapter newsAdapter = new NewsAdapter(this,newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_dark_mode) {
            //code for setting dark mode
            //true for dark mode, false for day mode, currently toggling on each click
            DarkModePrefManager darkModePrefManager = new DarkModePrefManager(this);
            darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


