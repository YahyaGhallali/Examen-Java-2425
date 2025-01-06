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

    public Client(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.commandes = new ArrayList<Commande>();
    }

    public  void  ajouterCommande(Commande commande) {
        this.commandes.add(commande);
    }

}
