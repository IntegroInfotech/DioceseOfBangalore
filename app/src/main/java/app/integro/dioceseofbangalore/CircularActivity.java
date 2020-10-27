package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.CircularAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Circular;
import app.integro.dioceseofbangalore.models.CircularList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CircularActivity extends AppCompatActivity {

    private RecyclerView rvCircular;
    private ArrayList<Circular> circularArrayList;
    private CircularAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

        circularArrayList=new ArrayList<>();
        rvCircular= findViewById(R.id.rvCircular);
        rvCircular.setLayoutManager(new LinearLayoutManager(this));
        getCircularList();
    }

    public void getCircularList() {
        String date = "2017-11-02 23:55:42";
        Call<CircularList> circularListCall = ApiClients.getClient().create(ApiServices.class).getCircularList(date);
        circularListCall.enqueue(new Callback<CircularList>() {
            @Override
            public void onResponse(Call<CircularList> call, Response<CircularList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCircularList() != null) {
                        int size = response.body().getCircularList().size();
                        Log.d("RESPONSE", "CircularList " + size);
                        for (int i = 0; i < size; i++) {
                            circularArrayList.add(response.body().getCircularList().get(i));
                            adapter=new CircularAdapter(getApplicationContext(),circularArrayList);
                            rvCircular.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<CircularList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
