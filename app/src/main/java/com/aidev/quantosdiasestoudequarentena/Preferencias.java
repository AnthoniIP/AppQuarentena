package com.aidev.quantosdiasestoudequarentena;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.time.LocalDate;

/*

created by Anthoni Ipiranga

*/   public class Preferencias {


    private Context context;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "quarentena.preferencias";
    private final String TODAY = "today";
    private final String INICIO = "inicio";
    private SharedPreferences.Editor editor;

    private LocalDate inicio, hoje;
    private Long daysBetween, semanas, horas;


    public Preferencias(Context c) {

        this.context = c;
        this.preferences = context.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void salvarData(LocalDate inicio, LocalDate hoje) {

        this.inicio = inicio;
        this.hoje = hoje;


        Gson gson = new Gson();
        String json = gson.toJson(inicio);
        editor.putString(INICIO, json);
        editor.commit();

        Gson gson2 = new Gson();
        json = gson2.toJson(hoje);
        editor.putString(TODAY, json);
        editor.commit();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate recuperarInicio() {


        Gson gson = new Gson();
        String json = preferences.getString(INICIO, "");
        LocalDate inicio = gson.fromJson(json, LocalDate.class);

        return inicio;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate recuperarHoje() {

        hoje = LocalDate.now();

        return hoje;


    }

    public void salvarDias(long dias, long semanas, long horas) {

        this.daysBetween = dias;
        this.horas = horas;
        this.semanas = semanas;

        editor.putLong("dias", this.daysBetween);
        editor.commit();

        editor.putLong("horas", this.horas);
        editor.commit();

        editor.putLong("semanas", this.semanas);
        editor.commit();


    }

    public Long recuperarDias() {


        return preferences.getLong("dias", 0);

    }

    public Long recuperarSemanas() {
        return preferences.getLong("semanas", 0);
    }

    public Long recuperarHoras() {
        return preferences.getLong("horas", 0);
    }

    public void salvarString(String qualquer) {

        editor.putString("inicio_string", qualquer);
        editor.commit();

    }

    public String recuperarString() {

        return preferences.getString("inicio_string", "00");
    }
}
