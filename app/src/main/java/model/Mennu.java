/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 30/10/2016.
 */
public class Mennu implements Parcelable{

    private long id;
    private String cpo01;
    private String cpo02;
    private String cpo03;
    private String cpo04;
    private String cpo05;
    private String cpo06;

    public Mennu() {
    }

    public Mennu(String cpo01, String cpo02, String cpo03, String cpo04, String cpo05, String cpo06) {
        this.cpo01 = cpo01;
        this.cpo02 = cpo02;
        this.cpo03 = cpo03;
        this.cpo04 = cpo04;
        this.cpo05 = cpo05;
        this.cpo06 = cpo06;
    }

    public Mennu(long id, String cpo01, String cpo02, String cpo03, String cpo04, String cpo05, String cpo06) {
        this.id = id;
        this.cpo01 = cpo01;
        this.cpo02 = cpo02;
        this.cpo03 = cpo03;
        this.cpo04 = cpo04;
        this.cpo05 = cpo05;
        this.cpo06 = cpo06;
    }

    protected Mennu(Parcel in) {
        id = in.readLong();
        cpo01 = in.readString();
        cpo02 = in.readString();
        cpo03 = in.readString();
        cpo04 = in.readString();
        cpo05 = in.readString();
        cpo06 = in.readString();
    }

    public static final Creator<Mennu> CREATOR = new Creator<Mennu>() {
        @Override
        public Mennu createFromParcel(Parcel in) {
            return new Mennu(in);
        }

        @Override
        public Mennu[] newArray(int size) {
            return new Mennu[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getCpo03() {
        return cpo03;
    }

    public void setCpo03(String cpo03) {
        this.cpo03 = cpo03;
    }

    public String getCpo04() {
        return cpo04;
    }

    public void setCpo04(String cpo04) {
        this.cpo04 = cpo04;
    }

    public String getCpo05() {
        return cpo05;
    }

    public void setCpo05(String cpo05) {
        this.cpo05 = cpo05;
    }

    public String getCpo06() {
        return cpo06;
    }

    public void setCpo06(String cpo06) {
        this.cpo06 = cpo06;
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
    }
}
