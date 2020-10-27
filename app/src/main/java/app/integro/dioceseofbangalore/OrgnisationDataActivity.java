package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.OrganisationDataAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.OrganisationData;
import app.integro.dioceseofbangalore.models.OrganisationDataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrgnisationDataActivity extends AppCompatActivity {

    private RecyclerView rvOrg2;
    private ArrayList<OrganisationData> organisationDataArrayList;
    private OrganisationDataAdapter adapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgnisation_data);

        id = (String) getIntent().getSerializableExtra("data");

        organisationDataArrayList = new ArrayList<>();
        rvOrg2 = findViewById(R.id.rvOrg2);
        rvOrg2.setLayoutManager(new LinearLayoutManager(this));
        getInstitutionList();
    }

    public void getInstitutionList() {
        Call<OrganisationDataList> organisationDataListCall = ApiClients.getClient().create(ApiServices.class).getOrganisationDataList(id);
        organisationDataListCall.enqueue(new Callback<OrganisationDataList>() {
            @Override
            public void onResponse(Call<OrganisationDataList> call, Response<OrganisationDataList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getOrganisationDataArrayList() != null) {
                        int size = response.body().getOrganisationDataArrayList().size();
                        Log.d("RESPONSE", "OrganisationList " + size);
                        for (int i = 0; i < size; i++) {
                            organisationDataArrayList.add(response.body().getOrganisationDataArrayList().get(i));
                            adapter = new OrganisationDataAdapter(getApplicationContext(), organisationDataArrayList);
                            rvOrg2.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<OrganisationDataList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
