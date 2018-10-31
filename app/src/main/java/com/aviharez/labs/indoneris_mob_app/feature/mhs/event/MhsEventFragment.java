package com.aviharez.labs.indoneris_mob_app.feature.mhs.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aviharez.labs.indoneris_mob_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MhsEventFragment extends Fragment {


    public MhsEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mhs_event, container, false);
    }

}
