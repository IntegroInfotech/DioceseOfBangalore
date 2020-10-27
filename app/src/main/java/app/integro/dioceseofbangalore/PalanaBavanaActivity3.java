package app.integro.dioceseofbangalore;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.integro.dioceseofbangalore.models.PalanaBavana2;

public class PalanaBavanaActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palana_bavana3);

        TextView tvTitle=findViewById(R.id.tvTitle);
        TextView tvDescription=findViewById(R.id.tvDescription);
        PalanaBavana2 palanaBavana2 = (PalanaBavana2) getIntent().getSerializableExtra("DATA");
        tvTitle.setText(palanaBavana2.getName());
        tvDescription.setText(palanaBavana2.getDescription());

    }
}
