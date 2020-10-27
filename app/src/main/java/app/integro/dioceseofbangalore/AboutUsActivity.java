package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.AboutUs;
import app.integro.dioceseofbangalore.models.AboutUsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {
    private ArrayList<AboutUs> aboutUsArrayList;

    private TextView tvTitle;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        aboutUsArrayList = new ArrayList<>();
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        getAboutUs();
    }

    public void getAboutUs() {
        String date = "2017-11-01 23:26:29";
        Call<AboutUsList> aboutUsListCall = ApiClients.getClient().create(ApiServices.class).getAboutUsList(date);
        aboutUsListCall.enqueue(new Callback<AboutUsList>() {
            @Override
            public void onResponse(Call<AboutUsList> call, Response<AboutUsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAboutUsList() != null) {
                        aboutUsArrayList.add(response.body().getAboutUsList().get(0));
                        tvTitle.setText(aboutUsArrayList.get(0).getTitle());
                        tvDescription.setText(aboutUsArrayList.get(0).getContent());
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<AboutUsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
