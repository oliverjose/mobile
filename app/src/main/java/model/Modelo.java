package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jose.oliveira on 10/11/2016.
 */

public class Modelo implements Parcelable {

    private long id;
    private String cpo01;
    private String cpo02;
    private String cpo03;
    private String cpo04;
    private String cpo05;
    private String cpo06;
    private String cpo07;
    private String cpo08;
    private String cpo09;
    private String cpo10;
    private String cpo11;
    private String cpo12;
    private String cpo13;
    private String cpo14;
    private String cpo15;
    private String cpo16;
    private String cpo17;
    private String cpo18;
    private String cpo19;
    private String cpo20;
    private String cpo21;
    private String cpo22;
    private String cpo23;
    private String cpo24;
    private String cpo25;
    private String cpo26;
    private String cpo27;
    private String cpo28;

    public Modelo() {
    }

    public Modelo(String cpo01, String cpo02) {
        this.cpo01 = cpo01;
        this.cpo02 = cpo02;
    }

    protected Modelo(Parcel in) {
        id = in.readLong();
        cpo01 = in.readString();
        cpo02 = in.readString();
        cpo03 = in.readString();
        cpo04 = in.readString();
        cpo05 = in.readString();
        cpo06 = in.readString();
        cpo07 = in.readString();
        cpo08 = in.readString();
        cpo09 = in.readString();
        cpo10 = in.readString();
        cpo11 = in.readString();
        cpo12 = in.readString();
        cpo13 = in.readString();
        cpo14 = in.readString();
        cpo15 = in.readString();
        cpo16 = in.readString();
        cpo17 = in.readString();
        cpo18 = in.readString();
        cpo19 = in.readString();
        cpo20 = in.readString();
        cpo21 = in.readString();
        cpo22 = in.readString();
        cpo23 = in.readString();
        cpo24 = in.readString();
        cpo25 = in.readString();
        cpo26 = in.readString();
        cpo27 = in.readString();
        cpo28 = in.readString();
    }

    public static final Creator<Modelo> CREATOR = new Creator<Modelo>() {
        @Override
        public Modelo createFromParcel(Parcel in) {
            return new Modelo(in);
        }

        @Override
        public Modelo[] newArray(int size) {
            return new Modelo[size];
        }
    };

    public String getCpo01() {
        return cpo01;
    }

    public void setCpo01(String cpo01) {
        this.cpo01 = cpo01;
    }

    public String getCpo02() {
        return cpo02;
    }

    public void setCpo02(String cpo02) {
        this.cpo02 = cpo02;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(cpo01);
        dest.writeString(cpo02);
        dest.writeString(cpo03);
        dest.writeString(cpo04);
        dest.writeString(cpo05);
        dest.writeString(cpo06);
        dest.writeString(cpo07);
        dest.writeString(cpo08);
        dest.writeString(cpo09);
        dest.writeString(cpo10);
        dest.writeString(cpo11);
        dest.writeString(cpo12);
        dest.writeString(cpo13);
        dest.writeString(cpo14);
        dest.writeString(cpo15);
        dest.writeString(cpo16);
        dest.writeString(cpo17);
        dest.writeString(cpo18);
        dest.writeString(cpo19);
        dest.writeString(cpo20);
        dest.writeString(cpo21);
        dest.writeString(cpo22);
        dest.writeString(cpo23);
        dest.writeString(cpo24);
        dest.writeString(cpo25);
        dest.writeString(cpo26);
        dest.writeString(cpo27);
        dest.writeString(cpo28);
    }
}
