package com.mri.examenjava2425;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private Connection connection;

    public List<Client> listeClients() throws SQLException {
        List<Client> clients = new ArrayList<Client>();

        connection = SingletonConnexionDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM client");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            Client client = new Client(id, nom);
            clients.add(client);

        }
        return clients;
    }

    public void ajouterClient(Client client) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO client VALUES(?, ?)");
        ps.setInt(1, client.getId());
        ps.setString(2, client.getNom());
        ps.executeUpdate();
    }

    public void supprimerClient(int id) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM client WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void modifierClient(Client client) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE client SET nom = ? WHERE id = ?");
        ps.setString(1, client.getNom());
        ps.setInt(2, client.getId());
        ps.executeUpdate();
    }

    public Client chercheClient(int id) throws SQLException {
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Client(rs.getInt("id"), rs.getString("nom"));
        }
        return null;
    }


}
