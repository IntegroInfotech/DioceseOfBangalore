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

import app.integro.dioceseofbangalore.PalanaBavanaActivity3;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.PalanaBavana2;

public class PalanaBavana2Adapater extends RecyclerView.Adapter<PalanaBavana2Adapater.MyViewHolder> {

    private Context context;
    private ArrayList<PalanaBavana2> palanaBavana2ArrayList;

    public PalanaBavana2Adapater(Context context, ArrayList<PalanaBavana2> palanaBavana2ArrayList) {
        this.context = context;
        this.palanaBavana2ArrayList = palanaBavana2ArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_institutions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PalanaBavana2 palanaBavana2 = palanaBavana2ArrayList.get(position);
        holder.tvTitle.setText(palanaBavana2.getName());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PalanaBavanaActivity3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("DATA", palanaBavana2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return palanaBavana2ArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
