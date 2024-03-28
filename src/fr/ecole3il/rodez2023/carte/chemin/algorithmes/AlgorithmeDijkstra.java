package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    /**
     * Methode retournant le chemin le plus court entre deux Noeud et un graphe donné
     * @param graphe le graphe donné
     * @param depart le noeud de depart
     * @param arrivee le nœud d'arrivée
     * @return Le chemin le plus court
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        //Initialisation
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecessors = new HashMap<>();

        //Prédécesseurs
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecessors.put(noeud, null);
        }
        couts.put(depart, 0.0);

        //File de priorité
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> (int) (couts.get(n1) - couts.get(n2)));
        filePriorite.add(depart);
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeud = filePriorite.poll();
            if (noeud.equals(arrivee)) break;

            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double cout = couts.get(noeud) + graphe.getCoutArete(noeud, voisin);
                if (cout < couts.get(voisin)) {
                    couts.put(voisin, cout);
                    predecessors.put(voisin, noeud);
                    filePriorite.add(voisin);
                }
            }
        }

        //Chemin le plus court
        List<Noeud<E>> cheminLePlusCourt = new LinkedList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            cheminLePlusCourt.addFirst(noeud);
            noeud = predecessors.get(noeud);
        }
        Collections.reverse(cheminLePlusCourt);

        return cheminLePlusCourt;
    }
}
