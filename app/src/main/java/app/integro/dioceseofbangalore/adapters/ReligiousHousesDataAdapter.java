package app.integro.dioceseofbangalore.adapters;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.ReligiousHousesData;

public class ReligiousHousesDataAdapter extends RecyclerView.Adapter<ReligiousHousesDataAdapter.MyViewHolder> {

    private Context context;
    private List<ReligiousHousesData> religiousHousesDataArrayList;

    public ReligiousHousesDataAdapter(Context context, List<ReligiousHousesData> religiousHousesDataArrayList) {
        this.context = context;
        this.religiousHousesDataArrayList = religiousHousesDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_institutions2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ReligiousHousesData housesData= religiousHousesDataArrayList.get(position);

        holder.tvTitle.setText(housesData.getName());
        holder.tvDescription.setText(housesData.getDescription());

        holder.ivInstitutionMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("DefaultLocale")
                String uri = "http://maps.google.com/maps?saddr=" + housesData.getName();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return religiousHousesDataArrayList.size();
    }

    public void setFilter(ArrayList<ReligiousHousesData> religiousHousesData) {
        religiousHousesDataArrayList = new ArrayList<>();
        religiousHousesDataArrayList.addAll(religiousHousesData);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        ImageView ivInstitutionMap;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription2);
            ivInstitutionMap = itemView.findViewById(R.id.ivInstitutionMap);
        }
    }
}
