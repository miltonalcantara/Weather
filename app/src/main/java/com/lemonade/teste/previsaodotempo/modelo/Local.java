package com.lemonade.teste.previsaodotempo.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Milton Alcântara on 08/01/2016.
 */
public class Local implements Parcelable {

    private String nome;
    private String temperatura;
    private String diaSemana;
    private String data;
    private int icone;
    private int foto;

    private String tempMax;
    private String tempMin;
    private String vento;
    private String nuvens;
    private String pressao;
    private String umidade;
    private String solNasce;
    private String solPoe;

    private PrevisoesFuturas prev1;
    private PrevisoesFuturas prev2;
    private PrevisoesFuturas prev3;
    private PrevisoesFuturas prev4;
    private PrevisoesFuturas prev5;

    public Local(String nome,
                 String temperatura,
                 String diaSemana,
                 String data,
                 int icone,
                 int foto,
                 String tempMax,
                 String tempMin,
                 String vento,
                 String nuvens,
                 String pressao,
                 String umidade,
                 String solNasce,
                 String solPoe,
                 PrevisoesFuturas prev1,
                 PrevisoesFuturas prev2,
                 PrevisoesFuturas prev3,
                 PrevisoesFuturas prev4,
                 PrevisoesFuturas prev5
    ) {

        this.nome = nome;
        this.temperatura = temperatura;
        this.diaSemana = diaSemana;
        this.data = data;
        this.icone = icone;
        this.foto = foto;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.vento = vento;
        this.nuvens = nuvens;
        this.pressao = pressao;
        this.umidade = umidade;
        this.solNasce = solNasce;
        this.solPoe = solPoe;
        this.prev1 = prev1;
        this.prev2 = prev2;
        this.prev3 = prev3;
        this.prev4 = prev4;
        this.prev5 = prev5;

    }

    public String getNome() {
        return nome;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIcone() {
        return icone;
    }

    public int getFoto() {
        return foto;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getVento() {
        return vento;
    }

    public String getNuvens() {
        return nuvens;
    }

    public String getPressao() {
        return pressao;
    }

    public String getUmidade() {
        return umidade;
    }

    public String getSolNasce() {
        return solNasce;
    }

    public String getSolPoe() {
        return solPoe;
    }

    public PrevisoesFuturas getPrev1() {
        return prev1;
    }

    public PrevisoesFuturas getPrev2() {
        return prev2;
    }

    public PrevisoesFuturas getPrev3() {
        return prev3;
    }

    public PrevisoesFuturas getPrev4() {
        return prev4;
    }

    public PrevisoesFuturas getPrev5() {
        return prev5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Local(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(foto);
        dest.writeInt(icone);
        dest.writeString(nome);
        dest.writeString(temperatura);
        dest.writeString(diaSemana);
        dest.writeString(data);
        dest.writeString(tempMin);
        dest.writeString(tempMax);
        dest.writeString(vento);
        dest.writeString(nuvens);
        dest.writeString(pressao);
        dest.writeString(umidade);
        dest.writeString(solNasce);
        dest.writeString(solPoe);
        dest.writeParcelable(prev1 , flags);
        dest.writeParcelable(prev2 , flags);
        dest.writeParcelable(prev3 , flags);
        dest.writeParcelable(prev4 , flags);
        dest.writeParcelable(prev5 , flags);
    }

    private void readFromParcel(Parcel in) {
        foto = in.readInt();
        icone = in.readInt();
        nome = in.readString();
        temperatura = in.readString();
        diaSemana = in.readString();
        data = in.readString();
        tempMin = in.readString();
        tempMax = in.readString();
        vento = in.readString();
        nuvens = in.readString();
        pressao = in.readString();
        umidade = in.readString();
        solNasce = in.readString();
        solPoe = in.readString();
        prev1 = in.readParcelable(PrevisoesFuturas.class.getClassLoader());
        prev2 = in.readParcelable(PrevisoesFuturas.class.getClassLoader());
        prev3 = in.readParcelable(PrevisoesFuturas.class.getClassLoader());
        prev4 = in.readParcelable(PrevisoesFuturas.class.getClassLoader());
        prev5 = in.readParcelable(PrevisoesFuturas.class.getClassLoader());
    }

    public static final Creator<Local> CREATOR = new Creator<Local>() {

        @Override
        public Local createFromParcel(Parcel source) {
            return new Local(source);
        }

        @Override
        public Local[] newArray(int size) {
            return new Local[size];
        }
    };
}