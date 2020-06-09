package com.aidev.quantosdiasestoudequarentena.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.aidev.quantosdiasestoudequarentena.Preferencias;
import com.aidev.quantosdiasestoudequarentena.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.CalendarView;
import android.widget.Toast;


import java.time.LocalDate;
import java.util.Calendar;

import static java.time.temporal.ChronoUnit.DAYS;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private CalendarView calendario;
    private LocalDate inicio, hoje;

    private Preferencias preferencias;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        preferencias = new Preferencias(getApplicationContext());


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        calendario = findViewById(R.id.calendarView);
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.hide();
        fab.setImageResource(R.drawable.ic_baseline_done_24);


        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Calendar gc = Calendar.getInstance();
                gc.set(year, month, dayOfMonth);


                inicio = LocalDate.of(year, month + 1, dayOfMonth);


                hoje = LocalDate.now();
                preferencias.salvarData(inicio, hoje);

                preferencias.salvarString(inicio.toString());

                fab.show();


            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                long daysBetween = DAYS.between(inicio, hoje);
                long semanas = daysBetween / 7;
                long horas = daysBetween * 24;

                if (daysBetween <= 0) {

                    Toast.makeText(getApplicationContext(), "Informe uma data menor que a data de hoje", Toast.LENGTH_SHORT).show();
                    fab.hide();

                } else {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Dias de quarentena");
                    dialog.setMessage(
                            "Você está há " + daysBetween + " dias de quarentena. \n" +
                                    "Número de semanas: " + semanas +

                                    "\nNúmero de horas: " + horas


                    );

                    preferencias.salvarDias(daysBetween, semanas, horas);


                    dialog.setCancelable(true);

                    dialog.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            dialog.dismiss();
                            preparaExibicaoPersonalizada();

                        }
                    });

                    dialog.create();
                    dialog.show();


                }


            }
        });

        preparaExibicaoPersonalizada();

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void preparaExibicaoPersonalizada() {

        if (preferencias.recuperarInicio() != null) {

            Intent intent = new Intent(this, ActualInfoActivity.class);
            startActivity(intent);



        }


    }


}
