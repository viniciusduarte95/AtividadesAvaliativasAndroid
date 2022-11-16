package com.example.notasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calcMedia (View view){
        TextView name_input = findViewById(R.id.editAlunoText);
        TextView freq_input = findViewById(R.id.editFreqText);
        TextView nota1_input = findViewById(R.id.editNota1Text);
        TextView nota2_input = findViewById(R.id.editNota2Text);

        try {
            String situacao;
            String nome = name_input.getText().toString();
            int freq = Integer.parseInt(freq_input.getText().toString());
            Double nota1 = Double.parseDouble(nota1_input.getText().toString());
            Double nota2 = Double.parseDouble(nota2_input.getText().toString());

            Double media = (nota1 + nota2) / 2;

            if (100 < freq || 0 > freq || 10 < nota1 || 0 > nota1 || 10 < nota2 || 0 > nota2){
                Toast.makeText(this, " Os valores inseridos não são válidos!", Toast.LENGTH_SHORT).show();
            } else {

                if (media < 4) {
                    situacao = "Reprovado por nota!";
                } else if (freq < 75) {
                    situacao = "Reprovado por falta!";
                } else {
                    if (media >= 7) {
                        situacao = "Aprovado!";
                    } else {
                        situacao = "Final!";
                    }
                }
                Intent it = new Intent(this, DetailActivity.class);
                Bundle params = new Bundle();
                params.putString("media", String.valueOf(media));
                params.putString("situacao", String.valueOf(situacao));
                params.putString("nome", String.valueOf(nome));
                it.putExtras(params);
                startActivity(it);
            }
        }catch (Exception e){

            Toast.makeText(this, "Os campos não podem ficar em branco e/ou estão inválidos!", Toast.LENGTH_SHORT).show();
        }

    }


}