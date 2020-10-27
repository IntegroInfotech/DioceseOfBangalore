package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.PublicationsAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Publications;
import app.integro.dioceseofbangalore.models.PublicationList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicationsActivity extends AppCompatActivity {

    private static final String TAG = "PublicationsActivity";
    private ArrayList<Publications> publicationsArrayList;
    private RecyclerView rvPublications;
    private PublicationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        publicationsArrayList = new ArrayList<>();
        rvPublications = findViewById(R.id.rvPublications);
        rvPublications.setLayoutManager(new LinearLayoutManager(this));

        getPublicationsList();
    }

    public void getPublicationsList() {
        String date="2018-02-23 03:24:18";
        Call<PublicationList> publicationsListCall =
                ApiClients.getClient().create(ApiServices.class).getPublicationList(date);
        publicationsListCall.enqueue(new Callback<PublicationList>() {
            @Override
            public void onResponse(Call<PublicationList> call, Response<PublicationList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: fail");
                    return;
                }
                if (response.body().getPublicationsArrayList() == null) {
                    Log.i(TAG, "onResponse: null");
                    return;
                }
                int size = response.body().getPublicationsArrayList().size();
                Log.i(TAG, "onResponse: size " + size);
                for (int i = 0; i < size; i++) {
                    publicationsArrayList.add(response.body().getPublicationsArrayList().get(i));
                }
                adapter = new PublicationsAdapter(getApplicationContext(), publicationsArrayList);
                rvPublications.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PublicationList> call, Throwable t) {
                Log.i(TAG, "onFailure: getMessage " + t.getMessage());
            }
        });
    }
}