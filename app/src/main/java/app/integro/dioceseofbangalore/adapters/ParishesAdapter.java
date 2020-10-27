package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.ParishesDetailActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Parishes;

public class ParishesAdapter extends RecyclerView.Adapter<ParishesAdapter.MyViewHolder> {
    Context context;
    private ArrayList<Parishes> parishesArrayList;

    public ParishesAdapter(Context context, ArrayList<Parishes> parishesArrayList) {
        this.context = context;
        this.parishesArrayList = parishesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_churches, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Parishes parishesItem = parishesArrayList.get(position);
        holder.tvTitle.setText(parishesItem.getName());
        holder.tvAddress.setText(parishesItem.getAddress());

        holder.llMass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ParishesDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("data", parishesItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parishesArrayList.size();
    }

    public void setFilter(ArrayList<Parishes> arrayList) {
        parishesArrayList = new ArrayList<>();
        parishesArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAddress;
        private LinearLayout llMass;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            llMass = itemView.findViewById(R.id.llMass);
        }
    }
}
