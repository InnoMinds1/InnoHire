package edu.esprit.services;

import edu.esprit.entities.Etablissement;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceEtablissement implements IService<Etablissement> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Etablissement etablissement) {
   /*
String req = "INSERT INTO `etablissement`(`nom`, `prenom`) VALUES ('"+personne.getNom()+"','"+personne.getPrenom()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        --> cest pas pratique car si on a plusieur attributs on doit faire concatenation(+) pour chacun
*/
        String req = "INSERT INTO `etablissement`(`nom`, `lieu`, `code_etablissement`, `type_etablissement`, `id_utilisateur`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, etablissement.getNom());
            ps.setString(2, etablissement.getLieu());
            ps.setInt(3, etablissement.getCode_etablissement());
            ps.setString(4, etablissement.getType_etablissement());
            if (etablissement.isIdUtilisateurNull()) {
                // Si id_utilisateur est null, définir la valeur dans la base de données à NULL
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                // Sinon, définir la valeur normalement
                ps.setInt(5, etablissement.getId_utilisateur());
            }

            ps.executeUpdate();
            System.out.println("Etabliessement added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Etablissement etablissement) {
        int id = etablissement.getId_etablissement();
        Etablissement existingEtablissement = getOneByID(id);
        if (existingEtablissement != null) {
            String req = "UPDATE `etablissement` SET `nom`=?, `lieu`=?, `code_etablissement`=?, `type_etablissement`=?, `id_utilisateur`=? WHERE `id_etablissement`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, etablissement.getNom());
                ps.setString(2, etablissement.getLieu());
                ps.setInt(3, etablissement.getCode_etablissement());
                ps.setString(4, etablissement.getType_etablissement());
                ps.setInt(5, etablissement.getId_utilisateur());
                ps.setInt(6, id);

                ps.executeUpdate();
                System.out.println("Etablissement updated !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de mise à jour ");
        }


    }

    @Override
    public void supprimer(int id_etablissement) {

        Etablissement etablissement = getOneByID(id_etablissement);
        if (etablissement != null) {
            String req = "DELETE FROM `etablissement` WHERE `id_etablissement`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id_etablissement);
                ps.executeUpdate();
                System.out.println("Etablissement deleted !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de suppression ");
        }

    }

    @Override
    public Set<Etablissement> getAll() {
        Set<Etablissement> etablissements = new HashSet<>();

        String req = "Select * from etablissement";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id_etablissement = rs.getInt("id_etablissement"); //wala t7ot num colomn kima eli ta7etha
                String nom = rs.getString(2); //wala t7ot esm colomn kima eli fou9ha
                String lieu = rs.getString("lieu");
                int code_etablissement = rs.getInt("code_etablissement");
                String type_etablissement = rs.getString("type_etablissement");
                int id_utilisateur = rs.getInt("id_utilisateur");
                Etablissement e = new Etablissement(id_etablissement,nom,lieu,code_etablissement,type_etablissement,id_utilisateur);
                etablissements.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return etablissements;
    }

    @Override
    public Etablissement getOneByID(int id_etablissement) {
        String req = "SELECT * FROM etablissement WHERE id_etablissement = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_etablissement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2); //wala t7ot esm colomn kima eli fou9ha
                String lieu = rs.getString("lieu");
                int code_etablissement = rs.getInt("code_etablissement");
                String type_etablissement = rs.getString("type_etablissement");
                int id_utilisateur = rs.getInt("id_utilisateur");
                return new Etablissement(id_etablissement, nom, lieu,code_etablissement,type_etablissement,id_utilisateur);
            } else {
                System.out.print("Echec! Etablissement with ID " + id_etablissement + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}