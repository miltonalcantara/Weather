package com.lemonade.teste.previsaodotempo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.lemonade.teste.previsaodotempo.modelo.CidadesMockadas;
import com.lemonade.teste.previsaodotempo.modelo.Local;

import java.util.ArrayList;

/**
 * Created by Milton Alcântara on 07/01/2016.
 */
public class SplashScreenActivity extends Activity implements Runnable, OnClickListener {

    private Handler handler = new Handler();
    private boolean started;

    private int cidadeEscolhida;
    ArrayList<Local> listaLocais = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        RelativeLayout splash = (RelativeLayout) findViewById(R.id.id_splash);
        splash.setOnClickListener(this);
        handler.postDelayed(this, 2000);

        //Cidades Mockadas
        CidadesMockadas cidades = new CidadesMockadas();
        listaLocais = cidades.getSetListaMocada();

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        cidadeEscolhida = preferencias.getInt("cidadeEscolhida", 3);

    }

    public void run() {
        iniciarPrograma();
    }

    public void onClick(View v) {
        iniciarPrograma();
    }


    private synchronized void iniciarPrograma() {

        if (!started) {
            started = true;
            if (cidadeEscolhida != 3) {
                int BELEM = 0;
                int FLORIPA = 1;
                int SAMPA = 2;
                Bundle b = new Bundle();

                if (cidadeEscolhida == BELEM)
                    b.putParcelable("local", listaLocais.get(0));
                else if (cidadeEscolhida == FLORIPA)
                    b.putParcelable("local", listaLocais.get(1));
                else if (cidadeEscolhida == SAMPA)
                    b.putParcelable("local", listaLocais.get(2));


                Intent intent = new Intent(getApplicationContext(), TemperaturaActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            } else {
                Intent i = new Intent(SplashScreenActivity.this, PrincipalActivity.class);
                startActivity(i);
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}
