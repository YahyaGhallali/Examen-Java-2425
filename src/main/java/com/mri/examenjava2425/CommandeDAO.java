package com.mri.examenjava2425;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {
    private Connection connection;

    public Commande chercheCommande(int id) throws SQLException {
        String query = "SELECT * FROM Commande WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Commande commande = new Commande(
                        rs.getInt("id"),
                        new ClientDAO(connection).chercheClient(rs.getInt("client_id"))
                );
                chargerRepas(commande);
                return commande;
            }
        }
        return null;
    }

    public void ajouterCommande(Commande commande) throws SQLException {
        String query = "INSERT INTO Commande (client_id) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, commande.getClient().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                commande.setId(rs.getInt(1));
                sauvegarderRepas(commande);
            }
        }
    }

    public void supprimerCommande(int id) throws SQLException {
        String query = "DELETE FROM Commande WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void modifierCommande(Commande commande) throws SQLException {
        String query = "UPDATE Commande SET client_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, commande.getClient().getId());
            stmt.setInt(2, commande.getId());
            stmt.executeUpdate();

            // Update related repas
            supprimerRepasDeCommande(commande.getId());
            sauvegarderRepas(commande);
        }
    }

}