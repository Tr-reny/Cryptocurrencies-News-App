package com.tr_reny.cryptonews.Interface;

import com.tr_reny.cryptonews.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CryptocompareAPI {

    @GET("data/v2/news/?lang=EN")
    Call<News> getNews(
            @Header("Apikey") String apiKey);

}
