package app.integro.dioceseofbangalore.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Gallery;


public class GalleryFullImageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Gallery> galleryArrayList;

    public GalleryFullImageAdapter(Context context, ArrayList<Gallery> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }

    @Override
    public int getCount() {
        return galleryArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        ImageView ivFullImage;
        ImageView ivShare;
        TextView tvCount;

        View view = LayoutInflater.from(context).inflate(R.layout.card_fullimage, container, false);

        ivFullImage =  view.findViewById(R.id.ivFullImage);
        tvCount =  view.findViewById(R.id.tvCount);
        ivShare = view.findViewById(R.id.ivShare);

        int count = position + 1;
        tvCount.setText("" + count + " / " + "" + galleryArrayList.size());

        Picasso.with(context)
                .load(galleryArrayList.get(position).getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivFullImage);
        ((ViewPager) container).addView(view);

        ivShare.setOnClickListener(new View.OnClickListener() {
            String itemId = galleryArrayList.get(position).getId();

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, ""+itemId );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
