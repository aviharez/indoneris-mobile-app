package com.aviharez.labs.indoneris_mob_app.feature.admin.mhs;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import android.widget.*;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.adapter.AdmMhsAdapter;
import com.aviharez.labs.indoneris_mob_app.entity.DataMhs;
import com.aviharez.labs.indoneris_mob_app.entity.Kelas;
import com.aviharez.labs.indoneris_mob_app.entity.Prodi;
import com.aviharez.labs.indoneris_mob_app.rest.ApiClient;
import com.aviharez.labs.indoneris_mob_app.rest.ApiService;
import com.aviharez.labs.indoneris_mob_app.util.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdmMhsFragment extends Fragment {

    private Spinner sp_prodi, sp_kelas;
    private ProgressBar loading;
    private RecyclerView rv_mahasiswa;
    private AdmMhsAdapter mAdapter;
    private SharedPreferences pref;

    String prodiSelected, kelasSelected;

    List<Prodi> prodiItem;
    List<Kelas> kelasItem;

    private ArrayList<DataMhs> arrayMhs;

    public AdmMhsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_adm_mhs, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        sp_prodi = (Spinner) v.findViewById(R.id.sp_prodi);
        sp_kelas = (Spinner) v.findViewById(R.id.sp_kelas);

        loading = (ProgressBar) v.findViewById(R.id.loading);

        rv_mahasiswa = (RecyclerView) v.findViewById(R.id.rv_mahasiswa);

        rv_mahasiswa.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_mahasiswa.setLayoutManager(layoutManager);
        rv_mahasiswa.setItemAnimator(new DefaultItemAnimator());

        pref = getActivity().getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);

        final String token = pref.getString("regId", "tidak ada");
        String keyPro = "vabsdb234";
        String lists = "jurusan";
        loadProdi(token, keyPro, lists);

        sp_prodi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                prodiSelected = adapterView.getItemAtPosition(i).toString();
                String id_jurusan = String.valueOf(prodiItem.get(i).getId());
                String keyKelas = "vabsdb234";
                String listsKelas = "kelas";
                loadKelas(token, keyKelas, listsKelas, id_jurusan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_kelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kelasSelected = adapterView.getItemAtPosition(i).toString();
                String id_kelas = String.valueOf(kelasItem.get(i).getId());
                String keyList = "ash9812kjk";
                String show = "by_class";
                loadMahasiswa(keyList, show, token, id_kelas);
                loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    private void loadMahasiswa(String keyList, String show, String token, String id_kelas) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<DataMhs>> call = apiService.dataMahasiswa(keyList, show, token, id_kelas);
        call.enqueue(new Callback<List<DataMhs>>() {
            @Override
            public void onResponse(Call<List<DataMhs>> call, Response<List<DataMhs>> response) {
                try {
                    loading.setVisibility(View.INVISIBLE);
                    arrayMhs = new ArrayList<>(response.body());
                    mAdapter = new AdmMhsAdapter(arrayMhs, getContext());
                    rv_mahasiswa.setAdapter(mAdapter);
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            public void onFailure(Call<List<DataMhs>> call, Throwable t) {
                Toast.makeText(getContext(), "Periksa kembali sambungan internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadKelas(String token, String keyKelas, String listsKelas, String id_jurusan) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Kelas>> call = apiService.listKelas(token, keyKelas, listsKelas, id_jurusan);
        call.enqueue(new Callback<List<Kelas>>() {
            @Override
            public void onResponse(Call<List<Kelas>> call, Response<List<Kelas>> response) {
                try {
                    kelasItem = response.body();
                    List<String> listSpinner = new ArrayList<>();
                    for (int i = 0; i < kelasItem.size(); i++) {
                        listSpinner.add(kelasItem.get(i).getKelas());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    sp_kelas.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<Kelas>> call, Throwable t) {
                Toast.makeText(getContext(), "Periksa kembali sambungan internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProdi(String token, String keyPro, String lists) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Prodi>> call = apiService.listProdi(token, keyPro, lists);
        call.enqueue(new Callback<List<Prodi>>() {
            @Override
            public void onResponse(Call<List<Prodi>> call, Response<List<Prodi>> response) {
                try {
                    prodiItem = response.body();
                    List<String> listSpinner = new ArrayList<>();
                    for (int i = 0; i < prodiItem.size(); i++) {
                        listSpinner.add(prodiItem.get(i).getJurusan());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listSpinner);
                    sp_prodi.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<Prodi>> call, Throwable t) {
                Toast.makeText(getContext(), "Periksa kembali sambungan internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
