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
 * Created by jose.oliveira on 11/11/2016.
 */

public class Usuario implements Parcelable{

    private String idUser;
    private String login;
    private String nome;
    private String email;
    private String empresa;
    private String conexao;

    public Usuario() {
    }


    public Usuario(String idUser, String login, String nome, String email, String empresa, String conexao) {
        this.idUser = idUser;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.empresa = empresa;
        this.conexao = conexao;
    }

    protected Usuario(Parcel in) {
        idUser = in.readString();
        login = in.readString();
        nome = in.readString();
        email = in.readString();
        empresa = in.readString();
        conexao = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getConexao() {
        return conexao;
    }

    public void setConexao(String conexao) {
        this.conexao = conexao;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUser);
        dest.writeString(login);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(empresa);
        dest.writeString(conexao);
    }
}
