package com.example.notasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mediaView = findViewById(R.id.mediaTextView);
        TextView nomeView = findViewById(R.id.nomeTextView);
        TextView situacaoView = findViewById(R.id.situacaoTextView);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        String media = params.getString("media");
        String situacao = params.getString("situacao");
        String nome = params.getString("nome");
        mediaView.setText(media);
        nomeView.setText(nome);
        situacaoView.setText(situacao);


    }


}