package com.mri.examenjava2425;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepasDAO {
    private Connection connection;
    private PlatPrincipalDAO platPrincipalDAO;
    private SupplementDAO supplementDAO;

    public List<Repas> listeRepas() throws SQLException {
        List<Repas> repas = new ArrayList<>();
        List<PlatPrincipal> platsprincipales = platPrincipalDAO.listePlatsPrincipaux();
        List<Supplement> supplements = supplementDAO.listeSupplements();
        connection = SingletonConnexionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * from repas join examenjava2425.repas_supplement rs on rs.repas_id = repas.id;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id_repas = resultSet.getInt(0);
            int id_plat = resultSet.getInt(1);
            int id_supplement = resultSet.getInt(2);
            List<Supplement> supplements1 = new ArrayList<>();
            supplements1.add(supplements.get(id_supplement));
            repas.add(new Repas(id_repas, platsprincipales.get(id_plat), supplements1));
        }

        return repas;
    }
    public Repas chercheRepas(int id) throws SQLException {
        String query = "SELECT * FROM Repas WHERE id = ?";
        connection = SingletonConnexionDB.getConnection()
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Repas repas = new Repas(
                        rs.getInt("id"),
                        new PlatPrincipalDAO(connection).cherchePlatPrincipal(rs.getInt("platprincipal_id"))
                );
                chargerSupplements(repas);
                return repas;
            }
        }
        return null;
    }

    public void ajouterRepas(Repas repas) throws SQLException {
        String query = "INSERT INTO Repas (platprincipal_id) VALUES (?)";
        connection = SingletonConnexionDB.getConnection()
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, repas.getPlatPrincipal().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                repas.setId(rs.getInt(1));
                sauvegarderSupplements(repas);
            }
        }
    }

    public void supprimerRepas(int id) throws SQLException {
        // First delete related supplements associations
        supprimerSupplementsDeRepas(id);

        // Then delete the repas itself
        String query = "DELETE FROM Repas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void modifierRepas(Repas repas) throws SQLException {
        String query = "UPDATE Repas SET platprincipal_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, repas.getPlatPrincipal().getId());
            stmt.setInt(2, repas.getId());
            stmt.executeUpdate();

            // Update supplements
            supprimerSupplementsDeRepas(repas.getId());
            sauvegarderSupplements(repas);
        }
    }

    private void chargerSupplements(Repas repas) throws SQLException {
        String query = "SELECT s.* FROM Supplement s " +
                "JOIN Repas_Supplement rs ON s.id = rs.supplement_id " +
                "WHERE rs.repas_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, repas.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Supplement supplement = new Supplement(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDouble("prix")
                );
                repas.ajouterSupplement(supplement);
            }
        }
    }

    private void sauvegarderSupplements(Repas repas) throws SQLException {
        String query = "INSERT INTO Repas_Supplement (repas_id, supplement_id) VALUES (?, ?)";
        connection = SingletonConnexionDB.getConnection()
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (Supplement supplement : repas.getSupplements()) {
                stmt.setInt(1, repas.getId());
                stmt.setInt(2, supplement.getId());
                stmt.executeUpdate();
            }
        }
    }

    private void supprimerSupplementsDeRepas(int repasId) throws SQLException {
        String query = "DELETE FROM Repas_Supplement WHERE repas_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, repasId);
            stmt.executeUpdate();
        }
    }

    // Additional utility methods

    public List<Repas> chercheTousRepas() throws SQLException {
        List<Repas> repas = new ArrayList<>();
        String query = "SELECT * FROM Repas";
        connection = SingletonConnexionDB.getConnection()
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Repas r = new Repas(
                        rs.getInt("id"),
                        new PlatPrincipalDAO(connection).cherchePlatPrincipal(rs.getInt("platprincipal_id"))
                );
                chargerSupplements(r);
                repas.add(r);
            }
        }
        return repas;
    }

    public List<Repas> chercheRepasParPlatPrincipal(int platPrincipalId) throws SQLException {
        List<Repas> repas = new ArrayList<>();
        String query = "SELECT * FROM Repas WHERE platprincipal_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, platPrincipalId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Repas r = new Repas(
                        rs.getInt("id"),
                        new PlatPrincipalDAO(connection).cherchePlatPrincipal(rs.getInt("platprincipal_id"))
                );
                chargerSupplements(r);
                repas.add(r);
            }
        }
        return repas;
    }
}
