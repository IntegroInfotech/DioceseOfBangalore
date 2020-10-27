package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.integro.dioceseofbangalore.ABEInstitutionDetailsActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Institutions;

public class ABEInstitutionAdapter extends RecyclerView.Adapter<ABEInstitutionAdapter.MyViewHolder> {

    private Context context;
    private List<Institutions> institutions;

    public  ABEInstitutionAdapter(Context context, List<Institutions> institutions) {
        this.context = context;
        this.institutions = institutions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Institutions abeInstitution = institutions.get(position);
        holder.tvTitle.setText(abeInstitution.getName());

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ABEInstitutionDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("DATA", abeInstitution.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return institutions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle =  itemView.findViewById(R.id.tvTitle);
        }
    }
}
