package edu.esprit.entities;

import java.time.LocalDateTime;

public class CurrentWallet {

    private static int idWallet=20;
    private static int balance;
    private static LocalDateTime dateCreation;
    private static int status;
    private static Etablissement etablissement;

    public static int getIdWallet() {
        return idWallet;
    }

    public static void setIdWallet(int idWallet) {
        CurrentWallet.idWallet = idWallet;
    }

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) {
        CurrentWallet.balance = balance;
    }

    public static LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public static void setDateCreation(LocalDateTime dateCreation) {
        CurrentWallet.dateCreation = dateCreation;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        CurrentWallet.status = status;
    }

    public static Etablissement getEtablissement() {
        return etablissement;
    }

    public static void setEtablissement(Etablissement etablissement) {
        CurrentWallet.etablissement = etablissement;
    }
}
