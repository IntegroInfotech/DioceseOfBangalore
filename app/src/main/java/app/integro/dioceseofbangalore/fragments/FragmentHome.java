package app.integro.dioceseofbangalore.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.demono.AutoScrollViewPager;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.InstitutionsActivity;
import app.integro.dioceseofbangalore.PalanaBavanaActivity;
import app.integro.dioceseofbangalore.ParishesActivity;
import app.integro.dioceseofbangalore.PrincipalMessageActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.ReligiousHousesActivity;
import app.integro.dioceseofbangalore.adapters.ADSAdapter;
import app.integro.dioceseofbangalore.adapters.NewsViewPagerAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.ADS;
import app.integro.dioceseofbangalore.models.ADSList;
import app.integro.dioceseofbangalore.models.News;
import app.integro.dioceseofbangalore.models.NewsList;
import app.integro.dioceseofbangalore.models.PrincipalMessage;
import app.integro.dioceseofbangalore.models.PrincipalMessageList;
import app.integro.dioceseofbangalore.models.Videos;
import app.integro.dioceseofbangalore.models.VideosList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    private static final String API_KEY = "AIzaSyDI8lwUCJgNkMIOMYF9FaafwIWpKZ_J-ng";
    private YouTubePlayer mPlayer;
    private static final String TAG = "FragmentHome";

    private ApiServices apiServices;
    private ArrayList<PrincipalMessage> principalMessageArrayList;
    private TextView tvPrincipal;
    private TextView tvDesignation;

    private AutoScrollViewPager vpNews;
    private ArrayList<News> newsArrayList;
    private NewsViewPagerAdapter adapter;
    private ArrayList<Videos> videosArrayList;

    private ADSAdapter adsAdapter;
    private ArrayList<ADS> adsArrayList;
    private AutoScrollViewPager rvAds;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiServices = ApiClients.getClient().create(ApiServices.class);
        vpNews = view.findViewById(R.id.vpNews);
        newsArrayList = new ArrayList<>();

        rvAds = view.findViewById(R.id.rvADS);
        adsArrayList =new ArrayList<>();
        rvAds=view.findViewById(R.id.rvAds);
        //rvAds.setLayoutManager(new LinearLayoutManager(getContext()));

        tvPrincipal = view.findViewById(R.id.tvPrincipal);
        tvDesignation = view.findViewById(R.id.tvDesignation);

        TextView tvPalanaBavan = view.findViewById(R.id.tvPalanaBavan);
        TextView tvReligiousHouses = view.findViewById(R.id.tvReligiousHouses);

        LinearLayout llMessage = view.findViewById(R.id.llMessage);
        LinearLayout llInstitutions = view.findViewById(R.id.llInstitutions);
        LinearLayout llChurches = view.findViewById(R.id.llChurches);


        llInstitutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InstitutionsActivity.class);
                startActivity(intent);
            }
        });

        llChurches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ParishesActivity.class);
                startActivity(intent);
            }
        });

        tvPalanaBavan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), PalanaBavanaActivity.class);
                startActivity(intent);
            }
        });

        tvReligiousHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReligiousHousesActivity.class);
                startActivity(intent);
            }
        });

        llMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrincipalMessageActivity.class);
                startActivity(intent);
            }
        });

        getNews();
        getPrincipalMessage();
        getVideosList();
        getADSList();
        return view;
    }

    public void getADSList() {
        Call<ADSList> adsCall = ApiClients.getClient().create(ApiServices.class).getADSList();
        adsCall.enqueue(new Callback<ADSList>() {
            @Override
            public void onResponse(@NonNull Call<ADSList> call, @NonNull Response<ADSList> response) {
                if (!response.isSuccessful()) {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                    return;
                }
                Log.i(TAG, "onResponse: " + response.body());
                if (response.body().getAdsArrayList() == null) {
                    Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                int size = response.body().getAdsArrayList().size();
                for (int i = 0; i < size; i++) {
                    adsArrayList.add(response.body().getAdsArrayList().get(i));
                }
                adsAdapter=new ADSAdapter(getContext(),adsArrayList);
                rvAds.startAutoScroll(3000);
                rvAds.setCycle(true);
                rvAds.setAdapter(adsAdapter);
            }

            @Override
            public void onFailure(Call<ADSList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    private void getNews() {
        String date = "2017-11-01 23:26:29";
        Call<NewsList> newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getNewsList() == null) {
                    return;
                }
                int size = response.body().getNewsList().size();
                for (int i = 0; i < 5 ; i++) {
                    newsArrayList.add(response.body().getNewsList().get(i));
                }
                adapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                vpNews.setAdapter(adapter);
                vpNews.startAutoScroll(3000);
                vpNews.setCycle(true);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    private void getPrincipalMessage() {
        principalMessageArrayList = new ArrayList<>();
        String date = "2018-03-24 23:46:28";
        Call<PrincipalMessageList> messageListCall = apiServices.getMessageList(date);
        messageListCall.enqueue(new Callback<PrincipalMessageList>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull retrofit2.Call<PrincipalMessageList> call, @NonNull Response<PrincipalMessageList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null && response.body().getPrincipalMessageArrayList() == null) {
                    return;
                }
                int size = response.body().getPrincipalMessageArrayList().size();
                for (int i = 0; i < size; i++) {
                    principalMessageArrayList.add(response.body().getPrincipalMessageArrayList().get(i));
                    tvPrincipal.setText(principalMessageArrayList.get(0).getName());
                    tvDesignation.setText("Archbishop of Bangalore");
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<PrincipalMessageList> call, @NonNull Throwable t) {

            }
        });
    }

    private void getVideosList() {
        videosArrayList = new ArrayList<>();
        String date = "2018-03-24 23:46:28";
        Call<VideosList> videosListCall = apiServices.getVideosList(date);
        videosListCall.enqueue(new Callback<VideosList>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull retrofit2.Call<VideosList> call, @NonNull Response<VideosList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null && response.body().getVideosList() == null) {
                    return;
                }
                int size = response.body().getVideosList().size();
                for (int i = 0; i < size; i++) {
                    videosArrayList.add(response.body().getVideosList().get(i));
                }
                YouTubePlayerSupportFragment youTubePlayerFragment = new YouTubePlayerSupportFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();
                youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                        if (!b) {
                            mPlayer = youTubePlayer;
                            mPlayer.cueVideo("" + videosArrayList.get(0).getV_id());
                            Log.i(TAG, "onInitializationSuccess: " + videosArrayList.get(0).getV_id());
                            mPlayer.setFullscreen(false);
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                        // TODO Auto-generated method stub
                    }
                });
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<VideosList> call, @NonNull Throwable t) {

            }
        });
    }
}
