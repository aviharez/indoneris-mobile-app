package com.aviharez.labs.indoneris_mob_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.entity.RiwayatPoin;

import java.util.ArrayList;

public class MhsRiwayatAdapter extends RecyclerView.Adapter<MhsRiwayatAdapter.RiwayatViewHolder> {

    private ArrayList<RiwayatPoin> listPoin;
    private Context context;

    public MhsRiwayatAdapter(ArrayList<RiwayatPoin> listPoin, Context context) {
        this.context = context;
        this.listPoin = listPoin;
    }

    @Override
    public MhsRiwayatAdapter.RiwayatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mhs_history_item, viewGroup, false);
        return new RiwayatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RiwayatViewHolder holder, int i) {
        holder.tv_keterangan.setText(listPoin.get(i).getKeterangan());
        holder.tv_skkm.setText(String.valueOf(listPoin.get(i).getSkkm()));
        holder.tv_tanggal.setText(listPoin.get(i).getTanggal());
    }

    @Override
    public int getItemCount() {
        return listPoin.size();
    }

    class RiwayatViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_tanggal, tv_skkm, tv_keterangan;

        public RiwayatViewHolder(View itemView) {
            super(itemView);
            tv_keterangan = (TextView) itemView.findViewById(R.id.tv_keterangan);
            tv_skkm = (TextView) itemView.findViewById(R.id.tv_skkm);
            tv_tanggal = (TextView) itemView.findViewById(R.id.tv_tanggal);
        }

    }
}
