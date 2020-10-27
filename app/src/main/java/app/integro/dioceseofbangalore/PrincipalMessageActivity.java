package app.integro.dioceseofbangalore;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakira.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.adapters.PrincipalMessageAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.CircularList;
import app.integro.dioceseofbangalore.models.PrincipalMessage;
import app.integro.dioceseofbangalore.models.PrincipalMessageList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalMessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvBishops, tvData;
    private ArrayList<PrincipalMessage> principalMessageArrayList;
    private PrincipalMessageAdapter adapter;
    private RecyclerView rvMessage;
    private ExpandableLayout elBishopsEngagements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        rvMessage = findViewById(R.id.rvPrincipalMessage);
        principalMessageArrayList = new ArrayList<>();
        rvMessage.setLayoutManager(new LinearLayoutManager(this));

        elBishopsEngagements = findViewById(R.id.elBishopsEngagements);

        tvBishops = findViewById(R.id.tvBishops);
        tvData = findViewById(R.id.tvData);
        tvBishops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elBishopsEngagements.toggle();
                if (!elBishopsEngagements.isExpanded()) {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_up);
                    tvBishops.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                } else {
                    Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_down);
                    tvBishops.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                }
            }
        });

        getMessageList();
        getBishopsEngagementList();
    }

    public void getMessageList() {
        String date = "2018-03-24 23:46:28";
        retrofit2.Call<PrincipalMessageList> messageListCall = ApiClients.getClient().create(ApiServices.class).getMessageList(date);
        messageListCall.enqueue(new Callback<PrincipalMessageList>() {
            @Override
            public void onResponse(retrofit2.Call<PrincipalMessageList> call, Response<PrincipalMessageList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getPrincipalMessageArrayList() != null) {
                        int size = response.body().getPrincipalMessageArrayList().size();
                        for (int i = 0; i < size; i++) {
                            principalMessageArrayList.add(response.body().getPrincipalMessageArrayList().get(i));
                        }
                        adapter = new PrincipalMessageAdapter(getApplicationContext(), principalMessageArrayList);
                        rvMessage.setAdapter(adapter);

                    } else {
                        Toast.makeText(PrincipalMessageActivity.this, "No Messages Found...!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PrincipalMessageActivity.this, "Server Response Failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<PrincipalMessageList> call, Throwable t) {
                Toast.makeText(PrincipalMessageActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getBishopsEngagementList() {
        String date = "2017-11-02 23:55:42";
        Call<CircularList> circularListCall = ApiClients.getClient().create(ApiServices.class).getBishopsEngagementList(date);
        circularListCall.enqueue(new Callback<CircularList>() {
            @Override
            public void onResponse(Call<CircularList> call, Response<CircularList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCircularList() != null) {
                        int size = response.body().getCircularList().size();
                        Log.d("RESPONSE", "CircularList " + size);

                        if (response.body().getCircularList().size() > 0) {

                            tvData.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String url = response.body().getCircularList().get(0).getLink();
                                    launchContent(url);
                                }
                            });
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<CircularList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    private void launchContent(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorWebsite));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
