package edu.esprit.entities;

import java.util.Date;
import java.util.Objects;

public class Messagerie {
    public int id_message;
    public String type,contenu;
    public Date date;
    Utilisateur sender_id,reciver_id;

    public Messagerie() {
    }

    public Messagerie(String type, String contenu, Date date) {
        this.type = type;
        this.contenu = contenu;
        this.date = date;
    }

    public Messagerie(String type, String contenu, Date date, Utilisateur sender_id, Utilisateur reciver_id) {
        this.type = type;
        this.contenu = contenu;
        this.date = date;
        this.sender_id = sender_id;
        this.reciver_id = reciver_id;
    }

    public Messagerie(int id_message, String type, String contenu, Date date, Utilisateur sender_id, Utilisateur reciver_id) {
        this.id_message = id_message;
        this.type = type;
        this.contenu = contenu;
        this.date = date;
        this.sender_id = sender_id;
        this.reciver_id = reciver_id;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getSender_id() {
        return sender_id;
    }

    public void setSender_id(Utilisateur sender_id) {
        this.sender_id = sender_id;
    }

    public Utilisateur getReciver_id() {
        return reciver_id;
    }

    public void setReciver_id(Utilisateur reciver_id) {
        this.reciver_id = reciver_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messagerie that = (Messagerie) o;
        return id_message == that.id_message && Objects.equals(sender_id, that.sender_id) && Objects.equals(reciver_id, that.reciver_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_message, sender_id, reciver_id);
    }

    @Override
    public String toString() {
        return "Messagerie{" +
                "id_message=" + id_message +
                ", type='" + type + '\'' +
                ", contenu='" + contenu + '\'' +
                ", date=" + date +
                ", sender_id=" + sender_id +
                ", reciver_id=" + reciver_id +
                '}';
    }
}
