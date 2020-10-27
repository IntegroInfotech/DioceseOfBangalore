package app.integro.dioceseofbangalore;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.integro.dioceseofbangalore.Database.DatabaseClient;
import app.integro.dioceseofbangalore.adapters.ABEInstitutionDataAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.ABEInstitutionData;
import app.integro.dioceseofbangalore.models.ABEInstitutionDataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ABEInstitutionDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ABEInstitutionDetailsAc";
    int itemId;
    private ArrayList<ABEInstitutionData> abeInstitutionDataArrayList;
    private RecyclerView rvInstitutionsData;
    private ABEInstitutionDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_data2);

        itemId = (int) getIntent().getSerializableExtra("DATA");

        abeInstitutionDataArrayList = new ArrayList<>();
        rvInstitutionsData = findViewById(R.id.rvAbe);
        rvInstitutionsData.setLayoutManager(new LinearLayoutManager(this));

        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                ArrayList<ABEInstitutionData> abeInstitutionData = new ArrayList<>();
                for (ABEInstitutionData data : abeInstitutionDataArrayList) {
                    String name = data.getName().toLowerCase();
                    String address = data.getDescription().toLowerCase();
                    if (name.contains(text.toString().toLowerCase()) || address.contains(text.toString().toLowerCase())) {
                        abeInstitutionData.add(data);
                    }
                }
                adapter.setFilter(abeInstitutionData);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getInstitutionDataList();
        showDataInList();
    }

    public void getInstitutionDataList() {
        Call<ABEInstitutionDataList> descriptionListCall =
                ApiClients.getClient().create(ApiServices.class).getInstitutionDataList(String.valueOf(itemId));
        descriptionListCall.enqueue(new Callback<ABEInstitutionDataList>() {
            @Override
            public void onResponse(@NonNull Call<ABEInstitutionDataList> call, @NonNull Response<ABEInstitutionDataList> response) {
                if (!response.isSuccessful()) {
                    Log.i("RESPONSE", "RESPONSE FAIL");
                    return;
                }
                if (response.body().getABEInstitutionData() == null) {
                    Toast.makeText(getApplicationContext(), "No Items Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                abeInstitutionDataArrayList = response.body().getABEInstitutionData();
                if (abeInstitutionDataArrayList.size() > 0) {
                    storeDataInDB(abeInstitutionDataArrayList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ABEInstitutionDataList> call, @NonNull Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL" + t.getMessage());
            }
        });
    }

    private void storeDataInDB(final List<ABEInstitutionData> institutionList) {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < institutionList.size(); i++) {
                    ABEInstitutionData abeInstitutionData = new ABEInstitutionData();
                    abeInstitutionData.setId(institutionList.get(i).getId());
                    abeInstitutionData.setName(institutionList.get(i).getName());
                    abeInstitutionData.setOid(institutionList.get(i).getOid());
                    abeInstitutionData.setDescription(institutionList.get(i).getDescription());
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .myDao()
                            .insert(abeInstitutionData);
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
        class GetTasks extends AsyncTask<Void, Void, List<ABEInstitutionData>> {
            @Override
            protected List<ABEInstitutionData> doInBackground(Void... voids) {
                if (itemId != 0) {
                    List<ABEInstitutionData> abeInstitutionDataList = DatabaseClient
                            .getInstance(getApplicationContext())
                            .getAppDatabase()
                            .myDao()
                            .getAbeInstitutionData(itemId);
                    return abeInstitutionDataList;
                } else {
                    return new ArrayList<>();
                }
            }

            @Override
            protected void onPostExecute(List<ABEInstitutionData> institutions) {
                super.onPostExecute(institutions);
                Log.i(TAG, "onPostExecute: "+institutions.toString());
                adapter = new ABEInstitutionDataAdapter(getApplicationContext(), institutions);
                rvInstitutionsData.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
