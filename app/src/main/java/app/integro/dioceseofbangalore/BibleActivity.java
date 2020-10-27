package app.integro.dioceseofbangalore;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import app.integro.dioceseofbangalore.Database.DatabaseClient;
import app.integro.dioceseofbangalore.adapters.CustomSwipeAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.WordOfGod;
import app.integro.dioceseofbangalore.models.WordofgodList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BibleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "BibleActivity";
    boolean flag=false;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CustomSwipeAdapter adapter;
    private ArrayList<WordOfGod> wordOfGodArrayList;
    private AppBarLayout appBarLayout;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat1 = new SimpleDateFormat("d MMMM yyyy", /*Locale.getDefault()*/Locale.ENGLISH);
    private CompactCalendarView compactCalendarView;
    private boolean isExpanded = false;
    private Date selecetdDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wordOfGodArrayList = new ArrayList<>();

        viewPager = findViewById(R.id.fragmentHolder);
        tabLayout = findViewById(R.id.tabs);
        appBarLayout = findViewById(R.id.AppbarLay);
        ImageView arrow = findViewById(R.id.date_picker_arrow);
        LinearLayout datePickerButton = findViewById(R.id.date_picker_button);
        TextView textView = findViewById(R.id.name);
        textView.setText("WORD OF GOD");

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getDefault(), /*Locale.getDefault()*/Locale.ENGLISH);
        compactCalendarView.setShouldDrawDaysHeader(true);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                setSubtitle(dateFormat1.format(dateClicked));
                getWordOfGod(dateFormat.format(dateClicked));
                selecetdDate = dateClicked;
                isExpanded = !isExpanded;
                appBarLayout.setExpanded(isExpanded, true);
                showDataInList();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setSubtitle(dateFormat1.format(firstDayOfNewMonth));
            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    ViewCompat.animate(arrow).rotation(0).start();
                } else {
                    ViewCompat.animate(arrow).rotation(90).start();
                }

                isExpanded = !isExpanded;
                appBarLayout.setExpanded(isExpanded, true);
            }
        });

        // Set current date to today
        setCurrentDate(new Date());
        showDataInList();
    }

    private void setCurrentDate(Date date) {
        setSubtitle(dateFormat1.format(date));
        if (compactCalendarView != null) {
            compactCalendarView.setCurrentDate(date);
            getWordOfGod(dateFormat.format(date));
            selecetdDate = date;
        }
        //Log.i(TAG, "setCurrentDate: "+dateFormat.format(date));
    }


    private void getWordOfGod(String date) {
        Log.i(TAG, "getWordOfGod: " + date);

        Call<WordofgodList> call = ApiClients.getClient().create(ApiServices.class).getWordOfGod(date);
        call.enqueue(new Callback<WordofgodList>() {
            @Override
            public void onResponse(Call<WordofgodList> call, Response<WordofgodList> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: fail");
                    return;
                }
                if (response.body().getWordofgod()==null){
                    Log.i(TAG, "onResponse: null");
                    return;
                }
                if (response.body() != null && response.body().getSuccess().equals("1")) {
                    wordOfGodArrayList = response.body().getWordofgod();
                    if (wordOfGodArrayList.size() > 0) {
                        storeDataInDB(wordOfGodArrayList);
                    }
                    showDataInList();

                } else {
                    adapter = new CustomSwipeAdapter(getApplicationContext());
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager);
                    Toast.makeText(getApplicationContext(), " " + response.body().getMessage() + " ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WordofgodList> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    @Override
    public void setTitle(CharSequence title) {
        TextView tvTitle = findViewById(R.id.title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    private void setSubtitle(String subtitle) {
        TextView datePickerTextView = findViewById(R.id.date_picker_text_view);

        if (datePickerTextView != null) {
            datePickerTextView.setText(subtitle);
        }
    }


    private void storeDataInDB(final List<WordOfGod> wordOfGodList) {
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < wordOfGodList.size(); i++) {
                    WordOfGod wordOfGod = new WordOfGod();
                    wordOfGod.setId(wordOfGodList.get(i).getId());
                    wordOfGod.setDate(wordOfGodList.get(i).getDate());
                    wordOfGod.setReading(wordOfGodList.get(i).getReading());
                    wordOfGod.setReading_type(wordOfGodList.get(i).getReading_type());
                    wordOfGod.setTitle(wordOfGodList.get(i).getTitle());
                    wordOfGod.setVerse(wordOfGodList.get(i).getVerse());
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .myDao()
                            .insert(wordOfGod);
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
        class GetTasks extends AsyncTask<String, Void, List<WordOfGod>> {

            @Override
            protected List<WordOfGod> doInBackground(String... strings) {
                List<WordOfGod> wordOfGodList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .myDao()
                        .getWordOfGod(dateFormat.format(selecetdDate));

                return wordOfGodList;
            }

            @Override
            protected void onPostExecute(List<WordOfGod> wordOfGodList) {
                super.onPostExecute(wordOfGodList);
                adapter = new CustomSwipeAdapter(getApplicationContext(),wordOfGodList,dateFormat.format(selecetdDate));
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
                flag = true;
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute(dateFormat.format(selecetdDate));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

