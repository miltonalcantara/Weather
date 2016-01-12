package com.lemonade.teste.previsaodotempo.modelo;

import com.lemonade.teste.previsaodotempo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milton Alcântara on 12/01/2016.
 */
public class CidadesMockadas {

    ArrayList<Local> listaLocais = new ArrayList<>();


    public ArrayList<Local> getSetListaMocada() {
        String[] nomes = new String[]{"Belem, BR", "Florianopolis, BR", "Sao Paulo, BR"};
        String[] temperaturas = new String[]{"28º", "23º", "22.9º"};
        String[] diasSemana = new String[]{"Domingo", "Domingo", "Domingo"};
        String[] datas = new String[]{"10/01/2016", "10/01/2016", "10/01/2016"};
        int[] icones = new int[]{R.drawable.ic_sol, R.drawable.ic_nuvem_sol_chuva, R.drawable.ic_chuva};
        int[] fotos = new int[]{R.drawable.img_belem, R.drawable.img_floripa, R.drawable.img_sp};
        String[] tempsMax = new String[]{"27.7º", "23°º", "24º"};
        String[] tempsMin = new String[]{"25.4º", "23.3º", "23º"};
        String[] ventos = new String[]{"Light breeze 2.6 m/s\nNorth-northeast (20)", "Gentle Breeze 3.6 m/s\nLight breeze 2.1 m/s East (80)", "Moderate breeze 5.7 m/s\nNorth-northwest (330)"};
        String[] nuvens = new String[]{"sky is clear", "overcast clouds", "overcast clouds"};
        String[] pressao = new String[]{"1010 hpa", "1009 hpa", "1016 hpa"};
        String[] umidade = new String[]{"83 %", "94 %", "83 %"};
        String[] solNasce = new String[]{"6:16", "5:29", "5:30"};
        String[] solPoe = new String[]{"18:28", "19:15", "18:59"};

        PrevisoesFuturas prev1Belem = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "28.6º", "26.5º", "Segunda", "11/01/2016");
        PrevisoesFuturas prev2Belem = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "28.3º", "26.2º", "Terça", "12/01/2016");
        PrevisoesFuturas prev3Belem = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "29.2º", "26.9º", "Quarta", "13/01/2016");
        PrevisoesFuturas prev4Belem = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "33.5º", "25.7º", "Quinta", "14/01/2016");
        PrevisoesFuturas prev5Belem = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "33.6º", "25.8º", "Sexta", "15/01/2016");

        PrevisoesFuturas prev1Floripa = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "27º", "23.7º", "Segunda", "11/01/2016");
        PrevisoesFuturas prev2Floripa = new PrevisoesFuturas(R.drawable.ic_nuvem_e_sol, "26.6º", "22.5º", "Terça", "12/01/2016");
        PrevisoesFuturas prev3Floripa = new PrevisoesFuturas(R.drawable.ic_sol, "27.6º", "22.8º", "Quarta", "13/01/2016");
        PrevisoesFuturas prev4Floripa = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "21.3º", "18.6º", "Quinta", "14/01/2016");
        PrevisoesFuturas prev5Floripa = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "23.5º", "18.7º", "Sexta", "15/01/2016");

        PrevisoesFuturas prev1SP = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "24.3º", "22.5º", "Segunda", "11/01/2016");
        PrevisoesFuturas prev2SP = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "23.8º", "22.3º", "Terça", "12/01/2016");
        PrevisoesFuturas prev3SP = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "23.8º", "21.6º", "Quarta", "13/01/2016");
        PrevisoesFuturas prev4SP = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "25.2º", "21.6º", "Quinta", "14/01/2016");
        PrevisoesFuturas prev5SP = new PrevisoesFuturas(R.drawable.ic_nuvem_sol_chuva, "24.3º", "21º", "Sexta", "15/01/2016");

        PrevisoesFuturas[] prev1 = new PrevisoesFuturas[]{prev1Belem, prev1Floripa, prev1SP};
        PrevisoesFuturas[] prev2 = new PrevisoesFuturas[]{prev2Belem, prev2Floripa, prev2SP};
        PrevisoesFuturas[] prev3 = new PrevisoesFuturas[]{prev3Belem, prev3Floripa, prev3SP};
        PrevisoesFuturas[] prev4 = new PrevisoesFuturas[]{prev4Belem, prev4Floripa, prev4SP};
        PrevisoesFuturas[] prev5 = new PrevisoesFuturas[]{prev5Belem, prev5Floripa, prev5SP};

        for (int i = 0; i < 3; i++) {
            Local c = new Local(nomes[i % nomes.length], temperaturas[i % temperaturas.length],
                    diasSemana[i % diasSemana.length],
                    datas[i % datas.length],
                    icones[i % nomes.length],
                    fotos[i % fotos.length],
                    tempsMax[i % tempsMax.length],
                    tempsMin[i % tempsMin.length],
                    ventos[i % ventos.length],
                    nuvens[i % nuvens.length],
                    pressao[i % pressao.length],
                    umidade[i % umidade.length],
                    solNasce[i % solNasce.length],
                    solPoe[i % solPoe.length],
                    prev1[i % prev1.length],
                    prev2[i % prev2.length],
                    prev3[i % prev3.length],
                    prev4[i % prev4.length],
                    prev5[i % prev5.length]
            );
            listaLocais.add(c);
        }
        return (listaLocais);
    }
}
