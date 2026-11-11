package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Classe principale mta3 l formulaire d'enregistrement
public class MainActivity extends AppCompatActivity {

    private EditText etNom, etAnneeNaissance, etAnneeDest;
    private Spinner spMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation mta3 l views
        etNom = findViewById(R.id.et_nom_nj);
        etAnneeNaissance = findViewById(R.id.et_annee_naissance_nj);
        etAnneeDest = findViewById(R.id.et_annee_dest_nj);
        spMotif = findViewById(R.id.sp_motif_nj);
        btnInspecter = findViewById(R.id.btn_inspecter_nj);

        // Configuration mta3 l Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.motifs_array_nj, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMotif.setAdapter(adapter);

        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = etNom.getText().toString().trim();
                String naissStr = etAnneeNaissance.getText().toString().trim();
                String destStr = etAnneeDest.getText().toString().trim();

                if (nom.isEmpty() || naissStr.isEmpty() || destStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                String matricule4Digits = "1234";

                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
                intent.putExtra(KeyUtils.buildKey(matricule4Digits, "NOM_VOYAGEUR"), nom);
                intent.putExtra(KeyUtils.buildKey(matricule4Digits, "ANNEE_NAISSANCE"), Integer.parseInt(naissStr));
                intent.putExtra(KeyUtils.buildKey(matricule4Digits, "ANNEE_DESTINATION"), Integer.parseInt(destStr));
                intent.putExtra(KeyUtils.buildKey(matricule4Digits, "MOTIF_VOYAGE"), spMotif.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}