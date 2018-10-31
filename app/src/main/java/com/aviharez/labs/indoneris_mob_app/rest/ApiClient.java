package com.aviharez.labs.indoneris_mob_app.rest;

import com.aviharez.labs.indoneris_mob_app.util.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.mainUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getAPIService() {
        return getClient().create(ApiService.class);
    }

}
