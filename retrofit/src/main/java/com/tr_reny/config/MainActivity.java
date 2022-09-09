package com.tr_reny.config;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<News> newsList;
    MboumFinanceAPI mboumFinanceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        newsList = new ArrayList<>();

        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mboumFinanceAPI= retrofit.create(MboumFinanceAPI.class);
        getNews();

    }

    private void getNews(){
        Call<List<News>> call = mboumFinanceAPI.getMarketNews("https://mboum-finance.p.rapidapi.com", "7b17418753msh4f16608e0aa78d7p1a6fe6jsnfc06e90efe18");
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}