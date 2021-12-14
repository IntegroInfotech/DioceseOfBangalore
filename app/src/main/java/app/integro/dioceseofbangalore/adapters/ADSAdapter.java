package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.WebActivity;
import app.integro.dioceseofbangalore.models.ADS;
public class ADSAdapter extends PagerAdapter {

    private ArrayList<ADS> adsArrayList;
    Context context;
    LayoutInflater inflater;

    public ADSAdapter(Context context, ArrayList<ADS> adsArrayList) {
        this.adsArrayList = adsArrayList;
        this.context = context;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return adsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView tvAds;
        ADS ads = adsArrayList.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.card_ads, container, false);
        tvAds = view.findViewById(R.id.tvAds);
        tvAds.setText(adsArrayList.get(position).getTitle());

        ((ViewPager) container).addView(view);
        tvAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("URL", "" + ads.getWeblink());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}

