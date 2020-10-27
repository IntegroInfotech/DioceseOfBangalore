package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.MessageDetailActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.PrincipalMessage;

public class PrincipalMessageAdapter extends RecyclerView.Adapter<PrincipalMessageAdapter.MyViewHolder> {

    private static final String TAG = "PrincipalMessageAdapter";
    private ArrayList<PrincipalMessage> messageArrayList;
    private Context context;

    public PrincipalMessageAdapter(Context context, ArrayList<PrincipalMessage> principalMessageArrayList) {
        this.context = context;
        this.messageArrayList = principalMessageArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_message, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PrincipalMessage message = messageArrayList.get(position);
        holder.tvTitle.setText(message.getName());
        holder.tvDescription.setText(message.getDescription());

        if (message.getImage() != null) {
            Log.i(TAG, "onBindViewHolder: " + message.getImage());
            Glide.with(context).load(message.getImage()).into(holder.ivImage);
        } else {
            holder.ivImage.setVisibility(View.GONE);
        }

        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageDetailActivity.class);
                intent.putExtra("data", message);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvReadMore;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvReadMore = itemView.findViewById(R.id.tvReadMore);
        }
    }
}
