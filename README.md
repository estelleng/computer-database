Estelle Nguyen
Roxanne Ricci

------------------------------------------------------------------
CORRECTION+CODE REVIEW:
------------------------------------------------------------------

Expérience utilisateur:
Premier constat: La pagination: si je veux passer directement à la page 10, je dois y aller page par page. Avoir une dizaine de pages cliquables aurait été pas mal.
Les dates: Le format n'est pas "lisible", il aurait été mieux d'implémenter une fonction de formatage des dates pour les afficher de manière convenable
Le delete computer: j'ai 575 ordinateurs, c'est compliqué de le faire dans une liste. Implémenter un bouton "supprimer" à coté de chaque ordinateur aurait été beaucoup plus pratique.
La recherche ne fonctionne pas: je tombe sur une erreur 500 avec une NullPointerException, parce qu'il n'y a pas de vérification sur la nullité du champ "page"
L'édit ne préremplit pas les champs de type date. Ca aurait pu être moins embêtant si on pouvait quand même valider si on ne la modifiait pas, mais on reste bloqués sur le formulaire, sans message d'erreur.
Aucun message d'erreur affiché à l'utilisateur lorsqu'il soumet un formulaire: du coup, comment est ce que je sais que j'ai mal renseigné un champ?

Le code:
-Commentaires: yes!
-Protection des jsp: bien
-Utilisation des builder: yes!
-Utilisation des enums pour les singletons: yes!
-Controllers: attention! très peu de vérifications sur la validité et la non nullité des champs -> vous aurez des NullPointerException et l'utilisateur ne comprendra pas pourquoi! Vos variables qui définissent des constantes (ex: nbElements) devraient être des final static int. 
-Services: RAS
-Dao: fermeture des connexions dans un bloc finally: bien
-Domain: RAS
-JSP: Ce qui est présent est ok, dommage qu'il manque tellement de choses!

Bilan: Projet décevant. Votre code est bien commenté, bien structuré, mais vous faites des erreurs bêtes: pas de checks sur vos variables. L'expérience utilisateur est peu ergonomique et vous n'avez pas rempli le cahier des charges, sans même résoudre des bugs simples à mettre en évidence.
Avoir travaillé pendant les 4 jours vous donnera une note acceptable, mais j'attendais davantage de vous!

