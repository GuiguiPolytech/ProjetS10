# Application Back-end de Gestion de Frais

Cette application back-end de gestion de frais est développée avec Spring Boot. Elle fournit une API RESTful pour la création, la lecture, la mise à jour et la suppression des frais de déplacement.

## Fonctionnalités

- Endpoints pour la gestion des personnes, des frais de déplacement et des états des frais.
- Stockage des données dans une base de données relationnelle (par exemple, MySQL, PostgreSQL).
- Validation des données d'entrée et gestion des erreurs.

## Technologies utilisées

- Java et Spring Boot pour le développement de l'application back-end.
- Hibernate pour la persistance des données.
- Base de données relationnelle (par exemple, MySQL, PostgreSQL) pour le stockage des données.
- Maven pour la gestion des dépendances et la construction du projet.

## Configuration et utilisation

1. Clonez ce dépôt sur votre machine locale.
2. Assurez-vous d'avoir Java et Maven installés.
3. Configurez votre base de données en modifiant les propriétés de connexion dans le fichier `application.properties`.
4. Exécutez la commande `mvn spring-boot:run` pour démarrer l'application.
5. L'application sera accessible à l'URL `http://localhost:8080`.

## Structure du projet

Le projet suit la structure suivante :

- `src/main/java/com/example/model/` : Contient les classes de modèles représentant les entités de la base de données.
- `src/main/java/com/example/repository/` : Contient les interfaces de repository pour l'accès aux données.
- `src/main/java/com/example/controller/` : Contient les classes de contrôleurs pour la gestion des endpoints de l'API.
- `src/main/resources/` : Contient les fichiers de configuration et les ressources.

## Contribution

Les contributions sont les bienvenues ! Si vous souhaitez apporter des améliorations à l'application back-end, veuillez suivre les étapes suivantes :

1. Fork ce dépôt.
2. Créez une branche pour vos modifications : `git checkout -b feature/ma-fonctionnalite`.
3. Effectuez les modifications nécessaires et testez-les.
4. Validez vos modifications : `git commit -m 'Ajout de ma fonctionnalité'`.
5. Poussez vers la branche principale du dépôt forké : `git push origin feature/ma-fonctionnalite`.
6. Ouvrez une Pull Request pour que nous puissions examiner vos modifications.

## Auteurs

- Guillaume MACIE
- Titouan Lacombe

## Licence

Ce projet est sous licence MIT. Vous pouvez consulter le fichier [LICENSE](LICENSE) pour plus de détails.
