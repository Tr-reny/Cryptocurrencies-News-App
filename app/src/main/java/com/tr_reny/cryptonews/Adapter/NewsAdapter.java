package com.tr_reny.cryptonews.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tr_reny.cryptonews.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {








    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,pubDate,guid;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newsTitleTextView);
            guid = itemView.findViewById(R.id.newsDetailTextView);

        }
    }
}
