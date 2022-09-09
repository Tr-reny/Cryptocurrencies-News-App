package com.tr_reny.cryptonews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tr_reny.cryptonews.Model.News;
import com.tr_reny.cryptonews.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<News> newsList;
    private List<News> newsListFull;

    public NewsAdapter(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsListFull = newsList;
        this.newsList = new ArrayList<>(newsListFull);
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
        holder.title.setText(newsList.get(position).getTitle());
        holder.guid.setText(newsList.get(position).getGuid());
        holder.pubDate.setText(newsList.get(position).getPubDate());

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public Filter getFilter() {
        return newsFilter;
    }

    private final Filter newsFilter =  new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<News> filteredNewsList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredNewsList.addAll(newsListFull);
            }else
            {
                String filterPatten = constraint.toString().toLowerCase(Locale.ROOT).trim();
                for (News news : newsListFull ){
                    if ( news.title.toLowerCase(Locale.ROOT).contains(filterPatten))
                        filteredNewsList.add(news);
                }
            }

       FilterResults results = new FilterResults();
            results.values = filteredNewsList;
            results.count = filteredNewsList.size();
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {


            newsList.clear();
            newsList.addAll((ArrayList)results.values);
            notifyDataSetChanged();


        }
    };





    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, pubDate, guid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newsTitleTextView);
            guid = itemView.findViewById(R.id.newsDetailTextView);
            pubDate = itemView.findViewById(R.id.tv_time_stamp);

        }
    }
}
