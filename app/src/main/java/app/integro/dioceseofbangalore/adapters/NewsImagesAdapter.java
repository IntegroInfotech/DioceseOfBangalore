package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.NewsImages;

public class NewsImagesAdapter extends PagerAdapter {
    private ArrayList<NewsImages> newsImagesArrayList;
    private Context context;

    public NewsImagesAdapter(Context context, ArrayList<NewsImages> newsImagesArrayList) {
        this.context = context;
        this.newsImagesArrayList = newsImagesArrayList;
    }

    @Override
    public int getCount() {
        return newsImagesArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivNews;

        View itemView = LayoutInflater.from(context).inflate(R.layout.card_cover_photos, container, false);
        ivNews = (ImageView) itemView.findViewById(R.id.ivNews);

        Glide.with(context)
                .load(newsImagesArrayList.get(position).getImg())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivNews);

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
