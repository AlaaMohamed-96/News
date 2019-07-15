package com.example.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.news.API.APIManager;
import com.example.news.API.Model.ArticlesItem;
import com.example.news.API.Model.NewsResponse;
import com.example.news.API.Model.SourcesItem;
import com.example.news.API.Model.SourcesResponse;
import com.example.news.Base.BaseActivity;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends BaseActivity {

    TabLayout tabLayout;
    RecyclerView recyclerView;
    List<ArticlesItem> articles;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.tablayout);
        recyclerView=findViewById(R.id.recycler_view);
        initRecyclerView();
        loadNewsSource();
    }

    public void initRecyclerView(){
        newsAdapter=new NewsAdapter(null);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }
    private void loadNewsSource() {
        showProgressBar(R.string.loading);
        APIManager
                .getApis()
                .getNewsSources(Constants.API_KEY,Constants.LANGUAGE)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call,
                                           Response<SourcesResponse> response) {
                        hideProgressDialog();
                        setTabLayoutWithNewsSources(response.body().getSources());
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable t) {
                        hideProgressDialog();
                        showMessage(getString(R.string.error),t.getLocalizedMessage(),
                                getString(R.string.ok));
                    }
                });
    }

    private void loadNewsBySourceId(String sourceId){
        showProgressBar(R.string.loading);
        APIManager.getApis().getNews(Constants.API_KEY,
                Constants.LANGUAGE,sourceId+"")
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call,
                                           Response<NewsResponse> response) {
                        hideProgressDialog();
                        articles=response.body().getArticles();
                        newsAdapter.changeData(articles);
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        hideProgressDialog();
                        showMessage(getString(R.string.error),t.getLocalizedMessage(),
                                getString(R.string.ok));

                    }
                });

    }

    private void setTabLayoutWithNewsSources(final List<SourcesItem> sources) {
        for (int i=0;i<sources.size();i++){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(sources.get(i).getName());
            tab.setTag(sources.get(i));
            tabLayout.addTab(tab);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               /* int tabPos=tab.getPosition();
                loadNewsBySourceId(sources.get(tabPos).getId());*/
                SourcesItem item=((SourcesItem) tab.getTag());
                loadNewsBySourceId(item.getId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
