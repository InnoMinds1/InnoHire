package edu.esprit.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Commentaire {
 private int id_commentaire ;
 private Publication publication ;
 private Utilisateur utilisateur ;
 private String description_co ;
 private LocalDate date_co ;

 public Commentaire(int id_commentaire, Publication publication,Utilisateur utilisateur, String description_co, LocalDate date_co) {
  this.id_commentaire = id_commentaire;
  this.publication = publication;
  this.utilisateur = utilisateur;
  this.description_co = description_co;
  this.date_co = date_co;
 }

 public Commentaire(Publication publication, Utilisateur utilisateur, String description_co, LocalDate date_co) {
  this.publication = publication;
  this.utilisateur = utilisateur;
  this.description_co = description_co;
  this.date_co = date_co;
 }

 public Commentaire() {
 }

 public int getId_commentaire() {
  return id_commentaire;
 }

 public void setId_commentaire(int id_commentaire) {
  this.id_commentaire = id_commentaire;
 }

 public Publication getPublication() {
  return publication;
 }

 public void setPublication(Publication publication) {
  this.publication = publication;
 }

 public Utilisateur getUtilisateur() {
  return utilisateur;
 }

 public void setUtilisateur(Utilisateur utilisateur) {
  this.utilisateur = utilisateur;
 }

 public String getDescription_co() {
  return description_co;
 }

 public void setDescription_co(String description_co) {
  this.description_co = description_co;
 }

 public LocalDate getDate_co() {
  return date_co;
 }

 public void setDate_co(LocalDate date_co) {
  this.date_co = date_co;
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Commentaire that = (Commentaire) o;
  return id_commentaire == that.id_commentaire;
 }

 @Override
 public int hashCode() {
  return Objects.hash(id_commentaire);
 }

 @Override
 public String
 toString() {
  return "commentaire{" +
          "id_publication=" + publication +
          ", id_utilisateur=" + utilisateur +
          ", description_co='" + description_co + '\'' +
          ", date_co=" + date_co +
          '}';
 }
}
