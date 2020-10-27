package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.GalleryFullImageAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Gallery;
import app.integro.dioceseofbangalore.models.GalleryList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryDetailActivity extends AppCompatActivity {

    int position;
    private ViewPager fullImageViewpager;
    private ArrayList<Gallery> galleryArrayList;
    private GalleryFullImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        position = (int) getIntent().getSerializableExtra("position");

        galleryArrayList = new ArrayList<>();
        fullImageViewpager = findViewById(R.id.fullImageViewpager);
        getGallery();

    }

    public void getGallery() {
        String date = "2017-11-02 23:55:42";
        Call<GalleryList> galleryListCall = ApiClients.getClient().create(ApiServices.class).getGalleryList(date);
        galleryListCall.enqueue(new Callback<GalleryList>() {
            @Override
            public void onResponse(retrofit2.Call<GalleryList> call, Response<GalleryList> response) {
                if (response.isSuccessful()) {
                    Log.d("RESPONSE", "Success");
                    if (response.body() != null) {
                        int size = response.body().getGalleryList().size();
                        Log.d("RESPONSE", "gallery Size " + size);
                        for (int i = 0; i < size; i++) {
                            galleryArrayList.add(response.body().getGalleryList().get(i));
                            adapter = new GalleryFullImageAdapter(getApplicationContext(), galleryArrayList);
                            fullImageViewpager.setAdapter(adapter);
                            fullImageViewpager.setCurrentItem(position);
                        }
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<GalleryList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
