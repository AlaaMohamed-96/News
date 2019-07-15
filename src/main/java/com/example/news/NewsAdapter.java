package com.example.news;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.API.Model.ArticlesItem;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    List<ArticlesItem> articlesItems;

    public NewsAdapter(List<ArticlesItem> articlesItems) {
        this.articlesItems = articlesItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_news_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        ArticlesItem item=articlesItems.get(pos);
        viewHolder.title.setText(item.getTitle());
        viewHolder.date.setText(item.getPublishedAt());
        //image ->> glide
        Glide.with(viewHolder.itemView)
                .load(item.getUrlToImage())
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if(articlesItems==null)return 0;
        return articlesItems.size();
    }

    public void changeData(List<ArticlesItem> items){
        articlesItems=items;
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            title=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image_view);
        }
    }
}
