package com.tr_reny.cryptonews.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface JsonServeAPI {

    @GET("data/v2/news/?lang=EN")
    Call<News> getNewsRealtime(
            @Header("Apikey") String apiKey);


    //    https://api.jsonserve.com/HCiQ5c
    @GET("data/v2/news/?categories=BTC,ETH&excludeCategories=Sponsored")
    Call<News> getNews();

}
