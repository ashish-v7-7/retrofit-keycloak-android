package com.protium.protiumretrofitkeycloak.keycloak;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
  //  private static final String BASE_URL = "http://10.0.2.2:8080"; for emulator
    private static final String BASE_URL = "http://192.168.1.9:8080";  //for real device

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
