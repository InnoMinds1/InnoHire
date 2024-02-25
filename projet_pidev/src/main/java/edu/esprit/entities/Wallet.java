package edu.esprit.entities;

import java.util.Objects;

public class Wallet {
    private int id_wallet;
    private int balance;
    //regledenommage
    //date
    //inactivity
    private Etablissement etablissement;

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    public Wallet() {

    }

    public Wallet(int id_wallet, int balance, Etablissement etablissement) {
        this.id_wallet = id_wallet;
        this.balance = balance;
        this.etablissement = etablissement;
    }

    public Wallet(int balance, Etablissement etablissement) {
        this.balance = balance;
        this.etablissement = etablissement;
    }



    public int getId_wallet() {
        return id_wallet;
    }

    public void setId_wallet(int id_wallet) {
        this.id_wallet = id_wallet;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id_wallet == wallet.id_wallet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_wallet);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id_wallet=" + id_wallet +
                ", balance=" + balance +
                ", code_etablissement=" + etablissement.getCode_etablissement() +
                '}';
    }


}
