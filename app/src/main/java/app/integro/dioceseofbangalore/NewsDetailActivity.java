package app.integro.dioceseofbangalore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.demono.AutoScrollViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.NewsImagesAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.News;
import app.integro.dioceseofbangalore.models.NewsImages;
import app.integro.dioceseofbangalore.models.NewsImagesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private AutoScrollViewPager newsImages;
    private ImageView ivImages;
    private News newsItem;
    private ArrayList<NewsImages> newsImagesArrayList;
    private NewsImagesAdapter adapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsImagesArrayList = new ArrayList<>();
        newsImages = findViewById(R.id.newsImages);

        TextView tvTitle = findViewById(R.id.tvTitle);
        ivImages = findViewById(R.id.ivImages);
        TextView tvDescription = findViewById(R.id.tvDescription);
        TextView tvDate = findViewById(R.id.tvDate);
        Button btnShare = findViewById(R.id.btnShare);

        newsItem = (News) getIntent().getSerializableExtra("data");

        tvTitle.setText(newsItem.getTitle());
        tvDescription.setText(newsItem.getDescription());
        tvDate.setText(newsItem.getDate());
        id = newsItem.getId();

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "http://bangalorearchdiocesenews.org/newshare.php?id=" + newsItem.getId());
                startActivity(intent);
            }
        });

        getNewsImages();
    }

    public void getNewsImages() {
        Call<NewsImagesList> newsImagesListCall = ApiClients.getClient().create(ApiServices.class).getNewsImagesList(id);
        newsImagesListCall.enqueue(new Callback<NewsImagesList>() {
            @Override
            public void onResponse(retrofit2.Call<NewsImagesList> call, Response<NewsImagesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsImagesArrayList() != null) {
                        int size = response.body().getNewsImagesArrayList().size();
                        if (size != 0) {
                            for (int i = 0; i < size; i++) {
                                newsImagesArrayList.add(response.body().getNewsImagesArrayList().get(i));
                                adapter = new NewsImagesAdapter(getApplicationContext(), newsImagesArrayList);
                                newsImages.setAdapter(adapter);
                                newsImages.startAutoScroll(3000);
                                newsImages.setCycle(true);
                            }
                        } else {
                            newsImages.setVisibility(View.GONE);
                            ivImages.setVisibility(View.VISIBLE);
                            Picasso.with(getApplicationContext())
                                    .load(newsItem.getL_img())
                                    .placeholder(R.drawable.bg_placeholder)
                                    .into(ivImages);
                        }
                    }
                } else {
                    Toast.makeText(NewsDetailActivity.this, "server response fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<NewsImagesList> call, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
