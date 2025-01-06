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

    ;

}
