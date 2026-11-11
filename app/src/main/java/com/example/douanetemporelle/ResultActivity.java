
package com.example.douanetemporelle;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.douanetemporelle.MainActivity;
import com.example.douanetemporelle.R;

public class ResultActivity extends AppCompatActivity {

    private LinearLayout llBackground;
    private TextView tvVerdictMsg;
    private Button btnNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        llBackground = findViewById(R.id.ll_result_bg_nj);
        tvVerdictMsg = findViewById(R.id.tv_verdict_msg_nj);
        btnNouveau = findViewById(R.id.btn_nouveau_nj);

        String matricule4Digits = "1234";

        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra(KeyUtils.buildKey(matricule4Digits, "VERDICT"), false);
        String nomVoyageur = intent.getStringExtra(KeyUtils.buildKey(matricule4Digits, "NOM_VOYAGEUR"));

        if (nomVoyageur == null) {
            nomVoyageur = "Voyageur";
        }

        if (approuve) {
            llBackground.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvVerdictMsg.setText("Bon voyage " + nomVoyageur + " !");
        } else {
            llBackground.setBackgroundColor(Color.parseColor("#C62828"));
            tvVerdictMsg.setText(nomVoyageur + " est en état d'arrestation temporelle !");
        }

        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(ResultActivity.this, MainActivity.class);
                backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(backIntent);
                finish();
            }
        });
    }
}