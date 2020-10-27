package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.GalleryAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Gallery;
import app.integro.dioceseofbangalore.models.GalleryList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {

    private ArrayList<Gallery> galleryArrayList;
    private RecyclerView rvGallery;
    private GalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        galleryArrayList = new ArrayList<>();
        rvGallery = findViewById(R.id.rvGallery);
        rvGallery.setLayoutManager(new GridLayoutManager(this, 2));

        getGalleryList();
    }

    public void getGalleryList() {
        String date = "2017-11-02 23:55:42";
        Call<GalleryList> galleryListCall = ApiClients.getClient().create(ApiServices.class).getGalleryList(date);
        galleryListCall.enqueue(new Callback<GalleryList>() {
            @Override
            public void onResponse(Call<GalleryList> call, Response<GalleryList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getGalleryList() != null) {
                        int size = response.body().getGalleryList().size();
                        Log.d("RESPONSE", "getGalleryList " + size);
                        for (int i = 0; i < size; i++) {
                            galleryArrayList.add(response.body().getGalleryList().get(i));
                            adapter = new GalleryAdapter(getApplicationContext(), galleryArrayList);
                            rvGallery.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<GalleryList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

}
