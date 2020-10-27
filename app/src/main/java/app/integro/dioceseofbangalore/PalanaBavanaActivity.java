package app.integro.dioceseofbangalore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.PalanaBavanAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.PalanaBavan;
import app.integro.dioceseofbangalore.models.PalanaBavanList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PalanaBavanaActivity extends AppCompatActivity {

    private RecyclerView rvPalana;
    private ArrayList<PalanaBavan> palanaBavanArrayList;
    private PalanaBavanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palana_bavan);

        rvPalana = findViewById(R.id.rvPalanaBavan);
        rvPalana.setLayoutManager(new LinearLayoutManager(this));
        palanaBavanArrayList = new ArrayList<>();

        getPalanaList();
    }

    public void getPalanaList() {
        String date = "2020-02-28 01:53:34";
        Call<PalanaBavanList> palanaBavanListCall = ApiClients.getClient().create(ApiServices.class).getPalanaBavanList(date);
        palanaBavanListCall.enqueue(new Callback<PalanaBavanList>() {
            @Override
            public void onResponse(Call<PalanaBavanList> call, Response<PalanaBavanList> response) {
                if (!response.isSuccessful()){
                    return;
                }
                if (response.body().getPalanaBavanArrayList()==null){
                    return;
                }
                int size=response.body().getPalanaBavanArrayList().size();
                for (int i=0 ;i<size;i++){
                    palanaBavanArrayList.add(response.body().getPalanaBavanArrayList().get(i));
                }
                adapter=new PalanaBavanAdapter(getApplicationContext(),palanaBavanArrayList);
                rvPalana.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PalanaBavanList> call, Throwable t) {

            }
        });
    }
}
