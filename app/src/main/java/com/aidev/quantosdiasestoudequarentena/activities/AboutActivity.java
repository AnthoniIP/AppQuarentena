package com.aidev.quantosdiasestoudequarentena.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.aidev.quantosdiasestoudequarentena.R;

import mehdi.sakout.aboutpage.AboutPage;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.ic_baseline_info_24)
                .setDescription("Aplicativo desenvolvido por Anthoni Ipiranga.")
                .addGroup("Me contate")
                .addEmail("anthoni.ipiranga@gmail.com", "Email")
                .addYoutube("UCzCp52wboLCtBRsI4W7xD_w", "Youtube")
                .addGitHub("AnthoniIP", "GitHub")
                .addInstagram("_anthoniipiranga_", "Instagram")
                .create();


        setContentView(aboutPage);


    }
}