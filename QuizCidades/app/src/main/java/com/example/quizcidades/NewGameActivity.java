package com.example.quizcidades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity {

    ImageView imageViewCidade;
    TextView textViewResposta;
    EditText editTextCidade;
    Button buttonEnviar, buttonProxima;
    HashMap<String, String> cidades;
    String cidade;
    int cont, pontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        imageViewCidade = findViewById(R.id.imageViewCidade);
        textViewResposta = findViewById(R.id.textViewResposta);
        buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonProxima = findViewById(R.id.buttonProxima);
        editTextCidade = findViewById(R.id.editTextCidade);

        cont = 0;
        pontuacao = 0;
        Intent it = getIntent();
        cidades = (HashMap<String, String>) it.getSerializableExtra("cidades");
        if (cidades == null) {
            Toast.makeText(this, "Erro ao encontrar imagens", Toast.LENGTH_SHORT).show();
            finish();
        }
        cidade = sorteiaCidade(cidades);
    }

    public String sorteiaCidade(@NonNull HashMap cidades) {
        Object[] arrayKeys = cidades.keySet().toArray();
        Object key = arrayKeys[new Random().nextInt(arrayKeys.length)];
        String cidade = key.toString();
        String imagem = (String) cidades.get(key.toString());
        ProgressDialog progressDialog = new ProgressDialog(this);
        String url = "http://31.220.51.31/ufpr/cidades/" + imagem;
        MyTask task = new MyTask(imageViewCidade, progressDialog);
        task.execute(url);
        cont++;
        Log.i("CIDADE", "sorteiaCidade: " + cidade);
        return cidade;
    }

    public void verificaResposta(View view) {
        String mensagem = "";
        int msgColor;

        if (editTextCidade.length() == 0) {
            Toast.makeText(this, "Digite sua resposta!", Toast.LENGTH_SHORT).show();
            return;
        }

        String resposta = editTextCidade.getText().toString();
        if (resposta.equalsIgnoreCase(cidade)) {
            pontuacao += 25;
            mensagem = "Resposta Correta!";
            msgColor = R.color.right_answer;
        } else {
            mensagem = "Resposta Errada!\nA resposta correta é: " + cidade;
            msgColor =  R.color.wrong_answer;
        }
        textViewResposta.setText(mensagem);
        textViewResposta.setTextColor(ContextCompat.getColor(getApplicationContext(), msgColor));

        editTextCidade.setEnabled(false);
        buttonEnviar.setEnabled(false);
        buttonProxima.setEnabled(true);
        buttonProxima.setVisibility(View.VISIBLE);
    }

    public void proximaPergunta(View view) {
        if (cont >=4) {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pontuacao", pontuacao);
            bundle.putSerializable("cidades", cidades);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else {
            buttonProxima.setEnabled(false);
            buttonProxima.setVisibility(View.INVISIBLE);
            editTextCidade.setEnabled(true);
            editTextCidade.setText("");
            textViewResposta.setText("");
            buttonEnviar.setEnabled(true);
            cidade = sorteiaCidade(cidades);
        }

    }

}