package edu.esprit.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Commentaire {
 private int id_commentaire ;
 private int id_publication ;
 private int id_utilisateur ;
 private String description_co ;
 private LocalDate date_co ;

 public Commentaire(int id_commentaire, int id_publication, int id_utilisateur, String description_co, LocalDate date_co) {
  this.id_commentaire = id_commentaire;
  this.id_publication = id_publication;
  this.id_utilisateur = id_utilisateur;
  this.description_co = description_co;
  this.date_co = date_co;
 }

 public Commentaire(int id_publication, int id_utilisateur, String description_co, LocalDate date_co) {
  this.id_publication = id_publication;
  this.id_utilisateur = id_utilisateur;
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

 public int getId_publication() {
  return id_publication;
 }

 public void setId_publication(int id_publication) {
  this.id_publication = id_publication;
 }

 public int getId_utilisateur() {
  return id_utilisateur;
 }

 public void setId_utilisateur(int id_utilisateur) {
  this.id_utilisateur = id_utilisateur;
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
          "id_publication=" + id_publication +
          ", id_utilisateur=" + id_utilisateur +
          ", description_co='" + description_co + '\'' +
          ", date_co=" + date_co +
          '}';
 }
}
