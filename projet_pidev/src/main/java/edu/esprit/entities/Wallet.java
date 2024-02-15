package edu.esprit.entities;

import java.util.Objects;

public class Wallet {
    private int id_wallet;
    private int balance;
    private int id_etablissement;

    public Wallet(int id_wallet, int balance, int id_etablissement) {
        this.id_wallet = id_wallet;
        this.balance = balance;
        this.id_etablissement = id_etablissement;
    }

    public Wallet(int balance, int id_etablissement) {
        this.balance = balance;
        this.id_etablissement = id_etablissement;
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

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
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
                ", id_etablissement=" + id_etablissement +
                '}';
    }
}
