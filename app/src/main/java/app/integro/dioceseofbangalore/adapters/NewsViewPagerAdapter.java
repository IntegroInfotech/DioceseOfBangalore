package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.NewsDetailActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.News;

public class NewsViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<News> newsArrayList;

    public NewsViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView ivNews;
        TextView tvNews;
        RelativeLayout rlNews;

        View itemView = LayoutInflater.from(context).inflate(R.layout.card_newsviewpager, container, false);

        ivNews = itemView.findViewById(R.id.ivNews);
        rlNews = itemView.findViewById(R.id.rlNews);
        tvNews = itemView.findViewById(R.id.tvTitle);

        News newsItem = newsArrayList.get(position);
        tvNews.setText(newsArrayList.get(position).getTitle());

        Picasso.with(context)
                .load(newsArrayList.get(position).getL_img())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivNews);
        rlNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("data", newsItem);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
