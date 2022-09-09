package com.tr_reny.cryptonews.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.tr_reny.cryptonews.Adapter.NewsAdapter;
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

/**
 * Created by Reny Kipkoech on 19th AUG 2022
 * This is an App which shows the functionality of Retrofit with RecycleView and SearchView together
 */
public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSearch;
    private ArrayList<News> arrayListPost;
    private NewsAdapter myAdapter;
    private MboumFinanceAPI mboumFinanceAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewSearch);
        arrayListPost = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mboumFinanceAPI = retrofit.create(MboumFinanceAPI.class);
        getNews();
    }

    private void getNews() {
        Call<List<News>> call = mboumFinanceAPI.getMarketNews("mboum-finance.p.rapidapi.com", "7b17418753msh4f16608e0aa78d7p1a6fe6jsnfc06e90efe18");
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SearchActivity.this, "Code: " + response.code(), Toast.LENGTH_LONG);
                    return;
                }
                List<News> newsList1 = response.body();
                for (News news : newsList1) {

                    arrayListPost.add(news);
                }

                PutDataIntoRecylerView(arrayListPost);

            }


            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void PutDataIntoRecylerView(ArrayList<News> arrayListPost) {

        NewsAdapter newsAdapter = new NewsAdapter(this, arrayListPost);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearch.setAdapter(newsAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        getMenuInflater().inflate(R.menu.search_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}

