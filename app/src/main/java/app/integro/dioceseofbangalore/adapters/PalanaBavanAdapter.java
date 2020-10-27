package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.PalanaBavanaActivity2;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.WebActivity;
import app.integro.dioceseofbangalore.models.PalanaBavan;

public class PalanaBavanAdapter extends RecyclerView.Adapter<PalanaBavanAdapter.MyViewHolder> {

    private static final String TAG = "PalanaBavanAdapter";
    private Context context;
    private ArrayList<PalanaBavan> palanaBavanArrayList;

    public PalanaBavanAdapter(Context context, ArrayList<PalanaBavan> palanaBavanArrayList) {
        this.context = context;
        this.palanaBavanArrayList = palanaBavanArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PalanaBavan palanaBavan = palanaBavanArrayList.get(position);
        holder.tvTitle.setText(palanaBavan.getName());

        Log.i(TAG, "onBindViewHolder:"+palanaBavanArrayList.get(position).getName());

        if (holder.tvTitle.getText().equals("Paalnaa Bhavana")) {
            holder.tvTitle.setText("PAALANAA BHAVANA");
        }
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + position);
                if (holder.tvTitle.getText().equals("PAALANAA BHAVANA")) {
                    String paalanaBavana = "http://paalanaabhavana.org/";
                    Intent dailySaintIntent = new Intent(context, WebActivity.class);
                    dailySaintIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    dailySaintIntent.putExtra("URL", paalanaBavana);
                    context.startActivity(dailySaintIntent);
                } else {
                    Intent intent = new Intent(context, PalanaBavanaActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("DATA", palanaBavan);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return palanaBavanArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
