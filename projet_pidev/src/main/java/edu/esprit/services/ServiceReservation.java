package edu.esprit.services;

import edu.esprit.entities.Reservation;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceReservation implements  IService<Reservation> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Reservation reservation) {

        String req = "INSERT INTO `reservation`(`id_reservation`, `id_evenement`,`id_etablissement`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, reservation.getId_reservation());
            ps.setInt(2, reservation.getId_evenement());
            ps.setInt(3, reservation.getId_etablissement());
            ps.executeUpdate();
            System.out.println("Reservation added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reservation reservation) {
        int id = reservation.getId_reservation();
        Reservation existingEReservation = getOneByID(id);
        if (existingEReservation != null) {
            String req = "UPDATE `reservation` SET `id_evenement`=?,`id_etablissement`=? WHERE `id_reservation`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, reservation.getId_evenement());
                ps.setInt(2, reservation.getId_etablissement());
                ps.setInt(3, id);
                ps.executeUpdate();
                System.out.println("Reservation updated !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de mise à jour ");
        }
    }

    @Override
    public void supprimer(int id) {
        Reservation reservation = getOneByID(id);
        if (reservation != null) {
            String req = "DELETE FROM `reservation` WHERE `id_reservation`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("reservation deleted !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de suppression ");
        }
    }

    @Override
    public Set<Reservation> getAll() {
        Set<Reservation> reservations = new HashSet<>();

        String req = "Select * from reservation";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id_reservation");
                int id_evenement = rs.getInt("id_evenement");
                int id_etablissement = rs.getInt("id_etablissement");
                Reservation r = new Reservation(id,id_evenement,id_etablissement);
                reservations.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return reservations;
    }

    @Override
    public Reservation getOneByID(int id_reservation) {
        String req = "SELECT * FROM reservation WHERE id_reservation = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_reservation);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_evenement = rs.getInt("id_evenement");
                int id_etablissement = rs.getInt("id_etablissement");
                return new Reservation(id_reservation,id_evenement,id_etablissement);
            } else {
                System.out.print("Echec! Reservation with ID " + id_reservation + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;


        }   }
}
