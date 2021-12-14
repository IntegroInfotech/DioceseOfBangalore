package app.integro.dioceseofbangalore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstitutionsActivity extends AppCompatActivity {

    static String ABE = "ABE";
    static String R_EDU_INST = "R_EDU_INST";
    static String OTR_INST = "OTR_INST";
    static String CENTER_INST="CENTRE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutions);

        TextView tvABE = findViewById(R.id.tvAbE);
        TextView tvOI = findViewById(R.id.tvOI);
        TextView tvRI = findViewById(R.id.tvRI);
        TextView tvCI = findViewById(R.id.tvCI);

        Intent intent = new Intent(getApplicationContext(), ABEInstitutionActivity.class);

        tvABE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("tag", ABE);
                startActivity(intent);
            }
        });

        tvRI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("tag", R_EDU_INST);
                startActivity(intent);
            }
        });

        tvOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("tag", OTR_INST);
                startActivity(intent);
            }
        });

        tvCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("tag", CENTER_INST);
                startActivity(intent);
            }
        });
    }
}
