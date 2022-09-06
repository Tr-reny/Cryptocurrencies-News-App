package com.tr_reny.cryptonews.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MboumFinanceAPI {

    @GET("ne/news")
    Call<List<MboumFinanceAPI>> getMarketNews(
            @Header("X-RapidAPI-Host") String api,
            @Header("X-RapidAPI-Key") String apiKey);
}
