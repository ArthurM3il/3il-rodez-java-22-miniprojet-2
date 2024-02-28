package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Noeud<E>{
    private E E;
    private List<Noeud<E>> voisins;

    public Noeud(E valeur){
        this.E = valeur;
    }

    public E getValeur(){
        return E;
    }

    public List<Noeud<E>> getVoisins(){
        return voisins;
    }

    public void ajoutervoisin(Noeud<E> voisin){
        voisins.add(voisin);
    }
}
