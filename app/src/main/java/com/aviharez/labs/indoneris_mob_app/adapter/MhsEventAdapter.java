package com.aviharez.labs.indoneris_mob_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.aviharez.labs.indoneris_mob_app.R;
import com.aviharez.labs.indoneris_mob_app.entity.NextEvent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MhsEventAdapter extends RecyclerView.Adapter<MhsEventAdapter.EventViewHolder> {

    private List<NextEvent> nextEventList;
    private Context context;

    public MhsEventAdapter(List<NextEvent> eventList, Context context) {
        super();
        this.nextEventList = eventList;
        this.context = context;
    }

    @Override
    public MhsEventAdapter.EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mhs_event_item, viewGroup, false);
        EventViewHolder eventViewHolder = new EventViewHolder(v, context);
        return eventViewHolder;
    }

    @Override
    public int getItemCount() {
        return nextEventList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        NextEvent item = nextEventList.get(position);
        holder.bind(item);
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView iv_poster;

        Context context;
        private NextEvent currentItem;

        public EventViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_poster = (ImageView) itemView.findViewById(R.id.iv_img_event);
        }

        void bind(NextEvent item) {
            tv_name.setText(item.getJudul());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background);
            Glide.with(context).load(item.getGambar()).apply(options).into(iv_poster);
        }

    }
}
