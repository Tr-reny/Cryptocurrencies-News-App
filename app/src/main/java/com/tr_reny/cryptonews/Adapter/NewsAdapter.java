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

    private ArrayList<News> newsArrayList;
    private ArrayList<News> newsArrayListFull;

    public NewsAdapter(Context mContext, ArrayList<News> newsArrayList) {
        this.mContext = mContext;
        this.newsArrayListFull = newsArrayList;
        this.newsArrayList = new ArrayList<>(newsArrayListFull);
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
        holder.title.setText(newsArrayList.get(position).getTitle());
        holder.guid.setText(newsArrayList.get(position).getGuid());
        holder.pubDate.setText(newsArrayList.get(position).getPubDate());

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return titleFilter;
    }

    // Filter the Containers
    private final Filter titleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<News> filteredTitle = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredTitle.addAll(newsArrayListFull);
            }
            else {
                String filterPatten = constraint.toString().toLowerCase().trim();

                for (News news : newsArrayListFull){

                    if (news.getTitle().toLowerCase(Locale.ROOT).contains(filterPatten))
                        filteredTitle.add(news);

                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredTitle;
            results.count = filteredTitle.size();

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {


            newsArrayList.clear();
            newsArrayList.addAll((ArrayList)results.values);
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
