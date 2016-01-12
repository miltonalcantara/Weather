package com.lemonade.teste.previsaodotempo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lemonade.teste.previsaodotempo.modelo.Local;

public class DetalhesActivity extends AppCompatActivity {

    Local local;

    ImageView fotoLocal;
    TextView temperaturaDoDia;
    ImageView desenhoClima;
    TextView cidadePais;
    TextView diaDaSemana;
    TextView data;

    TextView tempMax;
    TextView tempMin;
    TextView vento;
    TextView nuvem;
    TextView pressao;
    TextView umidade;
    TextView solNascer;
    TextView solSePor;

    LinearLayout background;
    LinearLayout rl;

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        iniciandoViews();
        toolbar();
        rotacaoTela();

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
        String DiaDaSemana = local.getDiaSemana() + " | ";
        diaDaSemana.setText(DiaDaSemana);
        data.setText(local.getData());

        tempMax.setText(local.getTempMax());
        tempMin.setText(local.getTempMin());
        vento.setText(local.getVento().toUpperCase());
        nuvem.setText(local.getNuvens().toUpperCase());
        pressao.setText(local.getPressao().toUpperCase());
        umidade.setText(local.getUmidade());
        solNascer.setText(local.getSolNasce());
        solSePor.setText(local.getSolPoe());

        //Pegando a cor mais vibrante da foto e colocando no rodapé de informações das previsões futuras
        Bitmap foto = BitmapFactory.decodeResource(this.getResources(), local.getFoto());

        new Palette.Builder(foto).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
                assert vibrantLightSwatch != null;
                float[] vibrant = vibrantLightSwatch.getHsl();

                background.setBackgroundColor(Color.HSVToColor(vibrant));
            }
        });

    }

    //Tratar rotamento de tela pra Landscape, para as informações não ficarem em baixo da statusbar
    private void rotacaoTela() {
        if(getScreenOrientation() == 1 || getScreenOrientation() == 3){
            rl = (LinearLayout) findViewById(R.id.id_detalhes);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl.getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
            rl.setLayoutParams(params);
        }
    }

    //Pegando a rotação de tela
    private int getScreenOrientation(){
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        return display.getOrientation();
    }

    //iniciando as views
    public void iniciandoViews() {

        background = (LinearLayout) findViewById(R.id.id_background);

        fotoLocal = (ImageView) findViewById(R.id.id_foto_local);
        temperaturaDoDia = (TextView) findViewById(R.id.id_temperatura);
        desenhoClima = (ImageView) findViewById(R.id.id_desenho_clima);
        cidadePais = (TextView) findViewById(R.id.id_cidade_pais);
        diaDaSemana = (TextView) findViewById(R.id.id_dia_da_semana);
        data = (TextView) findViewById(R.id.id_data);

        tempMax = (TextView) findViewById(R.id.id_temp_max);
        tempMin = (TextView) findViewById(R.id.id_temp_min);
        vento = (TextView) findViewById(R.id.id_vento);
        nuvem = (TextView) findViewById(R.id.id_nuvem);
        pressao = (TextView) findViewById(R.id.id_pressao);
        umidade = (TextView) findViewById(R.id.id_umidade);
        solNascer = (TextView) findViewById(R.id.id_sol_nascer);
        solSePor = (TextView) findViewById(R.id.id_sol_se_por);

    }

    //Setando a Toolbar
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

    public void configuracoes(int i) {
        editor.putInt("cidadeEscolhida", i);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_configuracoes) {
            finishAffinity ();
            configuracoes(3);
            onBackPressed();
            startActivity(new Intent(this, PrincipalActivity.class));
            finishAffinity ();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

}
