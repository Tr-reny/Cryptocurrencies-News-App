package com.tr_reny.cryptonews.Adapter;

import android.content.Context;
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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Data> dataList;

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


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, body;
        ImageView imageViewUrl;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newsTitleTextView);
            body= itemView.findViewById(R.id.newsDetailTextView);
            imageViewUrl = itemView.findViewById(R.id.newsThumbnailImageView);

        }
    }
}
