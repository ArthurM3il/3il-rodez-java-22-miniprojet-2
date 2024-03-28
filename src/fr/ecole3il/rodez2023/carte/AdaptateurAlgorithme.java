package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {
    /**
     * Méthode permettant de trouver le chemin le plus court
     * @param algorithme
     * @param carte
     * @param xDepart
     * @param yDepart
     * @param xArrivee
     * @param yArrivee
     * @return le chemin le plus court
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        Graphe<Case> graphe = createGraphe(carte);
        Noeud<Case> noeudDepart = getNoeud(graphe, xDepart, yDepart);
        Noeud<Case> noeudArrivee = getNoeud(graphe, xArrivee, yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }

        return new Chemin(cheminCases);
    }



    static Graphe<Case> createGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<>(caseActuelle));
            }
        }

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){
        Noeud<Case> currentNode = getNoeud(graphe,x,y);
        if (currentNode ==null) return;
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case c = noeud.getValeur();
            if (c.equals(currentCase)) {
                currentNode = noeud;
                break;
            }
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                Noeud<Case> neighborNode = getNoeud(graphe,newX, newY);
                if (neighborNode != null) {
                    Case neighborCase = neighborNode.getValeur();
                    double cost = calculerCout(currentCase, neighborCase);
                    graphe.ajouterArete(currentNode, neighborNode, cost);
                    currentNode.ajouterVoisin(neighborNode);
                }
            }
        }
    }

    static double calculerCout(Case from, Case to){
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    private static Noeud<Case> getNoeud(Graphe graphe,int x, int y) {
        for (Object noeud : graphe.getNoeuds()) {
            Case caseActuelle = (Case) ((Noeud)noeud).getValeur();
            if (caseActuelle.getX() == x && caseActuelle.getY() == y) {
                return (Noeud)noeud;
            }
        }
        return null;
    }
}
