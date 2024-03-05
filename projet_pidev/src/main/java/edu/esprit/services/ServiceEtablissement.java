package edu.esprit.services;

import edu.esprit.entities.Etablissement;
<<<<<<< HEAD
import edu.esprit.entities.Utilisateur;
=======
import edu.esprit.entities.Quiz;
import edu.esprit.entities.Utilisateur;
import edu.esprit.entities.Wallet;
>>>>>>> gestion-etablissement
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceEtablissement implements IService<Etablissement> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Etablissement etablissement) throws SQLException {
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
        String req = "INSERT INTO `etablissement`(`nom`, `lieu`, `code_etablissement`, `type_etablissement`, `image`,`id_utilisateur`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, etablissement.getNom());
            ps.setString(2, etablissement.getLieu());
<<<<<<< HEAD
            ps.setInt(3, etablissement.getCode_etablissement());
            ps.setString(4, etablissement.getType_etablissement());
            ps.setInt(5, etablissement.getUser().getId_utilisateur());
=======
            ps.setInt(3, etablissement.getCodeEtablissement());
            ps.setString(4, etablissement.getTypeEtablissement());
            ps.setString(5, etablissement.getImage());
            ps.setInt(6, etablissement.getUser().getId_utilisateur());

>>>>>>> gestion-etablissement


            ps.executeUpdate();
            System.out.println("Etabliessement added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Etablissement etablissement) throws SQLException {
<<<<<<< HEAD
        int id = etablissement.getId_etablissement();
=======
        int id = etablissement.getIdEtablissement();
>>>>>>> gestion-etablissement
        Etablissement existingEtablissement = getOneByID(id);
        if (existingEtablissement != null) {
            String req = "UPDATE `etablissement` SET `nom`=?, `lieu`=?, `code_etablissement`=?, `type_etablissement`=?, `image`=?, `id_utilisateur`=? WHERE `id_etablissement`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, etablissement.getNom());
                ps.setString(2, etablissement.getLieu());
<<<<<<< HEAD
                ps.setInt(3, etablissement.getCode_etablissement());
                ps.setString(4, etablissement.getType_etablissement());
                ps.setInt(5, etablissement.getUser().getId_utilisateur());
                ps.setInt(6, id);
=======
                ps.setInt(3, etablissement.getCodeEtablissement());
                ps.setString(4, etablissement.getTypeEtablissement());
                ps.setString(5, etablissement.getImage());
                ps.setInt(6, etablissement.getUser().getId_utilisateur());

                ps.setInt(7, id);
>>>>>>> gestion-etablissement

                ps.executeUpdate();
                System.out.println("Etablissement updated !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Introuvable : Échec de mise à jour");
        }
    }

<<<<<<< HEAD
    @Override
    public void supprimer(int id_etablissement) throws SQLException {
=======
>>>>>>> gestion-etablissement

    @Override
    public void supprimer(int idEtablissement) throws SQLException {

        Etablissement etablissement = getOneByID(idEtablissement);
        if (etablissement != null) {
            String req = "DELETE FROM `etablissement` WHERE `id_etablissement`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, idEtablissement);
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
    public Set<Etablissement> getAll() throws SQLException {
        Set<Etablissement> etablissements = new HashSet<>();

        String req = "Select * from etablissement";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int idEtablissement = rs.getInt("id_etablissement"); //wala t7ot num colomn kima eli ta7etha
                String nom = rs.getString(2); //wala t7ot esm colomn kima eli fou9ha
                String lieu = rs.getString("lieu");
<<<<<<< HEAD
                int code_etablissement = rs.getInt("code_etablissement");
                String type_etablissement = rs.getString("type_etablissement");
                int id_utilisateur = rs.getInt("id_utilisateur");

                ServiceUtilisateur su = new ServiceUtilisateur();
                Utilisateur user = su.getOneByID(id_utilisateur);

                Etablissement e = new Etablissement(id_etablissement,nom,lieu,code_etablissement,type_etablissement,user);
=======
                int codeEtablissement = rs.getInt("code_etablissement");
                String typeEtablissement = rs.getString("type_etablissement");
                String image = rs.getString("image");
                int idUtilisateur = rs.getInt("id_utilisateur");


                ServiceUtilisateur su = new ServiceUtilisateur();
                Utilisateur user = su.getOneByID(idUtilisateur);

                Etablissement e = new Etablissement(idEtablissement,nom,lieu,codeEtablissement,typeEtablissement,image,null,user);
                etablissements.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return etablissements;
    }
    public Set<Etablissement> getByUserId(int idUtilisateur) throws SQLException {
        Set<Etablissement> etablissements = new HashSet<>();

        String req = "SELECT * FROM etablissement WHERE id_utilisateur = ?";
        try (PreparedStatement pst = cnx.prepareStatement(req)) {
            pst.setInt(1, idUtilisateur);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int idEtablissement = rs.getInt("id_etablissement");
                String nom = rs.getString("nom");
                String lieu = rs.getString("lieu");
                int codeEtablissement = rs.getInt("code_etablissement");
                String typeEtablissement = rs.getString("type_etablissement");
                String image = rs.getString("image");

                ServiceUtilisateur su = new ServiceUtilisateur();
                 Utilisateur user = su.getOneByID(idUtilisateur);

                // Vous pouvez créer l'objet Etablissement sans l'utilisateur
                Etablissement e = new Etablissement(idEtablissement, nom, lieu, codeEtablissement, typeEtablissement, image, null, user);
>>>>>>> gestion-etablissement
                etablissements.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return etablissements;
    }


<<<<<<< HEAD
    @Override
    public Etablissement getOneByID(int id_etablissement) throws SQLException {
=======

    @Override
    public Etablissement getOneByID(int idEtablissement) throws SQLException {
>>>>>>> gestion-etablissement
        String req = "SELECT * FROM etablissement WHERE id_etablissement = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idEtablissement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2); //wala t7ot esm colomn kima eli fou9ha
                String lieu = rs.getString("lieu");
<<<<<<< HEAD
                int code_etablissement = rs.getInt("code_etablissement");
                String type_etablissement = rs.getString("type_etablissement");
                int id_utilisateur = rs.getInt("id_utilisateur");

                ServiceUtilisateur su = new ServiceUtilisateur();
                Utilisateur user = su.getOneByID(id_utilisateur);

                return new Etablissement(id_etablissement, nom, lieu,code_etablissement,type_etablissement,user);
=======
                int codeEtablissement = rs.getInt("code_etablissement");
                String typeEtablissement = rs.getString("type_etablissement");
                String image = rs.getString("image");
                int idUtilisateur = rs.getInt("id_utilisateur");


                ServiceUtilisateur su = new ServiceUtilisateur();
                Utilisateur user = su.getOneByID(idUtilisateur);

                return new Etablissement(idEtablissement, nom, lieu,codeEtablissement,typeEtablissement,image,null,user);
>>>>>>> gestion-etablissement
            } else {
                System.out.print("Echec! Etablissement with ID " + idEtablissement + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean existe(int codeEtablissement) throws SQLException {
        String req = "SELECT * FROM etablissement WHERE code_etablissement = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, codeEtablissement);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retourne true si un établissement avec le code existe déjà
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
<<<<<<< HEAD
=======
    public boolean existeParNom(String nom) throws SQLException {
        String req = "SELECT * FROM etablissement WHERE nom = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retourne true si un établissement avec le code existe déjà
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

>>>>>>> gestion-etablissement

    public Etablissement getOneByCode(int code) {
        String req = "SELECT * FROM etablissement WHERE code_etablissement = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Assuming Utilisateur has appropriate constructor
                Etablissement etablissement = new Etablissement();
<<<<<<< HEAD
                etablissement.setId_etablissement(rs.getInt("id_etablissement"));
                etablissement.setCode_etablissement(rs.getInt("code_etablissement"));
                etablissement.setNom(rs.getString("nom"));
                etablissement.setLieu(rs.getString("lieu"));
                etablissement.setType_etablissement(rs.getString("type_etablissement"));
=======
                etablissement.setIdEtablissement(rs.getInt("id_etablissement"));
                etablissement.setCodeEtablissement(rs.getInt("code_etablissement"));
                etablissement.setNom(rs.getString("nom"));
                etablissement.setLieu(rs.getString("lieu"));
                etablissement.setTypeEtablissement(rs.getString("type_etablissement"));
                etablissement.setImage(rs.getString("image"));

>>>>>>> gestion-etablissement
                int id_user=rs.getInt("id_utilisateur");


                ServiceUtilisateur su=new ServiceUtilisateur();
                Utilisateur user1=su.getOneByID(id_user);
                etablissement.setUser(user1);
                // Set other properties as needed
                return etablissement;
            } else {
                System.out.println("pas de utilisateur avec ce code " + code);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Return null if no user found
    }

    int getIdEtablissement(int codeEtablissement) throws SQLException {
        String reqIdEtablissement = "SELECT id_etablissement FROM etablissement WHERE code_etablissement = ?";
        try {
            PreparedStatement psIdEtablissement = cnx.prepareStatement(reqIdEtablissement);
            psIdEtablissement.setInt(1, codeEtablissement);
            ResultSet rsIdEtablissement = psIdEtablissement.executeQuery();

            // Vérifier si un établissement avec le code existe
            if (rsIdEtablissement.next()) {
                return rsIdEtablissement.getInt("id_etablissement");
            } else {
                // Aucun établissement trouvé
                return -1; // Retourne -1 pour indiquer l'absence d'établissement
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1; // Retourne -1 en cas d'erreur
        }
    }


<<<<<<< HEAD
=======




    // Récupérer les quiz attribués à un établissement
    public List<Quiz> getQuizzesPourEtablissement(Etablissement etablissement) throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT q.* FROM quiz q " +
                "JOIN etablissement_quiz eq ON q.id_quiz = eq.id_quiz " +
                "WHERE eq.id_etablissement = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, etablissement.getIdEtablissement());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Quiz quiz = new Quiz();
                    quiz.setId_quiz(resultSet.getInt("id_quiz"));
                    quiz.setCode_quiz(resultSet.getInt("code_quiz"));
                    quiz.setNom_quiz(resultSet.getString("nom_quiz"));
                    quiz.setDescription(resultSet.getString("description"));
                    quiz.setPrix_quiz(resultSet.getInt("prix_quiz"));
                    // quiz.setImage_quiz(resultSet.getString("id_etablissement"));
                    quizzes.add(quiz);
                }
            }
        }
        return quizzes;
    }




    public Wallet getWalletByEtablissement(Etablissement etablissement) {
        int idEtablissement = etablissement.getIdEtablissement();
        String req = "SELECT * FROM wallet WHERE id_etablissement = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idEtablissement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idWallet = rs.getInt("id_wallet");
                int balance = rs.getInt("balance");

                // Utilisation de Timestamp pour récupérer les dates de type datetime
                Timestamp timestamp = rs.getTimestamp("date_c");
                LocalDateTime dateCreation = timestamp.toLocalDateTime();

                int status = rs.getInt("status");

                return new Wallet(idWallet, balance, dateCreation, status, etablissement);
            } else {
                System.out.print("Echec! Aucun portefeuille trouvé pour l'établissement avec ID " + idEtablissement);
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


>>>>>>> gestion-etablissement
}
