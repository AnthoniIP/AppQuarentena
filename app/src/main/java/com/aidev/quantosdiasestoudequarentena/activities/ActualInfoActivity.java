package com.aidev.quantosdiasestoudequarentena.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.aidev.quantosdiasestoudequarentena.Preferencias;
import com.aidev.quantosdiasestoudequarentena.R;
import com.google.android.gms.ads.AdView;

public class ActualInfoActivity extends AppCompatActivity {


    private Preferencias preferencias;
    private TextView dias, horas, semanas, inicio;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_info);
        preferencias = new Preferencias(getApplicationContext());


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
}