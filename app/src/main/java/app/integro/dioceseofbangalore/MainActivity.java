package app.integro.dioceseofbangalore;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import app.integro.dioceseofbangalore.adapters.SliderAdapter;
import app.integro.dioceseofbangalore.firebase.MyFireBaseMessagingService;

import static app.integro.dioceseofbangalore.firebase.MyFireBaseMessagingService.NEWS_KEY;
import static app.integro.dioceseofbangalore.firebase.MyFireBaseMessagingService.NOTIFICATION_KEY;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    public static String Email, Password = " ";
    private static boolean flag = false;
    private TextView btnVIP;
    private TextView btnLogout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser vipuser = firebaseAuth.getCurrentUser();
        if (vipuser != null) {
            btnVIP.setEnabled(false);
            flag = true;
            btnVIP.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
            FirebaseMessaging.getInstance().subscribeToTopic("archdiocese");
            FirebaseMessaging.getInstance().subscribeToTopic("archdiocese-vip");
        } else {
            flag = false;
            btnVIP.setVisibility(View.VISIBLE);
            btnVIP.setEnabled(true);
            btnLogout.setVisibility(View.GONE);

            FirebaseMessaging.getInstance().subscribeToTopic("archdiocese");
        }
    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        @SuppressLint("CutPasteId")
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogout = findViewById(R.id.btnLogout);
        TextView tvDailyReading = findViewById(R.id.tvDailyReading);
        TextView tvCatholicPrayer = findViewById(R.id.tvCatholicPrayer);
        TextView tvSaint = findViewById(R.id.tvSaint);
        TextView tvDonate = findViewById(R.id.tvDonate);
        TextView tvCuria = findViewById(R.id.tvCuria);
        TextView tvPublications = findViewById(R.id.tvPublications);

        ImageView ivFacebook = findViewById(R.id.ivFacebook);
        ImageView ivInstagram = findViewById(R.id.ivInstagram);
        ImageView ivFlicker = findViewById(R.id.ivFlicker);
        ImageView ivRadio = findViewById(R.id.ivRadio);

        CardView mMessageCard = findViewById(R.id.MessageCard);

        LinearLayout llAboutUs = findViewById(R.id.llAboutUs);
        LinearLayout llCircular = findViewById(R.id.llCircular);

        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://www.facebook.com/bangalorearchdiocese";
                getUrl(uri);
            }
        });

        ivInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://www.instagram.com/bangalorearchdiocese/";
                getUrl(uri);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firebaseAuth.getCurrentUser() != null) {
                    firebaseAuth.signOut();
                    startActivity(getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

        ivFlicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://www.flickr.com/photos/bangalorearchdiocese/";
                getUrl(uri);
            }
        });

        ivRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://38.96.148.28:14308/index.html?sid=1";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        mMessageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrincipalMessageActivity.class);
                startActivity(intent);
            }
        });

        tvPublications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PublicationsActivity.class);
                startActivity(intent);
            }
        });

        tvCuria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CuriaActivity.class);
                startActivity(intent);
            }
        });

        tvDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://bangalorearchdiocesenews.org/archdiocese_donation/donation.php";
                getUrl(uri);
            }
        });

        llAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        llCircular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CircularActivity.class);
                startActivity(intent);
            }
        });

        final int color1 = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
        final int color2 = ContextCompat.getColor(getApplicationContext(), R.color.colorRed);

        ViewPager slidingViewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(slidingViewPager);

        slidingViewPager.setAdapter(new SliderAdapter(getSupportFragmentManager(), flag));
        tabLayout.getTabAt(0).setIcon(R.drawable.h1);
        tabLayout.getTabAt(0).getIcon().setColorFilter(color1, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).setIcon(R.drawable.n1);
        tabLayout.getTabAt(2).setIcon(R.drawable.nt1);
        tabLayout.getTabAt(3).setIcon(R.drawable.w2);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(color1, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(color2, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        boolean isFCMIntent = getIntent().getBooleanExtra(MyFireBaseMessagingService.TAG, false);
        if (isFCMIntent) {
            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case NEWS_KEY:
                    slidingViewPager.setCurrentItem(1);
                    break;
                case NOTIFICATION_KEY:
                    slidingViewPager.setCurrentItem(2);
                    break;
            }
        }

        tvCatholicPrayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://bangalorearchdiocesenews.org/prayers/devotion_collection.html";
                getUrl(uri);
            }
        });

        tvDailyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BibleActivity.class);
                startActivity(intent);
            }
        });
        tvSaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://bangalorearchdiocesenews.org/calendar_archdiocese/aug.html";
                getUrl(uri);
            }
        });


        btnVIP = findViewById(R.id.btnVIP);
        btnVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.vip_login, null);
                dialogBuilder.setView(view);

                final AlertDialog alertDialog = dialogBuilder.create();
                final EditText email = view.findViewById(R.id.emailId);
                email.setText("priest@gmail.com");
                email.setEnabled(false);
                final EditText password = view.findViewById(R.id.password);
                final Button loginVip = view.findViewById(R.id.btnSend);

                loginVip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean signInFlag = true;

                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        if (currentUser == null) {

                        }
                        if (TextUtils.isEmpty(email.getText().toString())) {
                            email.setError("Please Enter E - mail ID");
                            signInFlag = false;
                        }
                        if (TextUtils.isEmpty(password.getText().toString())) {
                            password.setError("Please Enter Password");
                            signInFlag = false;
                        }
                        if (signInFlag) {
                            Email = email.getText().toString();
                            Password = password.getText().toString();
                            firebaseAuth = FirebaseAuth.getInstance();
                            firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        if (user != null) {
                                            startActivity(getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "Please Contact Admin.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        AlertDialog.Builder builder = alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getUrl(String uri) {
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", uri);
        startActivity(intent);
    }
}
