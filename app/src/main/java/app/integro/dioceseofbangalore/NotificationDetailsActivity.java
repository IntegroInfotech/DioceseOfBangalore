package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import app.integro.dioceseofbangalore.models.Notification;

public class NotificationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);

        Notification notification = (Notification) getIntent().getSerializableExtra("data");
        String notification_type = (String) getIntent().getStringExtra("TYPE");

        CardView notificationCard = findViewById(R.id.notificationCard);
        CardView notificationCard1 = findViewById(R.id.notificationCard1);

        TextView tvN_Title;
        TextView tvN_date;
        TextView tvN_Description;
        if (notification_type.contentEquals("CLERGY")) {
            notificationCard1.setVisibility(View.VISIBLE);
            notificationCard.setVisibility(View.GONE);
            tvN_date = findViewById(R.id.tvDate1);
            tvN_Title = findViewById(R.id.tvTitle1);
            tvN_Description = findViewById(R.id.tvDescription1);
            tvN_date.setText(notification.getDate());
            tvN_Title.setText(notification.getTitle());
            tvN_Description.setText(notification.getDescription());
        } else {
            notificationCard.setVisibility(View.VISIBLE);
            notificationCard1.setVisibility(View.GONE);
            tvN_date = findViewById(R.id.tvDate);
            tvN_Title = findViewById(R.id.tvTitle);
            tvN_Description = findViewById(R.id.tvDescription);
            tvN_date.setText(notification.getDate());
            tvN_Title.setText(notification.getTitle());
            tvN_Description.setText(notification.getDescription());
        }
    }
}
