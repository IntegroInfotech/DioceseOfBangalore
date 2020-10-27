package app.integro.dioceseofbangalore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.CuriaAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Curia;
import app.integro.dioceseofbangalore.models.CuriaList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuriaActivity extends AppCompatActivity {

    private RecyclerView rvCuria;
    private ArrayList<Curia> curiaArrayList;
    private CuriaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curia);

        rvCuria = findViewById(R.id.rvCuria);
        rvCuria.setLayoutManager(new LinearLayoutManager(this));
        curiaArrayList = new ArrayList<>();
        getCuriaList();
    }

    private void getCuriaList() {
        String date = "2020-03-02 21:27:19";
        Call<CuriaList> curiaListCall = ApiClients.getClient().create(ApiServices.class).getCuriaList(date);
        curiaListCall.enqueue(new Callback<CuriaList>() {
            @Override
            public void onResponse(Call<CuriaList> call, Response<CuriaList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getCuriaArrayList() == null) {
                    return;
                }
                int size = response.body().getCuriaArrayList().size();
                for (int i = 0; i < size; i++) {
                    curiaArrayList.add(response.body().getCuriaArrayList().get(i));
                }
                adapter=new CuriaAdapter(getApplicationContext(),curiaArrayList);
                rvCuria.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<CuriaList> call, Throwable t) {

            }
        });
    }
}
