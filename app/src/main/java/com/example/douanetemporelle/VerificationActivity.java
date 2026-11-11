package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {
// Ecran mte3 el scanner de paradoxe w verification mta3 el passeport

    private TextView tvNom, tvAge, tvMotif;
    private Button btnApprouver, btnRefuser;
    private String nomVoyageur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        tvNom = findViewById(R.id.tv_res_nom_nj);
        tvAge = findViewById(R.id.tv_res_age_nj);
        tvMotif = findViewById(R.id.tv_res_motif_nj);
        btnApprouver = findViewById(R.id.btn_approuver_nj);
        btnRefuser = findViewById(R.id.btn_refuser_nj);

        String matricule4Digits = "1234";

        Intent intent = getIntent();
        nomVoyageur = intent.getStringExtra(KeyUtils.buildKey(matricule4Digits, "NOM_VOYAGEUR"));
        int anneeNaiss = intent.getIntExtra(KeyUtils.buildKey(matricule4Digits, "ANNEE_NAISSANCE"), 0);
        int anneeDest = intent.getIntExtra(KeyUtils.buildKey(matricule4Digits, "ANNEE_DESTINATION"), 0);
        String motif = intent.getStringExtra(KeyUtils.buildKey(matricule4Digits, "MOTIF_VOYAGE"));

        int diffAnnees = Math.abs(anneeDest - anneeNaiss);

        tvNom.setText("Nom : " + nomVoyageur);
        tvAge.setText("Écart temporel : " + diffAnnees + " ans");
        tvMotif.setText("Motif : " + motif);
// Test ken motif 'Modification du passé' w diff annees nombre premier daharli ta3mel disable button

        if ("Modification du passé".equals(motif) && estNombrePremier(diffAnnees)) {
            btnApprouver.setEnabled(false);
        }

        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerResultat(true);
            }
        });

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerResultat(false);
            }
        });
    }

    private void lancerResultat(boolean approuve) {
        String matricule4Digits = "1234";

        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(KeyUtils.buildKey(matricule4Digits, "VERDICT"), approuve);
        intent.putExtra(KeyUtils.buildKey(matricule4Digits, "NOM_VOYAGEUR"), nomVoyageur);
        startActivity(intent);
    }

    private boolean estNombrePremier(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}