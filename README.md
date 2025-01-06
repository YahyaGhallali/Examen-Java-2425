# Examen Java 2024/25

## 1. Diagrammes de Classe

### 1. Diagramme de classe principale:
```mermaid
classDiagram
    class Client {
        - int id
        - String nom
        - List<Commande> commandes
        + void ajouterCommande(Commande commande)
        + void supprimerCommande(Commande commande)
    }

    class PlatPrincipal {
        - int id
        - String nom
        - double prix
        - List<Ingredient> ingredients
        + void calculerPrix()
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
%%        - List<Ingredient> ingredients
        - double prix
        + double calculerTotal()
    }

    class Commande {
        - int id
        - Client client
        - List<Repas> repas
        - double prix
        + double calculerTotal()
    }

    Client "1" -- "*" Commande: Commande
    Commande "1" --* "*" Repas: Contient
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

## 4. Classes Java

### Client.java
```java
public class Client {
    private int id;
    private String nom;
    private List<Commande> commandes;

    public Client(int id, String nom, List<Commande> commandes) {
        this.id = id;
        this.nom = nom;
        this.commandes = commandes;
    }
}
```

### Commande.java
```java
public class Commande {
    private int id;
    private Client client;
    private List<Repas> repas;
    private double prix;


    public Commande(int id, Client client, List<Repas> repas) {
        this.id = id;
        this.client = client;
        this.repas = repas;
    }

    private void calculatePrix() {
        for (Repas repas : repas) {
            this.prix += repas.getPrix();
        }
    }

    public Commande(int id, Client client) {
        this.id = id;
        this.client = client;
        this.repas = new ArrayList<>();
    }

    public void ajouterRepas(Repas repas) {
        this.repas.add(repas);
    }

    public void deleteRepas(Repas repas) {
        this.repas.remove(repas);
    }
}
```
### PlatPrincipal.java
````java
public class PlatPrincipal {
private  int id;
private String nom;
private double prix;
private List<Ingredient> ingredients;

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
}
````
### Commande.java
````java
public class Commande {
private int id;
private Client client;
private List<Repas> repas;
private double prix;


    public Commande(int id, Client client, List<Repas> repas) {
        this.id = id;
        this.client = client;
        this.repas = repas;
    }

    private void calculatePrix() {
        for (Repas repas : repas) {
            this.prix += repas.getPrix();
        }
    }}
````
### Ingredient.java
````java
public class Ingredient {
    private  int id;
    private String nom;
    private double prix;

    public Ingredient(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }}
````
### Repas.java
````java
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
}
````
### Supplement.java

````java
public class Supplement {
    private  int id;
    private String nom;
    private  double prix;

    public Supplement(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

}
````
## 5.  
## 6. 
## 7. 
## 8. 
## 9. 
## 10. 
## 11. 
## 12. 
