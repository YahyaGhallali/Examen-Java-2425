package com.mri.examenjava2425;

import java.sql.*;
import java.util.Scanner;

public class RestaurantShell {
    private Connection connection;
    private Scanner scanner;
    private ClientDAO clientDAO;
    private CommandeDAO commandeDAO;
    private RepasDAO repasDAO;
    private PlatPrincipalDAO platPrincipalDAO;
    private SupplementDAO supplementDAO;
    private IngredientDAO ingredientDAO;

    public RestaurantShell() {
        try {
            // Initialize connection (you should use proper connection parameters)
            connection = SingletonConnexionDB.getConnection();
            clientDAO = new ClientDAO();
            commandeDAO = new CommandeDAO();
            repasDAO = new RepasDAO();
            platPrincipalDAO = new PlatPrincipalDAO();
            supplementDAO = new SupplementDAO();
            ingredientDAO = new IngredientDAO();

            scanner = new Scanner(System.in);

            // Insert initial data
            insertSampleData();

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            System.exit(1);
        }
    }

    private void insertSampleData() throws SQLException {
        // Insert Clients
        Client client1 = new Client("Jean Dupont");
        Client client2 = new Client("Marie Martin");
        clientDAO.ajouterClient(client1);
        clientDAO.ajouterClient(client2);

        // Insert Ingredients
        Ingredient ing1 = new Ingredient("Pain burger", 1.50);
        Ingredient ing2 = new Ingredient("Steak haché", 3.50);
        Ingredient ing3 = new Ingredient("Salade", 0.50);
        ingredientDAO.ajouterIngredient(ing1);
        ingredientDAO.ajouterIngredient(ing2);
        ingredientDAO.ajouterIngredient(ing3);

        // Insert PlatPrincipal
        PlatPrincipal plat1 = new PlatPrincipal("Burger Classic");
        plat1.ajouterIngredient(ing1);
        plat1.ajouterIngredient(ing2);
        plat1.ajouterIngredient(ing3);
        platPrincipalDAO.ajouterPlatPrincipal(plat1);

        // Insert Supplements
        Supplement sup1 = new Supplement("Fromage", 1.00);
        Supplement sup2 = new Supplement("Bacon", 1.50);
        supplementDAO.ajouterSupplement(sup1);
        supplementDAO.ajouterSupplement(sup2);

        // Create Repas
        Repas repas1 = new Repas(plat1);
        repas1.ajouterSupplement(sup1);
        repasDAO.ajouterRepas(repas1);

        // Create Commande
        Commande commande1 = new Commande(client1);
        commande1.ajouterRepas(repas1);
        commandeDAO.ajouterCommande(commande1);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    gestionClients();
                    break;
                case "2":
                    gestionCommandes();
                    break;
                case "3":
                    gestionPlats();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Restaurant Management System ===");
        System.out.println("1. Gestion des clients");
        System.out.println("2. Gestion des commandes");
        System.out.println("3. Gestion des plats");
        System.out.println("4. Quitter");
        System.out.print("Choix: ");
    }

    private void gestionClients() {
        while (true) {
            System.out.println("\n=== Gestion des Clients ===");
            System.out.println("1. Ajouter client");
            System.out.println("2. Chercher client");
            System.out.println("3. Modifier client");
            System.out.println("4. Supprimer client");
            System.out.println("5. Retour");
            System.out.print("Choix: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Nom du client: ");
                        String nom = scanner.nextLine();
                        Client newClient = new Client(nom);
                        clientDAO.ajouterClient(newClient);
                        System.out.println("Client ajouté avec succès!");
                        break;
                    case "2":
                        System.out.print("ID du client: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Client client = clientDAO.chercheClient(id);
                        if (client != null) {
                            System.out.println("Client trouvé: " + client.getNom());
                        } else {
                            System.out.println("Client non trouvé");
                        }
                        break;
                    case "3":
                        System.out.print("ID du client à modifier: ");
                        int idModif = Integer.parseInt(scanner.nextLine());
                        Client clientModif = clientDAO.chercheClient(idModif);
                        if (clientModif != null) {
                            System.out.print("Nouveau nom: ");
                            String nouveauNom = scanner.nextLine();
                            clientModif.setNom(nouveauNom);
                            clientDAO.modifierClient(clientModif);
                            System.out.println("Client modifié avec succès!");
                        } else {
                            System.out.println("Client non trouvé");
                        }
                        break;
                    case "4":
                        System.out.print("ID du client à supprimer: ");
                        int idSuppr = Integer.parseInt(scanner.nextLine());
                        clientDAO.supprimerClient(idSuppr);
                        System.out.println("Client supprimé avec succès!");
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Option invalide");
                }
            } catch (SQLException e) {
                System.err.println("Erreur: " + e.getMessage());
            }
        }
    }

    // Similar methods for gestionCommandes() and gestionPlats()...

    public static void main(String[] args) {
        RestaurantShell shell = new RestaurantShell();
        shell.start();
    }
}