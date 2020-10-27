package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.OrgnisationDataActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Organisation;

public class OrganisationAdapter extends RecyclerView.Adapter<OrganisationAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Organisation> organisationArrayList;

    public OrganisationAdapter(Context context, ArrayList<Organisation> organisationArrayList) {
        this.context = context;
        this.organisationArrayList = organisationArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Organisation organisationItem = organisationArrayList.get(position);
        holder.tvTitle.setText(organisationItem.getName());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrgnisationDataActivity.class);
                intent.putExtra("data", organisationArrayList.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return organisationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
