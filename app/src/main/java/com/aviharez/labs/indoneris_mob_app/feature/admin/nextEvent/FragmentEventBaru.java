package com.aviharez.labs.indoneris_mob_app.feature.admin.nextEvent;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.util.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEventBaru extends Fragment {
    private Button btnPostImg,btnSubmit;
    private String token;
    private SharedPreferences pref;
    private EditText Edtitle, Eddesc, Edkuota, EdSkkm, Eddate, Edbiaya;
    private Spinner spPenyelenggara;
    Calendar calendar = Calendar.getInstance();
    public FragmentEventBaru() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v =  inflater.inflate(R.layout.fragment_fragment_event_baru, container, false);
      pref = getActivity().getSharedPreferences(Config.SHARED_PREF, Context.MODE_PRIVATE);

      btnPostImg = (Button) v.findViewById(R.id.BtnUpImgEvent);
      btnSubmit = (Button) v.findViewById(R.id.BtnSubmit);
      spPenyelenggara = (Spinner) v.findViewById(R.id.SpPenyelenggara);
      Eddate = (EditText) v.findViewById(R.id.EdDate);

    /*  Eddate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              new DatePickerDialog(getActivity(), dateEvent, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
          }
      }); */
      return v;
    }

  /*  DatePickerDialog.OnDateSetListener dateEvent = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR);
                    calendar.set(Calendar.MONTH);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTanggal();
        }
    };

    private void updateTanggal() {
        String formatTanggal = "yyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal, Locale.US);
        Eddate.setText(sdf.format(calendar.getTime()));
    } */
}
