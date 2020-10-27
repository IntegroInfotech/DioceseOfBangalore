package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.ReligiousInstitutionData;

public class ReligiousInstitutionDataAdapter extends RecyclerView.Adapter<ReligiousInstitutionDataAdapter.MyViewHolder> {
    private static final String TAG = "ReligiousInstitutionDat";
    private Context context;
    private List<ReligiousInstitutionData> dataListArrayList;

    public ReligiousInstitutionDataAdapter(Context context, List<ReligiousInstitutionData> religiousInstitutionDataArrayList) {
        this.context = context;
        this.dataListArrayList = religiousInstitutionDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReligiousInstitutionData institutionData = dataListArrayList.get(position);

        holder.tvTitle.setText(institutionData.getName());
        Log.i(TAG, "onBindViewHolder: " + institutionData.getName());

        holder.tvDescription.setText(institutionData.getAddress());
        holder.ivInstitutionMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.co.in/maps?q=" + institutionData.getName() + "" + institutionData.getAddress();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView ivInstitutionMap;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle =  itemView.findViewById(R.id.tvTitle);
            tvDescription =  itemView.findViewById(R.id.tvDescription2);
            ivInstitutionMap = itemView.findViewById(R.id.ivInstitutionMap);
        }


    }
}
