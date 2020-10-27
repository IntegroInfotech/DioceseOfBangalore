package app.integro.dioceseofbangalore;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.integro.dioceseofbangalore.Database.DatabaseClient;
import app.integro.dioceseofbangalore.adapters.ReligiousHousesDataAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.ReligiousHousesData;
import app.integro.dioceseofbangalore.models.ReligiousHousesDataList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReligiousHousesDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ReligiousHousesDetailsA";

    private ArrayList<ReligiousHousesData> religiousHousesDataArrayList;
    private RecyclerView rvReligiousHousesData;
    private ReligiousHousesDataAdapter adapter;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religious_houses_details);

        itemId = (int) getIntent().getSerializableExtra("DATA");

        religiousHousesDataArrayList = new ArrayList<>();
        rvReligiousHousesData = findViewById(R.id.rvReligiousHousesData);
        rvReligiousHousesData.setLayoutManager(new LinearLayoutManager(this));

        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                ArrayList<ReligiousHousesData> religiousHousesData = new ArrayList<>();
                for (ReligiousHousesData data : religiousHousesDataArrayList) {
                    String name = data.getName().toLowerCase();
                    String address = data.getDescription().toLowerCase();
                    if (name.contains(text.toString().toLowerCase()) || address.contains(text.toString().toLowerCase())) {
                        religiousHousesData.add(data);
                    }
                }
                adapter.setFilter(religiousHousesData);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getReligiousHousesList();
        showDataInList();
    }

    private void getReligiousHousesList() {
        Call<ReligiousHousesDataList> religiousHousesDataListCall =
                ApiClients.getClient().create(ApiServices.class).getReligiousHousesDataList("" + itemId);
        Log.i(TAG, "getReligiousHousesList: " + itemId);
        religiousHousesDataListCall.enqueue(new Callback<ReligiousHousesDataList>() {
            @Override
            public void onResponse(Call<ReligiousHousesDataList> call, Response<ReligiousHousesDataList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: response fail");
                    return;
                }
                if (response.body().getReligiousHousesData() == null) {
                    Toast.makeText(getApplicationContext(), "nothing found", Toast.LENGTH_SHORT).show();
                }
                // offline data
                religiousHousesDataArrayList = response.body().getReligiousHousesData();
                Log.i(TAG, "onResponse: " + religiousHousesDataArrayList.size());
                if (religiousHousesDataArrayList.size() > 0) {
                    storeDataInDB(religiousHousesDataArrayList);
                }


               /* // online data
                int size = response.body().getReligiousHousesData().size();
                for (int i = 0; i < size; i++) {
                    religiousHousesDataArrayList.add(response.body().getReligiousHousesData().get(i));
                }
                adapter = new ReligiousHousesDataAdapter(getApplicationContext(), religiousHousesDataArrayList);
                rvReligiousHousesData.setAdapter(adapter);*/
            }

            @Override
            public void onFailure(Call<ReligiousHousesDataList> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void storeDataInDB(final List<ReligiousHousesData> religiousHousesDataList) {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < religiousHousesDataList.size(); i++) {
                    ReligiousHousesData religiousHousesData = new ReligiousHousesData();
                    religiousHousesData.setId(religiousHousesDataList.get(i).getId());
                    religiousHousesData.setName(religiousHousesDataList.get(i).getName());
                    religiousHousesData.setDescription(religiousHousesDataList.get(i).getDescription());

                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .myDao()
                            .insert(religiousHousesData);
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
        class GetTasks extends AsyncTask<Void, Void, List<ReligiousHousesData>> {
            @Override
            protected List<ReligiousHousesData> doInBackground(Void... voids) {
                if (itemId != 0) {
                    List<ReligiousHousesData> religiousHousesDataList = DatabaseClient
                            .getInstance(getApplicationContext())
                            .getAppDatabase()
                            .myDao()
                            .getReligiousHousesData();
                    return religiousHousesDataList;
                } else {
                    return new ArrayList<>();
                }
            }

            @Override
            protected void onPostExecute(List<ReligiousHousesData> religiousHousesData) {
                super.onPostExecute(religiousHousesData);

                adapter = new ReligiousHousesDataAdapter(getApplicationContext(), religiousHousesData);
                rvReligiousHousesData.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
