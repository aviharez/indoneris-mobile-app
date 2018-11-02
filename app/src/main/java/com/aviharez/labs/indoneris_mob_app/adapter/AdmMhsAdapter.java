package com.aviharez.labs.indoneris_mob_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.entity.DataMhs;

import java.util.ArrayList;

public class AdmMhsAdapter extends RecyclerView.Adapter<AdmMhsAdapter.MhsViewHolder> {

    private ArrayList<DataMhs> mhsList;
    private Context context;

    public AdmMhsAdapter(ArrayList<DataMhs> arrayList, Context context) {
        this.mhsList = arrayList;
        this.context = context;
    }

    @Override
    public AdmMhsAdapter.MhsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adm_mhs_item, viewGroup, false);
        return new MhsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mhsList.size();
    }

    @Override
    public void onBindViewHolder(MhsViewHolder holder, int i) {
        holder.tv_nama.setText(mhsList.get(i).getNama());
        holder.tv_nim.setText(mhsList.get(i).getNim());
        holder.tv_skkm.setText(String.valueOf(mhsList.get(i).getSkkm()));
    }

    public class MhsViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nim, tv_nama, tv_skkm;
        public MhsViewHolder(View itemView) {
            super(itemView);
            tv_nim = (TextView) itemView.findViewById(R.id.tv_nim);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_skkm = (TextView) itemView.findViewById(R.id.tv_skkm);
        }

    }

}
