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

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.OrganisationData;

public class OrganisationDataAdapter extends RecyclerView.Adapter<OrganisationDataAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<OrganisationData> organisationDataArrayList;

    public OrganisationDataAdapter(Context context, ArrayList<OrganisationData> organisationDataArrayList) {
        this.context = context;
        this.organisationDataArrayList = organisationDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions2, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvTitle.setText(organisationDataArrayList.get(position).getName());
        holder.tvDescription.setText(organisationDataArrayList.get(position).getAddress() + "\n" + organisationDataArrayList.get(position).getDescription());

        holder.ivInstitutionMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.co.in/maps?q=" + organisationDataArrayList.get(position).getName() + "" + organisationDataArrayList.get(position).getAddress();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return organisationDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView ivInstitutionMap;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription2);
            ivInstitutionMap = itemView.findViewById(R.id.ivInstitutionMap);
        }
    }
}
