package com.aviharez.labs.indoneris_mob_app.rest;

import com.aviharez.labs.indoneris_mob_app.entity.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/login.php")
    Call<ResponseBody> loginRequest(@Field("key") String key,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("token") String token);

    @FormUrlEncoded
    @POST("shared/event/show.php")
    Call<List<NextEvent>> listNextEventMhs(@Field("token") String token,
                                           @Field("action") String action,
                                           @Field("key") String key);

    @FormUrlEncoded
    @POST("mhs/user/history.php")
    Call<List<RiwayatPoin>> listRiwayat(@Field("token") String token,
                                        @Field("key") String key,
                                        @Field("history") String history);

    @FormUrlEncoded
    @POST("mhs/user/profile.php")
    Call<ProfilMhs> profilMahasiswa(@Field("token") String token,
                                    @Field("detail") String detail,
                                    @Field("key") String key);

    @FormUrlEncoded
    @POST("shared/master/jurusan.php")
    Call<List<Prodi>> listProdi(@Field("token") String token,
                                @Field("key") String key,
                                @Field("lists") String lists);

    @FormUrlEncoded
    @POST("shared/master/jurusan.php")
    Call<List<Kelas>> listKelas(@Field("token") String token,
                                @Field("key") String key,
                                @Field("lists") String lists,
                                @Field("id_jurusan") String id);

    @FormUrlEncoded
    @POST("admin/mahasiswa/show.php")
    Call<List<DataMhs>> dataMahasiswa(@Field("key") String key,
                                      @Field("show") String show,
                                      @Field("token") String token,
                                      @Field("id_kelas") String id);

}
