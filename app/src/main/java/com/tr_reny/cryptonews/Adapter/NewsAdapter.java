package com.tr_reny.cryptonews.Adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tr_reny.cryptonews.Model.Data;
import com.tr_reny.cryptonews.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.github.curioustechizen.ago.RelativeTimeTextView;

public class NewsAdapter extends
        RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Data> dataList;

    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;


    public NewsAdapter(Context mContext, ArrayList<Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.layout_news, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(dataList.get(position).getTitle());
        holder.body.setText(dataList.get(position).getBody());


        //Glide Library to display the images

        Glide.with(mContext)
                .load(dataList.get(position).getImageurl())
                .into(holder.imageViewUrl);

        //time stamp
        long timeInMilliseconds = dataList.get(position).getPublishedOn() * 1000L;
        Date date = new Date(timeInMilliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String timestamp = sdf.format(date);
        holder.timestamp.setText(timestamp);


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 :dataList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return (position == dataList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Data());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = dataList.size() - 1;
        Data result = getItem(position);

        if (result != null) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(Data data) {
        dataList.add(data);
        notifyItemInserted(dataList.size() - 1);
    }

    public void addAll(List<Data> dataResults) {
        for (Data result : dataResults) {
            add(result);
        }
    }

    public Data getItem(int position) {
        return dataList.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, body , timestamp;
        ImageView imageViewUrl;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newsTitleTextView);
            body = itemView.findViewById(R.id.newsDetailTextView);
            imageViewUrl = itemView.findViewById(R.id.newsThumbnailImageView);
            timestamp = itemView.findViewById(R.id.tv_time_stamp);

        }
    }
}
