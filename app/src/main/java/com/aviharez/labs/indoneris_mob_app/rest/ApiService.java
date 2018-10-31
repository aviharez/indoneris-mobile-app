package com.aviharez.labs.indoneris_mob_app.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/login.php")
    Call<ResponseBody> loginRequest(@Field("key") String key,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("token") String token);



}
