package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E> {

    private Map<Noeud<E>, Map<Noeud<E>,Double>> graphe;
    private Noeud<E> depart;
    private Noeud<E> arrivee;
    private double cout;
    private Noeud<E> noeud;
    private List<Noeud<E>> noeuds;

    public void ajouterNoeud(Noeud<E> noeud){
        if (noeuds.contains(noeud)) System.out.println("Le noeud existe déjà dans le graphe");
        else {
            noeuds.add(noeud);
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout){
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        if(graphe.containsKey(depart) && graphe.containsKey(arrivee)) System.out.println("L'arete existe déjà");
        Map<Noeud<E>, Double> buffer = new HashMap<>();
        buffer.put(arrivee,cout);
        graphe.put(depart, buffer);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee){
         return graphe.get(depart).get(arrivee);
    }

    public List<Noeud<E>> getNoeuds(){
        List<Noeud<E>> liste = new ArrayList();
        for(Map.Entry<Noeud<E>, Map<Noeud<E>, Double>> entry : graphe.entrySet()){
            liste.add(entry.getKey());
        }
        return liste;
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud){
        return noeud.getVoisins();
    }


}
