package com.aviharez.labs.indoneris_mob_app.feature;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.feature.admin.main.AdminMainActivity;
import com.aviharez.labs.indoneris_mob_app.feature.mhs.MhsMainActivity;
import com.aviharez.labs.indoneris_mob_app.rest.ApiClient;
import com.aviharez.labs.indoneris_mob_app.rest.ApiService;
import com.aviharez.labs.indoneris_mob_app.util.Config;
import com.google.firebase.iid.FirebaseInstanceId;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button bt_login;
    private EditText et_user, et_pass;
    private Dialog dialog;
    private SharedPreferences pref;
    private String userName, passWord, token;
    private ApiService apiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = ApiClient.getAPIService();

        et_user = (EditText) findViewById(R.id.et_user);
        et_pass = (EditText) findViewById(R.id.et_pass);

        bt_login = (Button) findViewById(R.id.bt_login);

        pref = getSharedPreferences(Config.SHARED_PREF, MODE_PRIVATE);

        final boolean login = pref.getBoolean("login", false);
        final String role = pref.getString("role", "z");


        if (login && role.equals("A")) {
            Intent i = new Intent(LoginActivity.this, AdminMainActivity.class);
            startActivity(i);
            finish();
        } else if (login && role.equals("M")) {
            Intent i = new Intent(LoginActivity.this, MhsMainActivity.class);
            startActivity(i);
            finish();
        }

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token = pref.getString("regId", "tidak ada");
                String key = "ho7hhio98h";
                userName = et_user.getText().toString().trim();
                passWord = et_pass.getText().toString().trim();

                if (!userName.isEmpty() && !passWord.isEmpty()) {
                    checkLogin(key, userName, passWord, token);
                } else {
                    Toast.makeText(getApplicationContext(), "Lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkLogin(final String key, final String userName, final String passWord, final String token) {
        String tag_req = "req_login";

        Log.e(TAG, "Cek");
        apiService.loginRequest(key, userName, passWord, token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String role = jsonObject.getString("role");

                        SharedPreferences.Editor editor = pref.edit();
                        if (role.equals("A")) {
                            editor.putString("role", role);
                            editor.putBoolean("login", true);
                            editor.putString("username", userName);
                            editor.apply();
                            Intent i = new Intent(LoginActivity.this, AdminMainActivity.class);
                            startActivity(i);
                            finish();
                        } else if (role.equals("M")) {
                            editor.putString("role", role);
                            editor.putBoolean("login", true);
                            editor.putString("username", userName);
                            editor.apply();
                            Intent i = new Intent(LoginActivity.this, MhsMainActivity.class);
                            startActivity(i);
                            finish();
                        }

                        String token = pref.getString("regId", "tidak ada");
                        if (token.equals("tidak ada")) {
                            token = FirebaseInstanceId.getInstance().getToken();
                            editor.putString("regId", token);
                            editor.apply();
                        }
                        //Boolean login = pref.getBoolean()

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"ada kesalahan di server", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Periksa kembali inputan anda", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Gagal : " + t.toString());
                Toast.makeText(getApplicationContext(), "Periksa kembali sambungan internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
