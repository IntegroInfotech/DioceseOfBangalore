package app.integro.dioceseofbangalore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;

import app.integro.dioceseofbangalore.models.Parishes;

public class ParishesDetailActivity extends AppCompatActivity {

    private static final String TAG = "ParishesDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parishes_detail);

        final Parishes parishesItem = (Parishes) getIntent().getSerializableExtra("data");

        ImageView ivImage = findViewById(R.id.ivImage);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDailyMassTimings = findViewById(R.id.tvDailyMassTimings);
        TextView tvSundayMassTimings = findViewById(R.id.tvSundayMassTimings);
        TextView tvAdoration = findViewById(R.id.tvAdoration);
        TextView tvContact = findViewById(R.id.tvContact);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvParishPriest = findViewById(R.id.tvParishPriest);
        TextView tvAstParishPriest = findViewById(R.id.tvAstParishPriest);
        TextView tvWebsite = findViewById(R.id.tvWebsite);
        TextView tvEmail = findViewById(R.id.tvEmail);
        LinearLayout btnGPS = findViewById(R.id.btnGPS);
        LinearLayout btnShare = findViewById(R.id.btnShare);

        Glide.with(this)
                .load(parishesItem.getImage())
                .into(ivImage);

        tvName.setText(parishesItem.getName());
        tvAddress.setText(parishesItem.getAddress());
        tvParishPriest.setText(parishesItem.getPriest());
        tvAstParishPriest.setText(parishesItem.getApriest());
        tvDailyMassTimings.setText(parishesItem.getTiming());
        tvSundayMassTimings.setText(parishesItem.getSuntiming());
        tvAdoration.setText(parishesItem.getAdoration());
        tvContact.setText(parishesItem.getContact());
        tvEmail.setText(parishesItem.getEmail());
        tvWebsite.setText(parishesItem.getWebsite());

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = parishesItem.getContact();
                Log.i(TAG, "onClick: " + number);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(ParishesDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{parishesItem.getEmail()});
                email.putExtra(Intent.EXTRA_SUBJECT, "");
                email.putExtra(Intent.EXTRA_TEXT, "");
                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://maps.google.co.in/maps?q=" + parishesItem.getName() + " " + parishesItem.getAddress();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "http://bangalorearchdiocesenews.org/masshare.php?id=" + parishesItem.getId());
                startActivity(intent);
            }
        });
    }
}
