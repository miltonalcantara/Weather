package com.lemonade.teste.previsaodotempo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lemonade.teste.previsaodotempo.modelo.Local;

public class TemperaturaActivity extends AppCompatActivity {

    Local local;

    LinearLayout background;
    ImageView fotoLocal;
    TextView temperaturaDoDia;
    ImageView desenhoClima;
    TextView cidadePais;
    TextView diaDaSemana;
    TextView data;

    FloatingActionButton fab;

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        iniciandoViews();
        toolbar();

        //Recuperando informações
        final Bundle b = this.getIntent().getExtras();
        local = b.getParcelable("local");

        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferencias.edit();

        //Setando as informações do dia
        assert local != null;
        fotoLocal.setImageDrawable(ContextCompat.getDrawable(this, local.getFoto()));
        temperaturaDoDia.setText(local.getTemperatura());
        desenhoClima.setImageDrawable(ContextCompat.getDrawable(this, local.getIcone()));
        cidadePais.setText(local.getNome());
        String diaDeSemana = local.getDiaSemana() + " | ";
        diaDaSemana.setText(diaDeSemana);
        data.setText(local.getData());

        //Pegando a cor mais vibrante da foto e colocando no rodapé de informações das previsões futuras
        Bitmap foto = BitmapFactory.decodeResource(this.getResources(), local.getFoto());

        new Palette.Builder(foto).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
                assert vibrantLightSwatch != null;
                float[] vibrant = vibrantLightSwatch.getHsl();

                Palette.Swatch vibrantDarkSwatch = palette.getVibrantSwatch();
                assert vibrantDarkSwatch != null;
                float[] dark = vibrantDarkSwatch.getHsl();

                fab.setBackgroundTintList(ColorStateList.valueOf(Color.HSVToColor(dark)));
                background.setBackgroundColor(Color.HSVToColor(vibrant));
            }
        });

        //Setar previsões futuras
        previsoesFuturas();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putParcelable("local", local);
                Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void configuracoes(int i) {
        editor.putInt("cidadeEscolhida", i);
        editor.commit();
    }

    public void iniciandoViews() {

        background = (LinearLayout) findViewById(R.id.id_background);

        fotoLocal = (ImageView) findViewById(R.id.id_foto_local);
        temperaturaDoDia = (TextView) findViewById(R.id.id_temperatura);
        desenhoClima = (ImageView) findViewById(R.id.id_desenho_clima);
        cidadePais = (TextView) findViewById(R.id.id_cidade_pais);
        diaDaSemana = (TextView) findViewById(R.id.id_dia_da_semana);
        data = (TextView) findViewById(R.id.id_data);

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void previsoesFuturas() {
        LayoutInflater linf;
        LinearLayout rr;

        linf = LayoutInflater.from(this);

        rr = (LinearLayout) findViewById(R.id.id_background);

        //Preenchendo as Previsões futuras
        for (int i = 0; i < 5; i++) {

            int endIcone = 0;
            String endTempMax = "";
            String endTempMin = "";
            String endDiaSemana = "";
            String endData = "";

            if (i == 0) {
                endIcone = local.getPrev1().getIcone();
                endTempMax = local.getPrev1().getTempMax();
                endTempMin = local.getPrev1().getTempMin();
                endDiaSemana = local.getPrev1().getDiaSemana();
                endData = local.getPrev1().getData();
            } else if (i == 1) {
                endIcone = local.getPrev2().getIcone();
                endTempMax = local.getPrev2().getTempMax();
                endTempMin = local.getPrev2().getTempMin();
                endDiaSemana = local.getPrev2().getDiaSemana();
                endData = local.getPrev2().getData();
            } else if (i == 2) {
                endIcone = local.getPrev3().getIcone();
                endTempMax = local.getPrev3().getTempMax();
                endTempMin = local.getPrev3().getTempMin();
                endDiaSemana = local.getPrev3().getDiaSemana();
                endData = local.getPrev3().getData();
            } else if (i == 3) {
                endIcone = local.getPrev4().getIcone();
                endTempMax = local.getPrev4().getTempMax();
                endTempMin = local.getPrev4().getTempMin();
                endDiaSemana = local.getPrev4().getDiaSemana();
                endData = local.getPrev4().getData();
            } else if (i == 4) {
                endIcone = local.getPrev5().getIcone();
                endTempMax = local.getPrev5().getTempMax();
                endTempMin = local.getPrev5().getTempMin();
                endDiaSemana = local.getPrev5().getDiaSemana();
                endData = local.getPrev5().getData();
            }
            final View v = linf.inflate(R.layout.include_temperaturadias, null);

            ImageView icone = (ImageView) v.findViewById(R.id.img_primeiro_dia);
            icone.setImageDrawable(ContextCompat.getDrawable(this, endIcone));
            TextView max = (TextView) v.findViewById(R.id.txt_temp_max);
            max.setText(endTempMax);
            TextView min = (TextView) v.findViewById(R.id.txt_temp_min);
            min.setText(endTempMin);
            TextView diaSemana = (TextView) v.findViewById(R.id.dia_semana);
            diaSemana.setText(endDiaSemana);
            TextView data = (TextView) v.findViewById(R.id.data);
            data.setText(endData);

            rr.addView(v);
        }

    }

    private void toolbar() {
        // Inicializando a Toolbar e setando com uma actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) toolbar.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
            toolbar.setLayoutParams(params);
        }


        //Colocando a seta na cor branca
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        assert upArrow != null;
        upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //Pegar a altura da statusbar para setar a barra em baixo dela
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_configuracoes) {
            configuracoes(3);
            onBackPressed();
            startActivity(new Intent(this, PrincipalActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

}
