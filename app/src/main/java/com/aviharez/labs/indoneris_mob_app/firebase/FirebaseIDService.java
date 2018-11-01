package com.aviharez.labs.indoneris_mob_app.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.aviharez.labs.indoneris_mob_app.util.Config;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FirebaseIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();

        storeRegIdIn(token);

        registerToken(token);

        Intent regComplete = new Intent(Config.REGISTRATION_COMPLETE);
        regComplete.putExtra("token", token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(regComplete);

        Log.e(TAG, "Refreshed token: " + token);
    }

    private void storeRegIdIn(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.apply();
    }

    private void registerToken(String token) {
        OkHttpClient client = new OkHttpClient();
        SharedPreferences pref = getSharedPreferences(Config.SHARED_PREF, MODE_PRIVATE);
        final int login = pref.getInt("login", 0);
        if (login == 1) {
            String username = pref.getString("username", "gagal");
            /*RequestBody body = new FormBody.Builder().add("token", token).add("username", username).build();
            Request request = new Request.Builder().url(Config.mainUrl).post(body).build();
            try {
                client.newCall(request).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }
}
