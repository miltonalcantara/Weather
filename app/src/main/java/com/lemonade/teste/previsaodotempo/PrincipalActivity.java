package com.lemonade.teste.previsaodotempo;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lemonade.teste.previsaodotempo.modelo.CidadesMockadas;
import com.lemonade.teste.previsaodotempo.modelo.Local;
import com.lemonade.teste.previsaodotempo.modelo.PrevisoesFuturas;

import java.util.ArrayList;
import java.util.List;


public class PrincipalActivity extends AppCompatActivity {

    ArrayList<Local> listaLocais = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        // Inicializando a Toolbar e setando com uma actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);

        //Cidades Mockadas
        CidadesMockadas cidades = new CidadesMockadas();
        listaLocais = cidades.getSetListaMocada();

        ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<>();
        list.add("Belém");
        list.add("Florianópolis");
        list.add("São Paulo");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        // Seta o adapter para o ListView
        listview.setAdapter(adapter);

        preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferencias.edit();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                Bundle b = new Bundle();
                if (item.equalsIgnoreCase("Belém")) {
                    b.putParcelable("local", listaLocais.get(0));
                    configuracoes(0);
                } else if (item.equalsIgnoreCase("Florianópolis")) {
                    b.putParcelable("local", listaLocais.get(1));
                    configuracoes(1);
                } else if (item.equalsIgnoreCase("São Paulo")) {
                    b.putParcelable("local", listaLocais.get(2));
                    configuracoes(2);
                }

                Intent intent = new Intent(getApplicationContext(), TemperaturaActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                finishAffinity ();
            }
        });
    }

    public void configuracoes(int i) {
        editor.putInt("cidadeEscolhida", i);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        // Obtendo o SearchView e plugando o SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //Filtro SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

}
