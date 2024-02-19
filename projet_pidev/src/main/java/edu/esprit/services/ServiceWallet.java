package edu.esprit.services;


import edu.esprit.entities.Wallet;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceWallet implements IService<Wallet> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Wallet wallet) {
   /*
String req = "INSERT INTO `wallet`(`nom`, `prenom`) VALUES ('"+personne.getNom()+"','"+personne.getPrenom()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        --> cest pas pratique car si on a plusieur attributs on doit faire concatenation(+) pour chacun
*/
        String req = "INSERT INTO `wallet`(`balance`, `id_etablissement`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, wallet.getBalance());
            ps.setInt(2, wallet.getId_etablissement());

            ps.executeUpdate();
            System.out.println("Wallet added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Wallet wallet) {
        int id = wallet.getId_wallet();
        Wallet existingWallet = getOneByID(id);
        if (existingWallet != null) {
            String req = "UPDATE `wallet` SET `balance`=?, `id_etablissement`=? WHERE `id_wallet`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, wallet.getBalance());
                ps.setInt(2, wallet.getId_etablissement());

                ps.setInt(3, id);

                ps.executeUpdate();
                System.out.println("Wallet updated !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de mise à jour ");
        }


    }

    @Override
    public void supprimer(int id_wallet) {

        Wallet wallet = getOneByID(id_wallet);
        if (wallet != null) {
            String req = "DELETE FROM `wallet` WHERE `id_wallet`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id_wallet);
                ps.executeUpdate();
                System.out.println("Wallet deleted !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de suppression ");
        }

    }

    @Override
    public Set<Wallet> getAll() {
        Set<Wallet> wallets = new HashSet<>();

        String req = "Select * from wallet";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id_wallet = rs.getInt("id_wallet"); //wala t7ot num colomn kima eli ta7etha
                int balance = rs.getInt(2); //wala t7ot esm colomn kima eli fou9ha

                int id_etablissement = rs.getInt("id_etablissement");

                Wallet e = new Wallet(id_wallet,balance,id_etablissement);
                wallets.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return wallets;
    }

    @Override
    public Wallet getOneByID(int id_wallet) {
        String req = "SELECT * FROM wallet WHERE id_wallet = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_wallet);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int balance = rs.getInt(2); //wala t7ot esm colomn kima eli fou9ha

                int id_etablissement = rs.getInt("id_etablissement");
                return new Wallet(id_wallet, balance, id_etablissement);
            } else {
                System.out.print("Echec! Wallet with ID " + id_wallet + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
