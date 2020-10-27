package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.integro.dioceseofbangalore.models.PrincipalMessage;

public class MessageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        PrincipalMessage message = (PrincipalMessage) getIntent().getSerializableExtra("data");

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDescription = findViewById(R.id.tvDescription);

        tvTitle.setText(message.getName());
        tvDescription.setText(message.getDescription());
    }
}
