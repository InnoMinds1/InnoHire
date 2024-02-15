package edu.esprit.entities;

import java.util.Objects;

public class Reservation {
    private int id_reservation ;
    private int id_evenement;
    private int id_etablissement;

    public Reservation(int id_reservation, int id_evenement, int id_etablissement) {
        this.id_reservation = id_reservation;
        this.id_evenement = id_evenement;
        this.id_etablissement = id_etablissement;
    }

    public Reservation() {
    }

    public Reservation(int id_evenement, int id_etablissement) {
        this.id_evenement = id_evenement;
        this.id_etablissement = id_etablissement;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
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
        Reservation that = (Reservation) o;
        return id_reservation == that.id_reservation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reservation);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", id_evenement=" + id_evenement +
                ", id_etablissement=" + id_etablissement +
                '}';
    }
}
