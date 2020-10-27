package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.OrganisationAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Organisation;
import app.integro.dioceseofbangalore.models.OrganisationList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganisationActivity extends AppCompatActivity {

    private RecyclerView rvOrg;
    private ArrayList<Organisation> organisationArrayList;
    private OrganisationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);

        rvOrg =  findViewById(R.id.rvOrg);
        organisationArrayList = new ArrayList<>();
        rvOrg.setLayoutManager(new LinearLayoutManager(this));

        getInstitutionList();
    }

    public void getInstitutionList() {
        String date = "2017-11-14 21:50:22";
        Call<OrganisationList> organisationListCall = ApiClients.getClient().create(ApiServices.class).getOrganisationList(date);
        organisationListCall.enqueue(new Callback<OrganisationList>() {
            @Override
            public void onResponse(Call<OrganisationList> call, Response<OrganisationList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getOrganisationArrayList() != null) {
                        int size = response.body().getOrganisationArrayList().size();
                        Log.d("RESPONSE", "OrganisationList " + size);
                        for (int i = 0; i < size; i++) {
                            organisationArrayList.add(response.body().getOrganisationArrayList().get(i));
                            adapter = new OrganisationAdapter(getApplicationContext(), organisationArrayList);
                            rvOrg.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<OrganisationList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
