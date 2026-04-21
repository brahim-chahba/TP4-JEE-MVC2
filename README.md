# Projet Java EE — Gestion des Produits MVC2

Application web CRUD développée avec l'architecture **MVC2 + 3-Tiers** en utilisant Servlet, JSP, JSTL, Hibernate et Maven. Elle inclut un système d'authentification avec gestion des rôles (ADMIN / USER) et utilise le pattern **Front Controller**.

---

## Architecture du projet

<img width="440" height="801" alt="Screenshot 2026-04-21 210032" src="https://github.com/user-attachments/assets/e6f09835-6691-4518-be96-ed5a0793e4ed" />



```
TP4-MVC2/
├── src/main/java/
│   ├── dao/
│   │   ├── Produit.java               ← Entité Hibernate Produit
│   │   ├── ProduitDAO.java            ← Interface CRUD
│   │   ├── ProduitImpl.java           ← Implémentation Hibernate
│   │   ├── Utilisateur.java           ← Entité Hibernate Utilisateur
│   │   ├── UtilisateurDAO.java        ← Interface Authentification
│   │   └── UtilisateurImpl.java       ← Implémentation Authentification
│   ├── services/
│   │   ├── ProduitMetier.java         ← Interface service produit
│   │   ├── ProduitMetierImpl.java     ← Singleton service produit
│   │   ├── UtilisateurMetier.java     ← Interface service auth
│   │   └── UtilisateurMetierImpl.java ← Singleton service auth
│   ├── filters/
│   │   └── AuthFilter.java            ← Filtre de sécurité (Front Controller Filter)
│   ├── util/
│   │   └── HibernateUtil.java         ← Utilitaire pour SessionFactory Hibernate
│   └── web/
│       ├── DispatcherServlet.java     ← Front Controller (Gère toutes les actions CRUD)
│       ├── loginServlet.java          ← Gestion de la connexion
│       └── logoutServlet.java         ← Gestion de la déconnexion
├── src/main/resources/
│   └── hibernate.cfg.xml              ← Configuration Hibernate (Base de données)
├── src/main/webapp/
│   ├── login.jsp                      ← Page de connexion
│   ├── index.jsp                      ← Page principale (MVC2 : gérée par DispatcherServlet)
│   └── WEB-INF/
│       └── web.xml                    ← Descripteur de déploiement
└── pom.xml                            ← Dépendances Maven (Hibernate/MySQL/JSTL)
```

---

## 1. Page de connexion — `login.jsp`

Cette page permet à un utilisateur de se connecter avec son login et son mot de passe. L'authentification est vérifiée en base de données via Hibernate.

<img width="924" height="211" alt="Screenshot 2026-04-21 225142" src="https://github.com/user-attachments/assets/02da867b-17cf-4fb6-8f37-520d9d7592cf" />


---

## 2. Page principale — `index.jsp` (ADMIN)

 Le `DispatcherServlet` prépare les données (`listeProduits`) et redirige vers cette vue. L'administrateur peut ajouter, modifier (`produitEdit`) et supprimer des produits.

<img width="1015" height="472" alt="Screenshot 2026-04-21 225303" src="https://github.com/user-attachments/assets/381e4e82-5ec9-450e-8b07-fe5939033297" />


---

## 3. Page principale — `index.jsp` (USER)

Un utilisateur avec le rôle USER peut consulter et rechercher des produits par ID, mais les fonctionnalités de modification, suppression et ajout sont masquées.

<img width="813" height="399" alt="Screenshot 2026-04-21 225528" src="https://github.com/user-attachments/assets/a0494175-a3a7-472d-8658-9202c5b4beb8" />

---
## 4. Modification d'un produit 
Un admin peut modifier un produit 
<img width="1001" height="434" alt="Screenshot 2026-04-21 225901" src="https://github.com/user-attachments/assets/3b513529-bade-46ed-961c-481cec4ddf53" />

ou supprimer , mettre à jour.


---

## Flux de l'application (MVC2)

```
Requête HTTP (/produit?action=...)
     ↓
AuthFilter — session valide ?
     ├── NON → redirect /login
     └── OUI → laisse passer
                   ↓
             DispatcherServlet 
                   ↓ 
             1. Analyse l'action (list, add, update, delete, edit)
             2. Appelle le service (ProduitMetierImpl)
             3. Stocke les résultats dans request scope
             4. Forward vers index.jsp
                   ↓
             index.jsp (Affiche les données via JSTL)
```

---

## Technologies utilisées

| Technologie | Rôle |
|-------------|------|
| **Servlet** | Controller (Dispatcher & Auth) |
| **JSP + JSTL** | Vue (Template dynamique) |
| **Hibernate** | ORM (Persistance des données) |
| **MySQL** | Base de données relationnelle |
| **Maven** | Gestion de projet et dépendances |
| **Pattern MVC2** | Front Controller centralisé |
| **Pattern Singleton**| Services et DAO transversaux |

---

## Dépendances Clés (`pom.xml`)

```xml
<!-- Hibernate Core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.1.Final</version>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>

<!-- JSTL -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

---

## Lancement

1. Configurer la base de données dans `src/main/resources/hibernate.cfg.xml`.
2. Importer le projet dans **Eclipse** (Import -> Existing Maven Projects).
3. Clic droit sur le projet → **Run As → Run on Server** (Tomcat 9+).
4. Accéder à : `http://localhost:8080/TP4-MVC2/login`.

---

*Projet TP4 — Architecture MVC2 & Hibernate*
