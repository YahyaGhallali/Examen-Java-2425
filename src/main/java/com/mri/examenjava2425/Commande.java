package com.mri.examenjava2425;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private Client client;
    private List<Repas> repas;
    private double prix;


    public Commande(int id, Client client, List<Repas> repas) {
        this.id = id;
        this.client = client;
        this.repas = repas;
    }

    private void calculatePrix() {
        for (Repas repas : repas) {
            this.prix += repas.getPrix();
        }
    }

    public Commande(int id, Client client) {
        this.id = id;
        this.client = client;
        this.repas = new ArrayList<>();
    }

    public void ajouterRepas(Repas repas) {
        this.repas.add(repas);
    }

    public void deleteRepas(Repas repas) {
        this.repas.remove(repas);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", client=" + client +
                ", repas=" + repas +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Repas> getRepas() {
        return repas;
    }

    public void setRepas(List<Repas> repas) {
        this.repas = repas;
    }
}
