package com.c_project.auto_occasion.dao;

import java.util.ArrayList;
import java.util.List;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Categorie;
import com.c_project.auto_occasion.model.Detail_voiture;
import com.c_project.auto_occasion.model.Marque;
import com.c_project.auto_occasion.model.Voiture;
import com.c_project.auto_occasion.services.CategorieService;
import com.c_project.auto_occasion.services.Detail_voitureService;
import com.c_project.auto_occasion.services.MarqueService;

import java.sql.*;
public class VoitureDAO {

    public VoitureDAO() {
    }
       // crud  //
    // get all voiture
    /*
    public List<Voiture> findAll(Connection con) throws Exception {
        Statement stmt = null;
        List<Voiture> voitures = new ArrayList<>();
        MarqueService m_service = new MarqueService();
        CategorieService c_service = new CategorieService();
        Detail_voitureService dv_service = new Detail_voitureService();
        try{
            String query = "SELECT * FROM voiture";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Affichage de tous les voitures...");
            if(!rs.isBeforeFirst()){
                return null;
            } else {
                while(rs.next()) {
                    int idcar = rs.getInt("idcar");
                    String matricule = rs.getString("matricule");
                    String nomvoiture=rs.getString("nom_voiture");
                    int idmarque = rs.getInt("idmarque");
                    int idcategorie = rs.getInt("idcategorie");
                    int iddetail = rs.getInt("iddetail");

                    // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs
                    Marque marque = m_service.findOne(idmarque);
                    Categorie categorie = c_service.findOne(idcategorie);
                    Detail_voiture detail_car = dv_service.getOneDetail(iddetail);
                    voitures.add(new Voiture(idcar, matricule, nomvoiture, marque, categorie, detail_car));
                }
                return voitures;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting all the cars...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
     */
       public List<Voiture> findAll(Connection con) throws Exception {
           PreparedStatement pstmt = null;
           List<Voiture> voitures = new ArrayList<>();
           MarqueService m_service = new MarqueService();
           CategorieService c_service = new CategorieService();
           Detail_voitureService dv_service = new Detail_voitureService();
           try {
               String query = "SELECT * FROM voiture";
               pstmt = con.prepareStatement(query);
               ResultSet rs = pstmt.executeQuery();
               System.out.println("Affichage de tous les voitures...");
               if (!rs.isBeforeFirst()) {
                   return null;
               } else {
                   while (rs.next()) {
                       int idcar = rs.getInt("idcar");
                       String matricule = rs.getString("matricule");
                       String nomvoiture = rs.getString("nom_voiture");
                       int idmarque = rs.getInt("idmarque");
                       int idcategorie = rs.getInt("idcategorie");
                       int iddetail = rs.getInt("iddetail");

                       // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs
                       Marque marque = m_service.findOne(idmarque);
                       Categorie categorie = c_service.findOne(idcategorie);
                       Detail_voiture detail_car = dv_service.getOneDetail(iddetail);
                       voitures.add(new Voiture(idcar, matricule, nomvoiture, marque, categorie, detail_car));
                   }
                   return voitures;
               }
           } catch (SQLException e) {
               System.out.println("Error with getting all the cars...");
               throw e;
           } finally {
               if (pstmt != null) {
                   pstmt.close();
               }
           }
       }

    public List<Voiture> findAll() throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        List<Voiture> voitures = new ArrayList<>();
        try{
            con = c.getConnection();
            voitures = findAll(con);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return voitures;
    }
    // get one favorite
    public Voiture findOne(Connection con, int idcar) throws Exception {
        Statement stmt = null;
        Voiture one_voiture = new Voiture();
        MarqueService m_service = new MarqueService();
        CategorieService c_service = new CategorieService();
        Detail_voitureService dv_service = new Detail_voitureService();
        try {
            String query = "SELECT * FROM Voiture WHERE idcar="+idcar;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Afficher un Voiture avec idfavoris="+idcar);
            if(!rs.isBeforeFirst()) {
                return null;
            } else {
                while(rs.next()) {
                    String matricule = rs.getString("matricule");
                    String nomvoiture=rs.getString("nom_voiture");
                    int idmarque = rs.getInt("idmarque");
                    int idcategorie = rs.getInt("idcategorie");
                    int iddetail = rs.getInt("iddetail");

                    // Assuming you have methods to fetch Marque, Categorie, and Detail_voiture objects using their IDs

                    Marque marque = m_service.findOne(idmarque);
                    Categorie categorie = c_service.findOne(idcategorie);
                    Detail_voiture detail_car = dv_service.getOneDetail(iddetail);

                    one_voiture= new Voiture(idcar, matricule, nomvoiture, marque, categorie, detail_car);
                }
                return one_voiture;
            }
        } catch (SQLException e) {
            System.out.println("Error with getting one voiture...");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public Voiture findOne(int id_car) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Voiture one_voiture = new Voiture();
        try{
            con = c.getConnection();
            one_voiture = findOne(con, id_car);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return one_voiture;
    }

    // crud

    public void create(Connection con, Voiture voiture) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO voiture(nom_voiture,matricule,idMarque,idCategorie,idDetail) VALUES(?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, voiture.getNom_voiture());
            pstmt.setString(2, voiture.getMatricule());
            pstmt.setInt(3, voiture.getMarque_voiture().getIdMarque()); // Assuming Marque has a getId() method
            pstmt.setInt(4, voiture.getCategorie().getIdCategorie()); // Assuming Categorie has a getId() method
            pstmt.setInt(5, voiture.getDetail().getIdDetail()); // Assuming Detail_voiture has a getId() method

            System.out.println("Saving " + voiture.getNom_voiture() + " in the table voiture");
            System.out.println(query);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while saving " + voiture.getNom_voiture()+ " in voiture");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }


    public void create(Voiture voiture) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, voiture);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in voiture");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    public void update(Connection con, Voiture voiture, int id_car) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "UPDATE voiture SET nom_voiture=?,matricule=?,idMarque=?, idCategorie=?, idDetail=?  WHERE idcar=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, voiture.getNom_voiture());
            pstmt.setString(2, voiture.getMatricule());
            pstmt.setInt(3, voiture.getMarque_voiture().getIdMarque()); // Assuming Marque has a getId() method
            pstmt.setInt(4, voiture.getCategorie().getIdCategorie()); // Assuming Categorie has a getId() method
            pstmt.setInt(5, voiture.getDetail().getIdDetail()); // Assuming Detail_voiture has a getId() method
            pstmt.setInt(6, id_car);
            System.out.println("Updating id: " + id_car + " in the table voiture to " + voiture.getMarque_voiture().getIdMarque());
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating " + id_car + " in voiture to " +voiture.getMarque_voiture().getIdMarque());
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    public void update(Voiture voiture, int id_car) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            update(con, voiture, id_car);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while updating "+id_car+" to "+voiture+" in voiture");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    public void delete(Connection con,int id_car) throws Exception {
        Statement stmt = null;
        try{
            String query = "DELETE FROM voiture WHERE idcar="+ id_car +"";
            stmt = con.createStatement();
            System.out.println("Deleting id :"+ id_car + " in the table voiture");
            System.out.println(query);

            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while deleting "+ id_car + " in the table voiture");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void delete(int idcar) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            delete(con, idcar);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error while deleting"+idcar+" in voiture");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

}