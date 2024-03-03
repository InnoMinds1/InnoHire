package edu.esprit.services;

import edu.esprit.entities.Admin;
import edu.esprit.entities.Candidat;
import edu.esprit.entities.Representant;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceUtilisateur implements IService<Utilisateur> {
    static Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Utilisateur utilisateur) {
        if (utilisateur instanceof Admin) {
            Admin a = (Admin) utilisateur;
            String req = "INSERT INTO `admin`( `cin`, `name`, `prenom`, `email`, `mdp`) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, a.getCin());
                ps.setString(2, a.getName());
                ps.setString(3, a.getPrenom());
                ps.setString(4, a.getAdresse());
                String hashed =hashPassword(a.getMdp());
                ps.setString(5, hashed);
                ps.executeUpdate();
                System.out.println("Admin added");


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String req = "INSERT INTO `utilisateur`( `cin`, `name`, `prenom`, `adresse`, `mdp`, `role`) VALUES (?,?,?,?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, utilisateur.getCin());
                ps.setString(2, utilisateur.getName());
                ps.setString(3, utilisateur.getPrenom());
                ps.setString(4, utilisateur.getAdresse());
                String hashed= hashPassword(utilisateur.getMdp());
                ps.setString(5, hashed);
                if (utilisateur instanceof Representant) {
                    ps.setInt(6, Representant.getROLE());
                    ps.executeUpdate();
                    System.out.println("Representant added");
                } else {
                    ps.setInt(6, Candidat.getROLE());
                    ps.executeUpdate();
                    System.out.println("Candidat added");
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    public void modifier(Utilisateur utilisateur) {
        String req = "UPDATE utilisateur SET cin =?, name = ?, prenom = ?, adresse = ?, mdp = ? WHERE id_utilisateur = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, utilisateur.getCin());
            ps.setString(2, utilisateur.getName());
            ps.setString(3, utilisateur.getPrenom());
            ps.setString(4, utilisateur.getAdresse());
            ps.setString(5, utilisateur.getMdp());
            ps.setInt(6, utilisateur.getId_utilisateur());

            ps.executeUpdate();
            System.out.println("utilisateur modifié!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void modifier_admin(Admin admin) {
        String req = "UPDATE admin SET cin =?, name = ?, prenom = ?, email = ?, mdp = ? WHERE id_admin = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, admin.getCin());
            ps.setString(2, admin.getName());
            ps.setString(3, admin.getPrenom());
            ps.setString(4, admin.getAdresse());
            ps.setString(5, admin.getMdp());
            ps.setInt(6, admin.getId_utilisateur());

            ps.executeUpdate();
            System.out.println("admin modifié!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM utilisateur WHERE id_utilisateur = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("l'utilisateur avec l'id  " + id + " a ete supprime!");
            } else {
                System.out.println("il n'y a pas d'utilisateur avec l'id:  " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimer_admin(int id) {
        String req = "DELETE FROM admin WHERE id_admin = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("l'admin avec l'id  " + id + " a ete supprime!");
            } else {
                System.out.println("il n'y a pas d'admin avec l'id:  " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Set<Utilisateur> getAll() {
        Set<Utilisateur> utilisateurs = new HashSet<>();
        String req = "Select * from utilisateur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int id = rs.getInt(1);
                int cin = rs.getInt(2);
                String name = rs.getString("name");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String mdp = rs.getString("mdp");
                int role = rs.getInt(7);
                if (role == 0) {
                    Utilisateur p = new Representant(id, cin, name, prenom, adresse, mdp);
                    utilisateurs.add(p);
                } else {
                    Utilisateur p = new Candidat(id, cin, name, prenom, adresse, mdp);
                    utilisateurs.add(p);
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;
    }

    public Set<Admin> getAll_admin() {
        Set<Admin> admins = new HashSet<>();
        String req = "Select * from admin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int id = rs.getInt(1);
                int cin = rs.getInt(2);
                String name = rs.getString("name");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("email");
                String mdp = rs.getString("mdp");
                Admin p = new Admin(id, cin, name, prenom, adresse, mdp);
                admins.add(p);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }



    public  Utilisateur getOneByID(int id) {

        String req = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Assuming Utilisateur has appropriate constructor
                Utilisateur user = new Utilisateur();
                user.setId_utilisateur(id);
                user.setCin(rs.getInt("cin"));
                user.setName(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdresse(rs.getString("adresse"));
                user.setMdp(rs.getString("mdp"));
                user.setRole(rs.getInt("role"));
                user.setProfileImg(rs.getString("image"));

                // Set other properties as needed
                return user;
            } else {
                System.out.println("pas de utilisateur avec ce id " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Return null if no user found
    }

    public Utilisateur getOneAdminByID(int id) {
        String req = "SELECT * FROM admin WHERE id_admin = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Assuming Utilisateur has appropriate constructor
                Admin user = new Admin();
                user.setCin(rs.getInt("cin"));
                user.setName(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdresse(rs.getString("email"));
                user.setMdp(rs.getString("mdp"));
                // Set other properties as needed
                return user;
            } else {
                System.out.println("pas de admin avec ce id " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Return null if no user found
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erreur lors du hachage du mot de passe : " + e.getMessage());
            return null;
        }
    }
    public Utilisateur getByCin(int cin) {
        String req = "SELECT * FROM utilisateur WHERE cin = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, cin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId_utilisateur(rs.getInt("id_utilisateur"));
                user.setCin(rs.getInt("cin"));
                user.setName(rs.getString("name"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdresse(rs.getString("adresse"));
                user.setMdp(rs.getString("mdp"));
                // Set other properties as needed
                return user;
            } else {
                System.out.println("Aucun utilisateur avec ce CIN : " + cin);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Retourner null si aucun utilisateur n'est trouvé
    }

}