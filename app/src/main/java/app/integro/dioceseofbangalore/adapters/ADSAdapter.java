package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

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
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}

/*
public class ADSAdapter extends RecyclerView.Adapter<ADSAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ADS> adsArrayList;

    public ADSAdapter(Context context, ArrayList<ADS> adsArrayList) {
        this.context = context;
        this.adsArrayList = adsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_ads,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ADS ads=adsArrayList.get(position);
        holder.tvAds.setText(ads.getTitle());

        holder.tvAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebActivity.class);
                intent.putExtra("URL",""+ads.getWeblink());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAds;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAds = itemView.findViewById(R.id.tvAds);
        }
    }
*/

