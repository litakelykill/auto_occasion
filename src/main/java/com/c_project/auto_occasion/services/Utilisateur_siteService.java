package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.UtilisateurSiteDAO;
import com.c_project.auto_occasion.model.Utilisateur_site;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Utilisateur_siteService {
    private UtilisateurSiteDAO utilisateurSiteDAO;
    private Connexion con;
   
    
      public Utilisateur_siteService() {
        utilisateurSiteDAO = new UtilisateurSiteDAO();
        con = new Connexion();
    }
    // crud

    public void create(Utilisateur_site newUtilisateur) throws Exception {
        try {
            utilisateurSiteDAO.create(newUtilisateur);
        } catch (SQLException e) {
            throw e;
        }
    }
   
      public String verificationUser(Connection co, String email, String password) throws Exception {
        Connection connex = null;
        String token = null;
        try{
            connex = con.getConnection();
            try {
                token = utilisateurSiteDAO.verificationUser(co, email, password);
                System.out.println("User verified successfully.");
            } catch (Exception e) {
                System.out.println("Diso oooot.");
                throw e;
            }
       
        }catch (SQLException e) {
            System.out.println("Error persist with verification in utilisateur_site");
            throw e;
        } finally {
            if(connex != null) {
                connex.close();
            }
        }
        return token;
     }
     public List<Utilisateur_site> findAllUser() throws Exception {
         Connection connection = null;
         List<Utilisateur_site> users = new ArrayList<>();
         try {
             connection = con.getConnection();
             users = utilisateurSiteDAO.findAllUser();
         } catch (SQLException e) {
             throw e;
         } finally {
             if(connection != null) {
                 connection.close();
             }
         }
         return users;
     }
     public Utilisateur_site findOne(int id_user) throws Exception {
         Connection connection = null;
         Utilisateur_site user = new Utilisateur_site();
         try {
             connection = con.getConnection();
             user = utilisateurSiteDAO.findOne(id_user);
         } catch (SQLException e) {
             throw e;
         } finally {
             if(connection != null) {
                 connection.close();
             }
         }
         return user;
     }
    
     public Utilisateur_site findToken(String token) throws Exception {
        Connection connection = null;
        Utilisateur_site one_fav = new Utilisateur_site();
        try {
            connection = con.getConnection();
            one_fav = utilisateurSiteDAO.findTokenUser(token);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_fav;
    }
    public int getLastCreatedUser() throws Exception {
        Connection connection = null;
        int iduser = 0;
        try {
            connection = con.getConnection();
            iduser = utilisateurSiteDAO.getLastCreatedUser();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return iduser;
    }
    public String getTokenUser(int id_user) throws Exception {
        Connection connection = null;
        String token = null;
        try {
            connection = con.getConnection();
            token = utilisateurSiteDAO.getTokenUser(id_user);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return token;
    }
    public String generateToken(String email, String password) throws Exception {
        String token = null;
        try {
            token = utilisateurSiteDAO.generateToken(email, password);
        } catch (Exception e) {
            throw e;
        }
        return token;
    }
    public void saveToken(String token, int userId) throws Exception {
        Connection connection = null;
        try {
            connection = con.getConnection();
            utilisateurSiteDAO.saveTokenToDatabase(connection, token, userId);
        } catch (SQLException e) {
            throw e;
        }
    }
}
