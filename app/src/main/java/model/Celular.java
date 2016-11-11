
package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jose.oliveira on 11/11/2016.
 *
 */


public class Celular implements Parcelable{

    private String imei;
    private String imsi;

    public Celular() {
    }

    public Celular(String imei, String imsi) {
        this.imei = imei;
        this.imsi = imsi;
    }

    protected Celular(Parcel in) {
        imei = in.readString();
        imsi = in.readString();
    }

    public static final Creator<Celular> CREATOR = new Creator<Celular>() {
        @Override
        public Celular createFromParcel(Parcel in) {
            return new Celular(in);
        }

        @Override
        public Celular[] newArray(int size) {
            return new Celular[size];
        }
    };

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imei);
        dest.writeString(imsi);
    }
}
