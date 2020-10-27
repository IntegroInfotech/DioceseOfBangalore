package app.integro.dioceseofbangalore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.adapters.NewsAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.News;
import app.integro.dioceseofbangalore.models.NewsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    private ArrayList<News> newsArrayList;
    private RecyclerView rvNews;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        rvNews = view.findViewById(R.id.rvNews);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rvNews.setLayoutManager(manager);
        newsArrayList = new ArrayList<>();
        getNews();
        return view;
    }

    private void getNews() {
        String date = "2017-11-01 23:26:29";
        Call<NewsList> newsListCall = ApiClients.getClient().create(ApiServices.class).getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                    return;
                }
                if (response.body().getNewsList() == null) {
                    Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                }
                int size = response.body().getNewsList().size();
                Log.d("RESPONSE", "NewsList " + size);
                for (int i = 0; i < size; i++) {
                    newsArrayList.add(response.body().getNewsList().get(i));
                }
                newsAdapter = new NewsAdapter(getContext(), newsArrayList);
                rvNews.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

}
