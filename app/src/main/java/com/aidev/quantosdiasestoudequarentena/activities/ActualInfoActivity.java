package com.aidev.quantosdiasestoudequarentena.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aidev.quantosdiasestoudequarentena.Preferencias;
import com.aidev.quantosdiasestoudequarentena.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ActualInfoActivity extends AppCompatActivity {


    private AdView mAdView;
    private Preferencias preferencias;
    private TextView dias, horas, semanas, inicio;
    private Button voltar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_info);
        preferencias = new Preferencias(getApplicationContext());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        voltar = findViewById(R.id.buttonVoltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizarInformacoes();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        atualizarInformacoes();
    }

    private void atualizarInformacoes() {

        inicio = findViewById(R.id.txt_inicio);
        dias = findViewById(R.id.txt_diasPercorridos);
        horas = findViewById(R.id.txt_horas);
        semanas = findViewById(R.id.txt_semanas);


        String data = preferencias.recuperarString();

        inicio.setText(data);

        String diasS = preferencias.recuperarDias().toString();
        String horasS = preferencias.recuperarHoras().toString();
        String semanasS = preferencias.recuperarSemanas().toString();

        dias.setText(diasS);
        horas.setText(horasS);
        semanas.setText(semanasS);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.info) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);


    }

}