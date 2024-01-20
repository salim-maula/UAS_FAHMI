package com.example.uas_fahminuradi_20110610012;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.HolderItem> {
    List<ModelList> mListItem;
    Context context;

    public AdapterList(List<ModelList> mListItem, Context context, RequestQueue ignoredRequestQueue) {
        this.mListItem = mListItem;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rows, parent, false);
        return new HolderItem(view);
    }

    @Override
    public void onBindViewHolder(HolderItem holder, int position) {
        ModelList currentItem = mListItem.get(position);

        // Set data to views
        Glide.with(context)
                .load(currentItem.getImg())
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thubmail);
        Picasso.get().load(currentItem.getImg()).into(holder.thubmail);
        holder.tv_title.setText(currentItem.getTitle());
        holder.tv_keterangan.setText(currentItem.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ModelList> newData) {
        mListItem.clear();
        mListItem.addAll(newData);
        notifyDataSetChanged();
    }

    public static class HolderItem extends RecyclerView.ViewHolder {
        ImageView thubmail;
        TextView tv_title, tv_keterangan;

        public HolderItem(View v) {
            super(v);

            thubmail = v.findViewById(R.id.img_cover);
            tv_title = v.findViewById(R.id.tv_title);
            tv_keterangan = v.findViewById(R.id.tv_description);
        }
    }
}

