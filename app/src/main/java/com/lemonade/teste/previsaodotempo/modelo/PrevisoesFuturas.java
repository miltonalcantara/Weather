package com.lemonade.teste.previsaodotempo.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Milton Alcântara on 10/01/2016.
 */
public class PrevisoesFuturas implements Parcelable {

    private int icone;
    private String tempMax;
    private String tempMin;
    private String diaSemana;
    private String data;

    public PrevisoesFuturas(int icone,
                 String tempMax,
                 String tempMin,
                 String diaSemana,
                 String data) {

        this.icone = icone;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.diaSemana = diaSemana;
        this.data = data;

    }

    public int getIcone() {
        return icone;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
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


    @Override
    public int describeContents() {
        return 0;
    }

    public PrevisoesFuturas(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icone);
        dest.writeString(tempMin);
        dest.writeString(tempMax);
        dest.writeString(diaSemana);
        dest.writeString(data);
    }

    private void readFromParcel(Parcel in) {
        icone = in.readInt();
        tempMin = in.readString();
        tempMax = in.readString();
        diaSemana = in.readString();
        data = in.readString();
    }

    public static final Parcelable.Creator<PrevisoesFuturas> CREATOR = new Parcelable.Creator<PrevisoesFuturas>() {

        @Override
        public PrevisoesFuturas createFromParcel(Parcel source) {
            return new PrevisoesFuturas(source);
        }

        @Override
        public PrevisoesFuturas[] newArray(int size) {
            return new PrevisoesFuturas[size];
        }
    };
}
