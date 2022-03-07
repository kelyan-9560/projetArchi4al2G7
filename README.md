# projetArchi4al2G7

Clone de l'application TradesMe: 

Plateforme pour trouver des ouvriers qualifiés (électricien, maçon, plombier, charpentier, etc) pour intervenir sur des projets de construction
Des prestataires emploient des ouvriers qualifiés sur quelques jours ou quelques semaines pour des tâches spécifiques
Les ouvriers peuvent venir et partir sur un même projet
La plateforme permet aux ouvriers qualifiés de s’enregistrer, d’afficher leurs compétences, leur zone géographique de disponibilité et leur taux journalier
Ils ne peuvent être employés directement sans un prestataire de service
Certaines tâches peuvent exiger des certificats d’aptitudes
La plateforme permet aux prestataires de s’enregistrer et lister leurs projets
Pour chaque projet, il est spécifié les métiers et les compétences requises, la localisation, les taux journaliers qu’il souhaite payer, la durée d’engagement, etc

Fonctionnalités implémentées: 

Use_cases: Tradesman, contractor, Project, Task, MatchProjectTradesman

TradesMan: EventBu

MatchProjectTradesman: table representant la relation entre un projet au tradesman le plus adapté aux besoins du projet

Dans le Kernel se trouvent toutes les classes partagées par toute l'application

Capture d’écran 2022-03-07 à 14.51.54<img width="748" alt="image" src="https://user-images.githubusercontent.com/58575124/157047061-f79e8d2d-6939-4bc7-aeb8-17139cee1ff4.png">



- CQS
- revoir les classes d'erreur
- quitter / rejoindre un projet
- vérifier que le tradesman a les compétences requises
- les events
- controlleurs
- les tests

 
