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

import app.integro.dioceseofbangalore.CuriaDetailActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Curia;

public class CuriaAdapter extends RecyclerView.Adapter<CuriaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Curia> curiaArrayList;

    public CuriaAdapter(Context context, ArrayList<Curia> curiaArrayList) {
        this.context = context;
        this.curiaArrayList = curiaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Curia curia = curiaArrayList.get(position);
        holder.tvTitle.setText(curia.getTitle());

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CuriaDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("DATA", curia);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return curiaArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
