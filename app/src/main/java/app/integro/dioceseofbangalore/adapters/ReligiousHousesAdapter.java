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

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.ReligiousHousesDetailsActivity;
import app.integro.dioceseofbangalore.models.ReligiousHouses;

public class ReligiousHousesAdapter extends RecyclerView.Adapter<ReligiousHousesAdapter.MyViewHolder> {

    private Context context;
    private List<ReligiousHouses> religiousHouses;

    public ReligiousHousesAdapter(Context context, List<ReligiousHouses> religiousHouses) {
        this.context = context;
        this.religiousHouses = religiousHouses;
    }

    @NonNull
    @Override
    public ReligiousHousesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReligiousHousesAdapter.MyViewHolder holder, int position) {
        ReligiousHouses houses = religiousHouses.get(position);

        holder.title.setText(houses.getName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReligiousHousesDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("DATA", houses.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return religiousHouses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
        }
    }
}
