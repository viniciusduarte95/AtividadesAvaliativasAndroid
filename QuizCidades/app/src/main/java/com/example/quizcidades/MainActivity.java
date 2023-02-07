package com.example.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciar(View view) {
        HashMap<String, String> cidades = preencheCidades();
        Intent it = new Intent(getApplicationContext(), NewGameActivity.class);
        it.putExtra("cidades", cidades);
        startActivity(it);
    }

    public HashMap<String, String> preencheCidades() {
        HashMap<String, String> cidades = new HashMap<>();
        cidades.put("Barcelona", "01_barcelona.jpg");
        cidades.put("Brasília", "02_brasilia.jpg");
        cidades.put("Curitiba", "03_curitiba.jpg");
        cidades.put("Las Vegas", "04_lasvegas.jpg");
        cidades.put("Montreal", "05_montreal.jpg");
        cidades.put("Paris", "06_paris.jpg");
        cidades.put("Rio de Janeiro", "07_riodejaneiro.jpg");
        cidades.put("Salvador", "08_salvador.jpg");
        cidades.put("São Paulo", "09_saopaulo.jpg");
        cidades.put("Tóquio", "10_toquio.jpg");
        return cidades;
    }

}