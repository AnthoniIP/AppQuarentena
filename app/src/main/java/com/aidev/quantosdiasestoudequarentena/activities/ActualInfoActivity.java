package com.aidev.quantosdiasestoudequarentena.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ActualInfoActivity extends AppCompatActivity {


    private LocalDate inicioData, hojeData;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        atualizarInformacoes();

        inicioData = LocalDate.parse(preferencias.recuperarString());


        hojeData = LocalDate.now();

        long daysBetween = DAYS.between(inicioData, hojeData);
        long semanasD = daysBetween / 7;
        long horasD = daysBetween * 24;

        atualizarInformacoes();


        dias.setText(String.valueOf(daysBetween));
        horas.setText(String.valueOf(horasD));
        semanas.setText(String.valueOf(semanasD));



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

        }else if(id == R.id.remover_anuncios) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    "https://play.google.com/store/apps/details?id=com.aidev.quantosdiasestoudequarentenanoads"));
            intent.setPackage("com.android.vending");
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