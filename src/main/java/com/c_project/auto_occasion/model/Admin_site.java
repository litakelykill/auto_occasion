package com.c_project.auto_occasion.model;

public class Admin_site {
    int idadmin;
    String email;
    String nom;
    String prenom;
    String mdp;
    String contact;

    public Admin_site() {
    }

    public int getIdadmin() {
        return idadmin;
    }
    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Admin_site(int idadmin, String email, String nom, String prenom, String mdp, String contact) {
        this.idadmin = idadmin;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.contact = contact;
    }

    public Admin_site(String email, String nom, String prenom, String mdp, String contact) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.contact = contact;
    }
}
