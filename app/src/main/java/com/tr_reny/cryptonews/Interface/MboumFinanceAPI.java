package com.tr_reny.cryptonews.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MboumFinanceAPI {
    //.url("https://mboum-finance.p.rapidapi.com/ne/news")
    //	.addHeader("X-RapidAPI-Key", "7b17418753msh4f16608e0aa78d7p1a6fe6jsnfc06e90efe18")
    //	.addHeader("X-RapidAPI-Host", "mboum-finance.p.rapidapi.com")

    @GET("ne/news")
    Call<List<MboumFinanceAPI>> getMarketNews(
            @Header("X-RapidAPI-Host") String api,
            @Header("X-RapidAPI-Key") String apiKey);
}
