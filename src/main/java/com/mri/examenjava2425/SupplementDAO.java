package com.mri.examenjava2425;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAO {
    private Connection connection;

    public List<Supplement> listeSupplements() throws SQLException {
        List<Supplement> supplements = new ArrayList<>();
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplement");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            double prix = resultSet.getDouble("prix");
            Supplement supplement = new Supplement(id, nom, prix);
            supplements.add(supplement);
        }

        return supplements;
    }

    public void ajouteSupplement(Supplement supplement) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO supplement VALUES(?, ?, ?)");
        preparedStatement.setInt(1, supplement.getId());
        preparedStatement.setString(2, supplement.getNom());
        preparedStatement.setDouble(3, supplement.getPrix());
        preparedStatement.executeUpdate();
    }

    public Supplement chercheSupplement(int id) throws SQLException {
        Supplement supplement = null;
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplement WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            supplement = new Supplement(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getDouble("prix")
            );
        }
        return supplement;
    }

    public void modierSupplement(Supplement supplement) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE supplement SET nom=?, prix=? WHERE id=?");
        preparedStatement.setString(1, supplement.getNom());
        preparedStatement.setDouble(2, supplement.getPrix());
        preparedStatement.setInt(3, supplement.getId());
        preparedStatement.executeUpdate();
    }

    public void supprimerSupplement(int id) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplement WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }


}
