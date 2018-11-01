package com.aviharez.labs.indoneris_mob_app.feature.mhs.event;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.feature.mhs.MhsMainActivity;
import com.aviharez.labs.indoneris_mob_app.util.Config;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import okhttp3.*;

import java.io.IOException;

public class DetailEventActivity extends AppCompatActivity {

    private TextView tv_judul_atas, tv_judul, tv_desc, tv_tanggal, tv_biaya, tv_penyelenggara, tv_skkm, tv_kuota, tv_kuota_terisi;
    private ImageView iv_poster;
    private Button bt_daftar;
    private SharedPreferences pref;
    private int id_acara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        tv_judul_atas = (TextView) findViewById(R.id.tv_judul_atas);
        tv_judul = (TextView) findViewById(R.id.tv_judul);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_tanggal = (TextView) findViewById(R.id.tv_tanggal);
        tv_biaya = (TextView) findViewById(R.id.tv_biaya);
        tv_penyelenggara = (TextView) findViewById(R.id.tv_penyelenggara);
        tv_skkm = (TextView) findViewById(R.id.tv_skkm);
        tv_kuota = (TextView) findViewById(R.id.tv_kuota);
        tv_kuota_terisi = (TextView) findViewById(R.id.tv_kuota_terisi);

        iv_poster = (ImageView) findViewById(R.id.iv_poster);

        bt_daftar = (Button) findViewById(R.id.bt_daftar);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        if (b != null) {
            tv_judul_atas.setText(b.getString("judul"));
            tv_judul.setText(b.getString("judul"));
            tv_desc.setText(b.getString("deskripsi"));
            tv_tanggal.setText(b.getString("tanggal"));
            tv_biaya.setText(b.getString("biaya"));
            tv_penyelenggara.setText(b.getString("penyelenggara"));
            tv_skkm.setText(String.valueOf(b.getInt("skkm")));
            tv_kuota.setText(String.valueOf(b.getInt("kuota")));
            tv_kuota_terisi.setText(String.valueOf(b.getInt("kuota_terisi")));

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background);
            Glide.with(getApplicationContext()).load(b.getString("gambar")).apply(options).into(iv_poster);

        }

        id_acara = b.getInt("id_penyelenggara");

        pref = getSharedPreferences(Config.SHARED_PREF, MODE_PRIVATE);
        final String token = pref.getString("regId", "tidak ada");

        bt_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String key = "qd7uij297";
               String action = "followEvent";
               final OkHttpClient client = new OkHttpClient();
                RequestBody body = null;
                body = new FormBody.Builder()
                        .add("token", token)
                        .add("key", key)
                        .add("action", action)
                        .add("id_acara", String.valueOf(id_acara))
                        .build();
                final Request request = new Request.Builder().url(Config.mainUrl + "mhs/event/manip.php").post(body).build();
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Response responDaftar = client.newCall(request).execute();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Selamat, anda mendaftar pada kegiatan tersebut", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(DetailEventActivity.this, MhsMainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
}
