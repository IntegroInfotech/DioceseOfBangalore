package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Publications;

public class PublicationsAdapter extends RecyclerView.Adapter<PublicationsAdapter.MyViewHolder> {
    private ArrayList<Publications> publicationsArrayList;
    private Context context;

    public PublicationsAdapter(Context context, ArrayList<Publications> publicationsArrayList) {
        this.context = context;
        this.publicationsArrayList = publicationsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_publications, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Publications publicationsItems = publicationsArrayList.get(position);

        Glide.with(context)
                .load(publicationsItems.getImage())
                .into(holder.ivImage);

        holder.tvTitle.setText(publicationsItems.getName());
        holder.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(publicationsItems.getUrl_pdf()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publicationsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvDownload;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDownload = itemView.findViewById(R.id.tvDownload);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
