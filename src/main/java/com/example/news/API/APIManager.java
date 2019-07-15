package com.example.news.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIManager {
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        //b157611e9895467ebfb67ff2c10f9c5b
        if(retrofit==null){
            retrofit =new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static WebServices getApis(){
        return  getInstance().create(WebServices.class);
    }
}
