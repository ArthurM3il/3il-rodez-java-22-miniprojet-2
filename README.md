# Mini projet Java - Repérage et direction sur une carte en 2D

## Réponses aux questions

#### Quelle structure de données pourrait être utilisé pour stocker les relations entre les noeufs du graphe et les informations associées à ces relations, comme les coûts des arêtes?
La bonne structure serait un tableau.

#### Pourquoi pensez-vous que les classes Noeud et Graphe ont été définies avec des paramètres génériques ?
Les classes sont définies avec des paramètres génériques afin de réduire la réutilisation de code et d'améliorer la clarté.

#### Pourquoi pensez-vous que le création d'une interface est une bonne pratique dans ce contexte ?
L'interface permet généralement de créer un comportement générique. Ici, l'interface permettra d'implémenter plus simplement une même méthode sur plusieurs classes différentes. 
Dans le cas présent, cela permet d'utiliser plus facilement deux algorithmes différents retournant la même chose.

## Réalisations 

Dire que tout marche serait un mensonge, cependant, la génération de la carte est fonctionnelle, la chose qui ne l'est pas
est le chemin le plus court entre deux points. Je n'ai pas réussi à debugger le problème.

## Retour personnel

Le projet était dans l'ensemble bien plus simple que le premier, que ce soit dans sa longueur ou dans la compréhension du sujet dans sa globalité.
Les points les plus difficiles selon moi ont étés la réalisation (et la compréhension) des algorithmes de Dijkstra et A*. 
Je pense avoir globalement mieux réussi ce miniprojet que le premier que ce soit dans la compréhension ou dans la réalisation.