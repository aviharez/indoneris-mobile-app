package com.aviharez.labs.indoneris_mob_app.feature.mhs.profil;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.adapter.MhsRiwayatAdapter;
import com.aviharez.labs.indoneris_mob_app.entity.ProfilMhs;
import com.aviharez.labs.indoneris_mob_app.entity.RiwayatPoin;
import com.aviharez.labs.indoneris_mob_app.rest.ApiClient;
import com.aviharez.labs.indoneris_mob_app.rest.ApiService;
import com.aviharez.labs.indoneris_mob_app.util.Config;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.api.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MhsProfilFragment extends Fragment {

    private RecyclerView rv_history;
    private ArrayList<RiwayatPoin> poinArrayList;
    private MhsRiwayatAdapter adapter;
    private TextView tv_nama_mhs, tv_tgl_lahir, tv_tempat_lahir, tv_jk, tv_skkm, tv_email;
    private ImageView iv_foto;
    private SharedPreferences pref;

    private static final String TAG = MhsProfilFragment.class.getSimpleName();

    public MhsProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mhs_profil, container, false);

        tv_nama_mhs = (TextView) v.findViewById(R.id.tv_nama_mhs);
        tv_tgl_lahir = (TextView) v.findViewById(R.id.tv_tgl_lahir);
        tv_tempat_lahir = (TextView) v.findViewById(R.id.tv_tempat_lahir);
        tv_jk = (TextView) v.findViewById(R.id.tv_jk);
        tv_skkm = (TextView) v.findViewById(R.id.tv_skkm);
        tv_email = (TextView) v.findViewById(R.id.tv_email);

        iv_foto = (ImageView) v.findViewById(R.id.iv_foto);

        rv_history = (RecyclerView) v.findViewById(R.id.rv_history_point);

        rv_history.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_history.setLayoutManager(layoutManager);
        rv_history.setItemAnimator(new DefaultItemAnimator());

        pref = getActivity().getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);

        String token = pref.getString("regId", "tidak ada");

        String keyHistory = "asf78knkjn";
        String history = "skkm";

        loadHistory(token, keyHistory, history);

        String detail = "profile";
        String keyProfile = "jfe98js07";

        loadProfile(token, detail, keyProfile);

        return v;
    }

    private void loadProfile(String token, String detail, String keyProfile) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ProfilMhs> call = apiService.profilMahasiswa(token, detail, keyProfile);
        call.enqueue(new Callback<ProfilMhs>() {
            @Override
            public void onResponse(Call<ProfilMhs> call, Response<ProfilMhs> response) {
                try {
                    Log.e(TAG, "Respon server : " + response.body());
                    tv_nama_mhs.setText(response.body().getNama());
                    tv_tempat_lahir.setText(response.body().getTempat_lahir());
                    tv_tgl_lahir.setText(response.body().getTgl_lahir());
                    tv_jk.setText(response.body().getJenis_kelamin());
                    tv_email.setText(response.body().getEmail());
                    tv_skkm.setText(String.valueOf(response.body().getSkkm()));
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background);
                    Glide.with(getContext()).load(response.body().getGambar()).apply(options).into(iv_foto);
                } catch (Exception e) {
                    Log.e(TAG, "Error catch : " + e.getMessage());
                    Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfilMhs> call, Throwable t) {
                Log.e(TAG, "Error failure : " + t.getMessage());
                Toast.makeText(getContext(), "Periksa kembali sambungan internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadHistory(String token, String keyHistory, String history) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<RiwayatPoin>> call = apiService.listRiwayat(token, keyHistory, history);
        call.enqueue(new Callback<List<RiwayatPoin>>() {
            @Override
            public void onResponse(Call<List<RiwayatPoin>> call, Response<List<RiwayatPoin>> response) {
                try {
                    Log.e(TAG, "Respon server : " + response.body());
                    poinArrayList = new ArrayList<>(response.body());
                    adapter = new MhsRiwayatAdapter(poinArrayList, getContext());
                    rv_history.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error catch : " + e.getMessage());
                    Toast.makeText(getContext(), "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatPoin>> call, Throwable t) {
                Log.e(TAG, "Error failure : " + t.getMessage());
                Toast.makeText(getContext(), "Periksa kembali koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
