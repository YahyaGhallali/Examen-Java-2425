package com.mri.examenjava2425;

import java.util.List;

public class PlatPrincipal {
    private  int id;
    private String nom;
    private double prix;
    private List<Ingredient> ingredients;

    public PlatPrincipal(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public PlatPrincipal(int id, String nom, List<Ingredient> ingredients) {
        this.id = id;
        this.nom = nom;
        this.ingredients = ingredients;
        this.calculatePrice();
    }

    private void calculatePrice() {
        for (Ingredient ingredient : ingredients) {
            this.prix += ingredient.getPrix();
        }
    }

    @Override
    public String toString() {
        return "PlatPrincipal{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", ingredients=" + ingredients +
                '}';
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void ajouterIngredient(Ingredient ing1) {
    }
}
