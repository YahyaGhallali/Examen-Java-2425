package com.mri.examenjava2425;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String nom;
    private List<Commande> commandes;

    public Client(int id, String nom, List<Commande> commandes) {
        this.id = id;
        this.nom = nom;
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", commandes=" + commandes +
                '}';
    }

    public Client(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.commandes = new ArrayList<Commande>();
    }

    public void ajouterCommande(Commande commande) {
        this.commandes.add(commande);
    }

    public void supprimerCommande(Commande commande) {
        this.commandes.remove(commande);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
