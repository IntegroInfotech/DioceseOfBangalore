package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.NotificationDetailsActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Notification> notificationArrayList;
    private static final String TAG = "NotificationAdapter";
    public NotificationAdapter(Context context, ArrayList<Notification> notificationsArrayList) {
        this.context = context;
        this.notificationArrayList = notificationsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_notification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Notification notificationItem = notificationArrayList.get(position);
        String date = notificationItem.getDate();
        SpannableString ss1 = new SpannableString(date);

        if (notificationItem.getTopicname().contentEquals("archdiocese-vip")) {
            holder.notificationCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            holder.llNotification.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            holder.notificationCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NotificationDetailsActivity.class);
                    intent.putExtra("data", notificationItem);
                    intent.putExtra("TYPE", "CLERGY");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        } else {
            holder.notificationCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NotificationDetailsActivity.class);
                    intent.putExtra("data", notificationItem);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("TYPE", "NORMAl");
                    context.startActivity(intent);
                }
            });
        }

        holder.tvN_Title.setText(notificationItem.getTitle());
        holder.tvN_date.setText(notificationItem.getDate());
        holder.tvN_Description.setText(notificationItem.getDescription());
        holder.tvN_date.setText(ss1);

        Log.i(TAG, "onBindViewHolder: "+notificationItem.getLink());
        if (notificationItem.getLink().equals("")) {
            holder.tvLink.setVisibility(View.GONE);
        } else {
            holder.tvLink.setText(notificationItem.getLink());
            holder.tvLink.setVisibility(View.VISIBLE);
            holder.tvLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(notificationItem.getLink()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvN_Title;
        private TextView tvN_date;
        private TextView tvN_Description;
        private TextView tvLink;
        private CardView notificationCard;
        private LinearLayout llNotification;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvN_date = itemView.findViewById(R.id.tvDate);
            tvN_Title = itemView.findViewById(R.id.tvTitle);
            tvN_Description = itemView.findViewById(R.id.tvDescription);
            tvLink = itemView.findViewById(R.id.tvLink);
            //Color Changing
            notificationCard = itemView.findViewById(R.id.notificationCard);
            llNotification = itemView.findViewById(R.id.llNotification);
            View horizontalLine = itemView.findViewById(R.id.horizontalLine);
        }
    }
}
