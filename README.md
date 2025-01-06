# Examen Java 2024/25

## 1. Diagrammes de Classe

### 1. Diagramme de classe principale:
```mermaid
classDiagram
    class Client {
        - int id
        - String nom
        + List<Commande> commandes
        + void ajouterCommande(Commande commande)
    }

    class PlatPrincipal {
        - int id
        - String nom
        - double prix
        - List<Ingredient> ingredients
        + double calculerPrix()
    }

    class Ingredient {
        - int id
        - String nom
        - double prixUnitaire
    }

    class Supplement {
        - int id
        - String nom
        - double prix
    }

    class Repas {
        - int id
        - PlatPrincipal platPrincipal
        - List<Supplement> supplements
        - List<Ingredient> ingredients
        - double prix
        + double calculerTotal()
    }

    class Commande {
        - int id
        - Client client
        - List<Repas> repas
        + double calculerTotal()
    }

    Client "1" -- "*" Commande: Commande
    Commande "*" --o "1" Repas: Contient
    PlatPrincipal "*" --* "1" Ingredient: Composé
    Repas "1" --* "1" PlatPrincipal: composé
    Repas "*" --* "1" Supplement: Composé
```

### 2. Digramme de classe DAO:
```mermaid
classDiagram
    class ClientDAO {
        + List<Client> listeClients()
        + void creerClient(Client client)
        + Client lireClient(int id)
        + void mettreAJourClient(Client client)
        + void supprimerClient(int id)
    }

    class PlatPrincipalDAO {
        + List<PlatPrincipal> listePlatsPrincipaux()
        + void creerPlatPrincipal(PlatPrincipal plat)
        + PlatPrincipal lirePlatPrincipal(int id)
        + void mettreAJourPlatPrincipal(PlatPrincipal plat)
        + void supprimerPlatPrincipal(int id)
    }

    class IngredientDAO {
        + List<Ingredient> listeIngredients()
        + void creerIngredient(Ingredient ingredient)
        + Ingredient lireIngredient(int id)
        + void mettreAJourIngredient(Ingredient ingredient)
        + void supprimerIngredient(int id)
    }


    class SupplementDAO {
        + List<Supplement> listeSupplements()
        + void creerSupplement(Supplement supplement)
        + Supplement lireSupplement(int id)
        + void mettreAJourSupplement(Supplement supplement)
        + void supprimerSupplement(int id)
    }
    
    class SingletonConnexionDB {
        -String url
        -String user
        -String password
        + Connection getConnection()
    }
    
    SingletonConnexionDB "1" -- "*" ClientDAO: Utilise
    SingletonConnexionDB "1" -- "*" PlatPrincipalDAO: Utilise
    SingletonConnexionDB "1" -- "*" IngredientDAO: Utilise
    SingletonConnexionDB "1" -- "*" SupplementDAO: Utilise
```

## 2. Diagramme MLD

## 3. Tables MySQL

```mysql
```

## 4. 
## 5.  
## 6. 
## 7. 
## 8. 
## 9. 
## 10. 
## 11. 
## 12. 
