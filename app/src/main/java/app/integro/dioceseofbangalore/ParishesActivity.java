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
import app.integro.dioceseofbangalore.adapters.ParishesAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Parishes;
import app.integro.dioceseofbangalore.models.ParishesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParishesActivity extends AppCompatActivity {

    private RecyclerView rvMass;
    private ArrayList<Parishes> parishesArrayList;
    private ParishesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parishes);

        parishesArrayList = new ArrayList<>();
        rvMass = findViewById(R.id.rvMass);
        rvMass.setLayoutManager(new LinearLayoutManager(this));

        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {      }
            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                ArrayList<Parishes> parishesArrayList1 = new ArrayList<>();
                for (Parishes parishes : parishesArrayList) {
                    String name = parishes.getName().toLowerCase();
                    String address = parishes.getAddress().toLowerCase();
                    if (name.contains(text.toString().toLowerCase()) || address.contains(text.toString().toLowerCase())) {
                        parishesArrayList1.add(parishes);
                    }
                }
                adapter.setFilter(parishesArrayList1);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getMassList();
        showDataInList();
    }

    public void getMassList() {
        String date = "2017-11-02 23:55:42";
        Call<ParishesList> massListCall = ApiClients.getClient().create(ApiServices.class).getMassList(date);
        massListCall.enqueue(new Callback<ParishesList>() {
            @Override
            public void onResponse(Call<ParishesList> call, Response<ParishesList> response) {
                if (!response.isSuccessful()) {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                    return;
                }
                if (response.body().getParishesList() == null) {
                    Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    return;
                }

                parishesArrayList = (ArrayList<Parishes>) response.body().getParishesList();
                if (parishesArrayList.size() > 0) {
                    storeDataInDB(parishesArrayList);
                }

            }

            @Override
            public void onFailure(Call<ParishesList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    private void storeDataInDB(final List<Parishes> parishesList) {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < parishesList.size(); i++) {
                    Parishes parishes = new Parishes();
                    parishes.setId(parishesList.get(i).getId());
                    parishes.setName(parishesList.get(i).getName());
                    parishes.setAddress(parishesList.get(i).getAddress());
                    parishes.setPriest(parishesList.get(i).getPriest());
                    parishes.setApriest(parishesList.get(i).getApriest());
                    parishes.setTiming(parishesList.get(i).getTiming());
                    parishes.setSuntiming(parishesList.get(i).getSuntiming());
                    parishes.setAdoration(parishesList.get(i).getAdoration());
                    parishes.setContact(parishesList.get(i).getContact());
                    parishes.setEmail(parishesList.get(i).getEmail());
                    parishes.setWebsite(parishesList.get(i).getWebsite());
                    parishes.setImage(parishesList.get(i).getImage());
                    parishes.setUpdated_date(parishesList.get(i).getUpdated_date());
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .myDao()
                            .insert(parishes);
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
        class GetTasks extends AsyncTask<Void, Void, List<Parishes>> {
            @Override
            protected List<Parishes> doInBackground(Void... voids) {
                List<Parishes> parishesList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .myDao()
                        .getParishes();
                return parishesList;
            }

            @Override
            protected void onPostExecute(List<Parishes> parishesList) {
                super.onPostExecute(parishesList);
                adapter = new ParishesAdapter(getApplicationContext(), (ArrayList<Parishes>) parishesList);
                rvMass.setAdapter(adapter);
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
