package com.mri.examenjava2425;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    private Connection connection;

    public List<Ingredient> listeIngredients() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ingredient");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            double prix = resultSet.getDouble("prix");
            Ingredient ingredient = new Ingredient(id, nom, prix);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    public void ajouterIngredient(Ingredient ingredient) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ingredient VALUES(?, ?, ?)");
        preparedStatement.setInt(1, ingredient.getId());
        preparedStatement.setString(2, ingredient.getNom());
        preparedStatement.setDouble(3, ingredient.getPrix());
        preparedStatement.executeUpdate();
    }

    public Ingredient chercheIngredient(int id) throws SQLException {
        Ingredient ingredient = null;
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ingredient WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            ingredient = new Ingredient(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getDouble("prix")
            );
        }
        return ingredient;
    }

    public void modierIngredient(Ingredient ingredient) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ingredient SET nom=?, prix=? WHERE id=?");
        preparedStatement.setString(1, ingredient.getNom());
        preparedStatement.setDouble(2, ingredient.getPrix());
        preparedStatement.setInt(3, ingredient.getId());
        preparedStatement.executeUpdate();
    }
}
