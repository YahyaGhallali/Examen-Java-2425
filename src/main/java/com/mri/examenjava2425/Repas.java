package com.mri.examenjava2425;

import java.util.List;

public class Repas {
    private int id;
    private PlatPrincipal platPrincipal;
    private List<Supplement> supplement;
    private double prix;

    public Repas(int id, PlatPrincipal platPrincipal, List<Supplement> supplement) {
        this.id = id;
        this.platPrincipal = platPrincipal;
        this.supplement = supplement;
        this.calculatePrice();
    }

    private void calculatePrice() {
        this.prix = platPrincipal.getPrix();
        for (Supplement supplement : supplement) {
            this.prix += supplement.getPrix();
        }

        this.prix += platPrincipal.getPrix();
    }

    @Override
    public String toString() {
        return "Repas{" +
                "id=" + id +
                ", platPrincipal=" + platPrincipal +
                ", supplement=" + supplement +
                ", prix=" + prix +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlatPrincipal getPlatPrincipal() {
        return platPrincipal;
    }

    public void setPlatPrincipal(PlatPrincipal platPrincipal) {
        this.platPrincipal = platPrincipal;
    }

    public List<Supplement> getSupplement() {
        return supplement;
    }

    public void setSupplement(List<Supplement> supplement) {
        this.supplement = supplement;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
