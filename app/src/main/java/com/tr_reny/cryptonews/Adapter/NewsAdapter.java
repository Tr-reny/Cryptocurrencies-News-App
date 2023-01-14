package com.tr_reny.cryptonews.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The NewsAdapter class is an adapter class for displaying news data in a RecyclerView.
 * It binds data from a JSON file to the view holder and displays it on the RecyclerView.
 * The class uses Glide library to display the images, and converts timestamp from JSON to human-readable format.
 * The class also has loading footer feature which allows for pagination.
 */

public class NewsAdapter extends
        RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private Context mContext;
    private ArrayList<Data> dataList;
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

//Convert timestamp from JSON to human-readable format
        long timeInMilliseconds = dataList.get(position).getPublishedOn() * 1000L;
        CharSequence timestamp = DateUtils.getRelativeTimeSpanString(timeInMilliseconds, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timestamp);


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == dataList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /**
     * Method to add a loading footer to the RecyclerView
     */
    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Data());
    }

    /**
     * Method to remove the loading footer from the RecyclerView
     */

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

    /**
     * Inner class ViewHolder to hold the views of the RecyclerView
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, body, timestamp;
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
