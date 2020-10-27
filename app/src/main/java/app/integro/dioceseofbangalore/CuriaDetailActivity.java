package app.integro.dioceseofbangalore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.CuriaDataAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Curia;
import app.integro.dioceseofbangalore.models.CuriaData;
import app.integro.dioceseofbangalore.models.CuriaDataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuriaDetailActivity extends AppCompatActivity {
    private int itemId;
    private RecyclerView rvCuria2;
    private ArrayList<CuriaData> curiaDataArrayList;
    private CuriaDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curia_detail);

        Curia curia = (Curia) getIntent().getSerializableExtra("DATA");
        itemId = Integer.parseInt(curia.getId());

        rvCuria2 = findViewById(R.id.rvCuriaList);
        rvCuria2.setLayoutManager(new LinearLayoutManager(this));
        curiaDataArrayList = new ArrayList<>();
        getCuriaDataList();
    }

    private void getCuriaDataList() {
        Call<CuriaDataList> curiaDataListCall = ApiClients.getClient().create(ApiServices.class).getCuriaDataList("" + itemId);
        curiaDataListCall.enqueue(new Callback<CuriaDataList>() {
            @Override
            public void onResponse(Call<CuriaDataList> call, Response<CuriaDataList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getCuriaDataArrayList() == null) {
                    return;
                }
                int size = response.body().getCuriaDataArrayList().size();

                for (int i = 0; i < size; i++) {
                    curiaDataArrayList.add(response.body().getCuriaDataArrayList().get(i));
                }
                adapter=new CuriaDataAdapter(getApplicationContext(),curiaDataArrayList);
                rvCuria2.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<CuriaDataList> call, Throwable t) {

            }
        });
    }
}
