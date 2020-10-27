package app.integro.dioceseofbangalore.firebase;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import app.integro.dioceseofbangalore.ParishesActivity;
import app.integro.dioceseofbangalore.MainActivity;
import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.models.ADS;
import app.integro.dioceseofbangalore.models.Parishes;
import app.integro.dioceseofbangalore.models.News;
import app.integro.dioceseofbangalore.models.Notification;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFireBaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "MyFirebaseMsgService";
    public static final String TYPE = "type";
    public static final String BODY = "body";
    public static final String PARISHES_KEY = "parishes";
    public static final String NEWS_KEY = "news";
    public static final String NOTIFICATION_KEY = "notifications";
    public static final String NOTIFICATION_CHANNEL_ID = "4655";
    public static final String NOTIFICATION_CHANNEL_NAME = "notification_channel_name";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "data payload: " + remoteMessage.getData());
        }
        sendNotification(remoteMessage.getData());
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification(Map <String, String> data) {
        Log.d(TAG, "In function ");
        try {
            Log.d(TAG, "In try ");
            int type = Integer.parseInt(data.get(TYPE));
            String body = data.get(BODY);
            Intent intent = null;
            String title = null;
            String img = null;
            String description = null;
            NotificationCompat.Builder notificationBuilder;
            PendingIntent pendingIntent;

            if (type == 2) {
                Log.d(TAG, "In type 2 ");
                Parishes parishesItem = (Parishes) new Gson().fromJson(body, Parishes.class);
                title = parishesItem.getName();
                description = parishesItem.getAddress();
                intent = new Intent(this, ParishesActivity.class);
                intent.putExtra(TYPE, PARISHES_KEY);
            }
            if (type == 3) {
                Notification notificationItem = (Notification) new Gson().fromJson(body, Notification.class);
                title = notificationItem.getTitle();
                description = notificationItem.getDescription();
                intent = new Intent(this, MainActivity.class);
                intent.putExtra(TYPE, NOTIFICATION_KEY);
            }
            if (type == 4) {
                News newsItem = (News) new Gson().fromJson(body, News.class);
                img = newsItem.getL_img().toString();
                title = newsItem.getTitle();
                description = newsItem.getDescription();
                intent = new Intent(this, MainActivity.class);
                intent.putExtra(TYPE, NEWS_KEY);
            }
            if (type == 5) {
                Log.d(TAG, "in type 5");
                Notification notificationItem = (Notification) new Gson().fromJson(body, Notification.class);
                title = notificationItem.getTitle();
                description = notificationItem.getDescription();
                intent = new Intent(this, MainActivity.class);
                intent.putExtra(TYPE, NOTIFICATION_KEY);

                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_custom);
                remoteViews.setImageViewResource(R.id.custNotificationLogo, R.drawable.logo051);
                remoteViews.setTextViewText(R.id.custNotificationTitle, title);
                remoteViews.setTextViewText(R.id.custNotificationDescpn, description);
            }

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.oringz1);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importanceDefault = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importanceDefault);
                channel.setDescription("description");
                notificationManager.createNotificationChannel(channel);
                channel.setSound(sound, channel.getAudioAttributes());
            }

            intent.putExtra(TAG, true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.oringz1);
            mediaPlayer.start();
            notificationBuilder = new NotificationCompat.Builder(this);
            notificationBuilder
                    .setSmallIcon(R.drawable.logo051)
                    .setContentTitle(title)
                    .setContentText(description)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setSound(sound)
                    .setDefaults(android.app.Notification.DEFAULT_ALL)
                    .setContentIntent(pendingIntent);
            //setting BigPicture in Notifications
            Bitmap bitmap_image = getBitmapFromURL(img);
            if (bitmap_image == null) {
                notificationBuilder.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(description));
            } else {
                Log.d("IMAGE", "" + bitmap_image);
                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
                notificationBuilder.setStyle(style);
                notificationBuilder.setLargeIcon(bitmap_image);
            }

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(Integer.parseInt("001"), notificationBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "CATCH" + e.getMessage());
        }
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}