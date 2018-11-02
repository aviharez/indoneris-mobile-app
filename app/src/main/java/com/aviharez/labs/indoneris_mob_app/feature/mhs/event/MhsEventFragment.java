package com.aviharez.labs.indoneris_mob_app.feature.mhs.event;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.adapter.MhsEventAdapter;
import com.aviharez.labs.indoneris_mob_app.entity.NextEvent;
import com.aviharez.labs.indoneris_mob_app.rest.ApiClient;
import com.aviharez.labs.indoneris_mob_app.rest.ApiService;
import com.aviharez.labs.indoneris_mob_app.util.Config;
import com.aviharez.labs.indoneris_mob_app.util.RecyclerTouchListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MhsEventFragment extends Fragment {

    private static final String TAG = MhsEventFragment.class.getSimpleName();

    private RecyclerView rv_event_mhs;
    private EditText et_search;
    private Spinner sp_penyelenggara;
    private SharedPreferences pref;

    private ArrayList<NextEvent> arrayEvent;
    private MhsEventAdapter adapter;


    public MhsEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_mhs_event, container, false);

        et_search = (EditText) v.findViewById(R.id.et_search);
        sp_penyelenggara = (Spinner) v.findViewById(R.id.sp_penyelenggara);
        rv_event_mhs = (RecyclerView) v.findViewById(R.id.rv_event);

        rv_event_mhs.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rv_event_mhs.setLayoutManager(layoutManager);

        rv_event_mhs.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rv_event_mhs, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NextEvent nextEvent = arrayEvent.get(position);
                Bundle b = new Bundle();
                b.putInt("id", nextEvent.getId());
                b.putString("judul", nextEvent.getJudul());
                b.putString("deskripsi", nextEvent.getDeskripsi());
                b.putString("tanggal", nextEvent.getTanggal());
                b.putString("biaya", nextEvent.getBiaya());
                b.putInt("id_penyelenggara", nextEvent.getId_penyelenggara_acara());
                b.putString("penyelenggara", nextEvent.getPenyelenggara());
                b.putInt("skkm", nextEvent.getSkkm());
                b.putInt("kuota", nextEvent.getKuota());
                b.putInt("kuota_terisi", nextEvent.getKuota_terisi());
                b.putString("gambar", nextEvent.getGambar());
                b.putBoolean("diikuti", nextEvent.getDiikuti());
                Intent i = new Intent(getActivity(), DetailEventActivity.class);
                i.putExtras(b);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        pref = getActivity().getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);
        String token = pref.getString("regId", "tidak ada");
        //Toast.makeText(getContext(), token, Toast.LENGTH_SHORT).show();
        String action = "showNext";
        String key = "adh821eaa";
        loadEvent(token, action, key);

        return v;
    }

    private void loadEvent(final String token, final String action, final String key) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<NextEvent>> call = apiService.listNextEventMhs(token, action, key);
        call.enqueue(new Callback<List<NextEvent>>() {
            @Override
            public void onResponse(Call<List<NextEvent>> call, Response<List<NextEvent>> response) {
                try {
                    Log.e(TAG, "Respon server: " + response.body());
                    arrayEvent = new ArrayList<>(response.body());
                    adapter = new MhsEventAdapter(arrayEvent, getContext());
                    rv_event_mhs.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error try catch : " + e.getMessage());
                    Toast.makeText(getContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NextEvent>> call, Throwable t) {
                Log.e(TAG, "Error failure: " + t.getMessage());
                Toast.makeText(getContext(), "Terdapat kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
