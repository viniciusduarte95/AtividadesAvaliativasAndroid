package com.example.quizcidades;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MyTask extends AsyncTask<String, Void, Bitmap> {

    ImageView output;
    ProgressDialog progress;

    public MyTask(ImageView output, ProgressDialog progress) {
        this.output = output;
        this.progress = progress;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progress.setMessage("Buscando cidade...");
        this.progress.setCancelable(false);
        this.progress.show();
    }

    @Override
    protected void onPostExecute(Bitmap img) {
        super.onPostExecute(img);
        this.progress.dismiss();
        this.output.setImageBitmap(img);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String stringURL = strings[0];
        Bitmap myImage = null;
        try {
            URL url = new URL(stringURL);
            InputStream inputStream = url.openStream();
            myImage = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e("GET", "doInBackground: GET IMAGEM - " + e.getMessage(), e);
            e.printStackTrace();
        }
        return myImage;
    }
}
