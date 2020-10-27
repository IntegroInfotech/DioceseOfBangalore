package app.integro.dioceseofbangalore;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.integro.dioceseofbangalore.Database.DatabaseClient;
import app.integro.dioceseofbangalore.adapters.ABEInstitutionAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.InstitutionList;
import app.integro.dioceseofbangalore.models.Institutions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ABEInstitutionActivity extends AppCompatActivity {

    private static final String TAG = "ABEInstitutionActivity";
    private RecyclerView rvInstitutions;
    private ArrayList<Institutions> linksArrayList;
    private ABEInstitutionAdapter adapter;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abd);

        tag = getIntent().getStringExtra("tag");
        Log.i(TAG, "onCreate: "+tag);

        linksArrayList = new ArrayList<>();
        rvInstitutions = findViewById(R.id.rvInstitutions);
        rvInstitutions.setLayoutManager(new LinearLayoutManager(this));

        getInstitutionList();
        showDataInList();
    }

    public void getInstitutionList() {
        Call<InstitutionList> linksListCall = ApiClients.getClient().create(ApiServices.class).getInstitutionList(tag);
        linksListCall.enqueue(new Callback<InstitutionList>() {
            @Override
            public void onResponse(@NonNull Call<InstitutionList> call, @NonNull Response<InstitutionList> response) {
                if (!response.isSuccessful()) {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                    return;
                }
                Log.i(TAG, "onResponse: "+response.body());
                if (response.body().getInstitutionArrayList() == null) {
                    Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                linksArrayList = response.body().getInstitutionArrayList();
                if (linksArrayList.size() > 0) {
                    storeDataInDB(linksArrayList);
                }
            }

            @Override
            public void onFailure(Call<InstitutionList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    private void storeDataInDB(final List<Institutions> institutionList) {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < institutionList.size(); i++) {
                    Institutions abeInstitution = new Institutions(
                            institutionList.get(i).getId(),
                            institutionList.get(i).getName(),
                            institutionList.get(i).getTag());
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .myDao()
                            .insert(abeInstitution);
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showDataInList();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }

    private void showDataInList() {
        @SuppressLint("StaticFieldLeak")
        class GetTasks extends AsyncTask<Void, Void, List<Institutions>> {
            @Override
            protected List<Institutions> doInBackground(Void... voids) {
                List<Institutions> abeInstitutionList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .myDao()
                        .getInstitutions(tag);
                return abeInstitutionList;
            }

            @Override
            protected void onPostExecute(List<Institutions> institutions) {
                super.onPostExecute(institutions);
                adapter = new ABEInstitutionAdapter(getApplicationContext(), institutions);
                rvInstitutions.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
