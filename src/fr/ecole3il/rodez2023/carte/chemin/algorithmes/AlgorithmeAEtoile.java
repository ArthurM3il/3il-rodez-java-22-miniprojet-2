package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    /**
     * Méthode permettant de trouver le chemin le plus court entre deux points sur un graphe donné
     * @param graphe
     * @param depart
     * @param arrivee
     * @return Le chemin le plus court
     */
        @Override
        public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
            Map<Noeud<E>, Double> coutEstime = new HashMap<>();
            Map<Noeud<E>, Double> coutactuel = new HashMap<>();
            Map<Noeud<E>, Noeud<E>> predecessors = new HashMap<>();

            for (Noeud<E> noeud : graphe.getNoeuds()) {
                coutEstime.put(noeud, Double.POSITIVE_INFINITY);
                coutactuel.put(noeud, Double.POSITIVE_INFINITY);
                predecessors.put(noeud, null);
            }
            coutEstime.put(depart, 0.0);
            coutactuel.put(depart, 0.0);
            PriorityQueue<Noeud<E>> filePriority = new PriorityQueue<>((n1, n2) -> (int) (coutEstime.get(n1) - coutEstime.get(n2)));
            filePriority.add(depart);
            while (!filePriority.isEmpty()) {
                Noeud<E> noeud = filePriority.poll();
                if (noeud.equals(arrivee)) break;

                for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                    double cout = coutactuel.get(noeud) + graphe.getCoutArete(noeud, voisin);
                    if (cout < coutactuel.get(voisin)) {
                        predecessors.put(voisin, noeud);
                        coutactuel.put(voisin, cout);
                        coutEstime.put(voisin, cout);
                        filePriority.add(voisin);
                    }
                }
            }
            List<Noeud<E>> chemin = new LinkedList<>();
            Noeud<E> noeud = arrivee;
            while (noeud != null) {
                chemin.add(noeud);
                noeud = predecessors.get(noeud);
            }
            Collections.reverse(chemin);

            return chemin;
        }
    }