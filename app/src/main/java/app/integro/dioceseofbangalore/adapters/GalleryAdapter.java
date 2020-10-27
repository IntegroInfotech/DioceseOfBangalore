package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.GalleryDetailActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Gallery;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Gallery> galleryArrayList;

    public GalleryAdapter(Context context, ArrayList<Gallery> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_gallery, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Gallery galleryItem = galleryArrayList.get(position);

        Picasso.with(context)
                .load(galleryItem.getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(holder.ivGallery);

        holder.ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GalleryDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGallery;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivGallery = itemView.findViewById(R.id.ivGallery);
        }
    }
}
