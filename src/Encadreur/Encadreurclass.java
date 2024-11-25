package Encadreur;

import Etudiant.Etudiantclass;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Encadreurclass {
    private String prenom;
    private String nom;
    private int cin;
    private int telephone;
    private String email;
    private ArrayList<Etudiantclass> listetudiants;
    public Encadreurclass(String p,String n,int c,int tel,String em)
    {
        prenom=p;
        nom=n;
        cin=c;
        telephone=tel;
        email=em;
        listetudiants=new ArrayList<>();
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
