package app.integro.dioceseofbangalore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.PalanaBavana2Adapater;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.PalanaBavan;
import app.integro.dioceseofbangalore.models.PalanaBavana2;
import app.integro.dioceseofbangalore.models.PalanaBavanaList2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PalanaBavanaActivity2 extends AppCompatActivity {
    int itemId;

    private RecyclerView rvPalana2;
    private ArrayList<PalanaBavana2> palanaBavana2ArrayList;
    private PalanaBavana2Adapater adapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palana_bavana2);

        rvPalana2 = findViewById(R.id.rvPalana2);
        palanaBavana2ArrayList = new ArrayList<>();
        rvPalana2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        PalanaBavan palanaBavan = (PalanaBavan) getIntent().getSerializableExtra("DATA");

        itemId = Integer.parseInt(palanaBavan.getId());
        getPalana2List();
    }

    public void getPalana2List() {
        Call<PalanaBavanaList2> palanaBavanaList2Call = ApiClients.getClient().create(ApiServices.class).getPalanaBavanaList2("" + itemId);
        palanaBavanaList2Call.enqueue(new Callback<PalanaBavanaList2>() {
            @Override
            public void onResponse(Call<PalanaBavanaList2> call, Response<PalanaBavanaList2> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getBavana2ArrayList() == null) {
                    return;
                }
                int size = response.body().getBavana2ArrayList().size();
                for (int i = 0; i < size; i++) {
                    palanaBavana2ArrayList.add(response.body().getBavana2ArrayList().get(i));
                    adapater = new PalanaBavana2Adapater(getApplicationContext(), palanaBavana2ArrayList);
                    rvPalana2.setAdapter(adapater);
                }
            }

            @Override
            public void onFailure(Call<PalanaBavanaList2> call, Throwable t) {

            }
        });
    }
}
