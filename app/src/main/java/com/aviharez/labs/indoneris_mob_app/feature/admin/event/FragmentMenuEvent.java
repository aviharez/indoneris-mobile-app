package com.aviharez.labs.indoneris_mob_app.feature.admin.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aviharez.labs.indoneris_mob_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuEvent extends Fragment {
    RecyclerView recyclerView;

    public FragmentMenuEvent() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_menu_event, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rv_event);

        return v;
    }
}
