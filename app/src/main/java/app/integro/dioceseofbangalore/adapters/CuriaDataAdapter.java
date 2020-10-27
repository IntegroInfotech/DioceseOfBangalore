package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.CuriaData;

public class CuriaDataAdapter extends RecyclerView.Adapter<CuriaDataAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CuriaData> curiaDataArrayList;

    public CuriaDataAdapter(Context context, ArrayList<CuriaData> curiaDataArrayList) {
        this.context = context;
        this.curiaDataArrayList = curiaDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_churches,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CuriaData curiaData=curiaDataArrayList.get(position);
        holder.tvTitle.setText(curiaData.getTitle());
        holder.tvAddress.setText(curiaData.getDescription());
    }

    @Override
    public int getItemCount() {
        return curiaDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
