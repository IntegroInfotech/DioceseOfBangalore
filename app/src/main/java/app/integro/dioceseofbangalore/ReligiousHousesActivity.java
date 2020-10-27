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
import app.integro.dioceseofbangalore.adapters.ReligiousHousesAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.ReligiousHouses;
import app.integro.dioceseofbangalore.models.ReligiousHousesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReligiousHousesActivity extends AppCompatActivity {

    private static final String TAG = "ReligiousHousesActivity";
    private ArrayList<ReligiousHouses> religiousHousesArrayList;
    private RecyclerView rvReligiousHouses;
    private ReligiousHousesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religious_houses);

        religiousHousesArrayList = new ArrayList<>();
        rvReligiousHouses = findViewById(R.id.rvReligiousHouses);
        rvReligiousHouses.setLayoutManager(new LinearLayoutManager(this));

        getReligiousHousesList();
        showDataInList();
    }

    private void getReligiousHousesList() {
        String date = "2020-01-18 21:17:15";
        Call<ReligiousHousesList> religiousHousesListCall =
                ApiClients.getClient().create(ApiServices.class).getReligiousHousesList(date);
        religiousHousesListCall.enqueue(new Callback<ReligiousHousesList>() {
            @Override
            public void onResponse(@NonNull Call<ReligiousHousesList> call, @NonNull Response<ReligiousHousesList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: response fail");
                    return;
                }
                if (response.body().getReligiousHousesArrayList() == null) {
                    Toast.makeText(ReligiousHousesActivity.this, "nothing found", Toast.LENGTH_SHORT).show();
                }

                religiousHousesArrayList = response.body().getReligiousHousesArrayList();
                if (religiousHousesArrayList.size() > 0) {
                    storeDataInDB(religiousHousesArrayList);
                }

            }

            @Override
            public void onFailure(@NonNull Call<ReligiousHousesList> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private void storeDataInDB(final List<ReligiousHouses> religiousHousesList) {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < religiousHousesList.size(); i++) {
                    ReligiousHouses religiousHouses = new ReligiousHouses();
                    religiousHouses.setId(religiousHousesList.get(i).getId());
                    religiousHouses.setName(religiousHousesList.get(i).getName());
                    religiousHouses.setUpdated_date(religiousHousesList.get(i).getUpdated_date());

                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .myDao()
                            .insert(religiousHouses);
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
        class GetTasks extends AsyncTask<Void, Void, List<ReligiousHouses>> {
            @Override
            protected List<ReligiousHouses> doInBackground(Void... voids) {
                List<ReligiousHouses> religiousHousesList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .myDao()
                        .getReligiousHouses();
                return religiousHousesList;
            }

            @Override
            protected void onPostExecute(List<ReligiousHouses> religiousHouses) {
                super.onPostExecute(religiousHouses);
                adapter=new ReligiousHousesAdapter(getApplicationContext(),religiousHouses);
                rvReligiousHouses.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
