package edu.esprit.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Wallet {
    private int idWallet;
    private int balance;


    private LocalDate dateCreation ;
   private int status;
    private Etablissement etablissement;


    public Wallet() {

    }

    public Wallet(int id_wallet, int balance,LocalDate dateCreation,int status ,Etablissement etablissement) {
        this.idWallet = id_wallet;
        this.balance = balance;
        this.dateCreation = dateCreation;
        this.status = status;
        this.etablissement = etablissement;
    }

    public Wallet(int balance,LocalDate dateCreation,int status ,Etablissement etablissement) {
        this.balance = balance;
        this.dateCreation = dateCreation;
        this.status = status;
        this.etablissement = etablissement;
    }



    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return idWallet == wallet.idWallet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWallet);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id_wallet=" + idWallet +
                ", balance=" + balance +
                ", date=" + dateCreation +
                ", status=" + status +


                ", code_etablissement=" + etablissement.getCodeEtablissement() +
                '}';
    }


}
