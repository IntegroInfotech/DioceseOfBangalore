package app.integro.dioceseofbangalore.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.adapters.NotificationAdapter;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.apis.ApiServices;
import app.integro.dioceseofbangalore.models.Notification;
import app.integro.dioceseofbangalore.models.NotificationList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private ArrayList<Notification> notificationsArrayList;
    private RecyclerView rvNotification;
    private NotificationAdapter adapter;
    private boolean flag = false;

    public NotificationsFragment() {
        FirebaseUser vipUser = FirebaseAuth.getInstance().getCurrentUser();
        if (vipUser != null) {
            flag = true;
        } else {
            flag = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        rvNotification = (RecyclerView) view.findViewById(R.id.rvNotifications);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvNotification.setLayoutManager(manager);
        notificationsArrayList = new ArrayList<>();
        getNotification();

        return view;
    }

    private void getNotification() {
        String date = "2017-11-01 23:26:29";
        Call<NotificationList> notificationListCall = ApiClients.getClient().create(ApiServices.class).getNotificationsList(date);
        notificationListCall.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(@NonNull Call<NotificationList> call, @NonNull Response<NotificationList> response) {
                if (!response.isSuccessful()) {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                    return;
                }
                if (response.body().getNotificationList() == null) {
                    Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                int size = response.body().getNotificationList().size();
                Log.d("RESPONSE", "Notification " + size);
                for (int i = 0; i < size; i++) {
                    if (flag == true) {
                        notificationsArrayList.add(response.body().getNotificationList().get(i));
                    } else {
                        if (response.body().getNotificationList().get(i).getTopicname().contentEquals("archdiocese")) {
                            notificationsArrayList.add(response.body().getNotificationList().get(i));
                        }
                    }
                }
                adapter = new NotificationAdapter(getContext(), notificationsArrayList);
                rvNotification.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<NotificationList> call, @NonNull Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }


}
