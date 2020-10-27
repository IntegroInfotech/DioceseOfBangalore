package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Circular;

public class CircularAdapter extends RecyclerView.Adapter<CircularAdapter.MyViewHolder> {
    private static final String TAG = "CircularAdapter";
    private Context context;
    private ArrayList<Circular> circularArrayList;

    public CircularAdapter(Context context, ArrayList<Circular> circularArrayList) {
        this.context = context;
        this.circularArrayList = circularArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_circular, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Circular circularItems = circularArrayList.get(position);
        holder.tvTitle.setText(circularItems.getName());
        holder.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(circularItems.getLink()));
                Log.i(TAG, "onClick: " + circularItems.getLink());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return circularArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDownload;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle =  itemView.findViewById(R.id.tvTitle);
            tvDownload =  itemView.findViewById(R.id.tvDownload);
        }
    }
}
