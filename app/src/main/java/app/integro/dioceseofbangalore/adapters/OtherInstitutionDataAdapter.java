package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.OtherInstitutionData;

public class OtherInstitutionDataAdapter extends RecyclerView.Adapter<OtherInstitutionDataAdapter.MyViewHolder> {

    private Context context;
    private List<OtherInstitutionData> otherInstitutionDataArrayList;

    public OtherInstitutionDataAdapter(Context context, List<OtherInstitutionData> otherInstitutionDataArrayList) {
        this.context = context;
        this.otherInstitutionDataArrayList = otherInstitutionDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OtherInstitutionData otherInstitutionData= otherInstitutionDataArrayList.get(position);

        holder.tvTitle.setText(otherInstitutionData.getName());
        holder.tvDescription.setText(otherInstitutionData.getDescription());
    }

    @Override
    public int getItemCount() {
        return otherInstitutionDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription2);
            ImageView ivInstitutionMap = itemView.findViewById(R.id.ivInstitutionMap);
        }
    }
}
