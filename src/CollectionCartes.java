

import java.util.ArrayList;
import java.util.Random;


public class CollectionCartes  {

   private String label;
   private ArrayList<Carte> cartes;
   
/**
 * Une collection de cartes à jouer.
 */
   public CollectionCartes(String label) {
      this.label = label;
      this.cartes = new ArrayList<Carte>();
   }

    /**
    * Renvoie l'étiquette de la collection de cartes.
    */
   public String getLabel() {
      return label;
   }

   /**
    * Retourne la carte avec l'index donné.
    */
   public Carte getCardAt(int i) {
      return cartes.get(i);
   }
/**
    * Retourne la dernière carte.
    */
 
   public Carte last() {
      int i = size() - 1;
      return getCardAt(i);
   }

   /**
    * Ajoute la carte donnée à la collection.
    */
   public void append(Carte c) {
      cartes.add(c);
   }

   
   /**
    * Supprime et retourne la carte avec l'index donné.
    */
   public Carte removeAt(int i) {
      return cartes.remove(i);
   }

    /**
    * Retire et retourne la dernière carte.
    */
   public Carte removeLast() {
      int i = size() - 1;
      return removeAt(i);
   }
 /**
    * Retourne le nombre de cartes.
    */
  
   public int size() {
      return cartes.size();
   }
 /**
    * Vrai si la collection est vide, faux sinon.
    */
 
   public boolean isEmpty() {
      return size() == 0;
   }

  /**
    * Déplace n cartes de cette collection vers la collection donnée.
    */
   public void deal(CollectionCartes cC, int n) {
      for (int i = 0; i < n; i++) {
         Carte carte = removeLast();
         cC.append(carte);
      }
   }
/**
    * Déplace toutes les cartes restantes vers la collection donnée.
    */
   public void dealAll(CollectionCartes cC) {
      int n = size();
      deal(cC, n);
   }

  /**
    * Intervertit les cartes aux index i et j.
    */
   public void swapAt(int i, int j) {
      append(cartes.get(i));
      cartes.set(i, cartes.get(j));
      cartes.set(j, removeLast());
   }

   /**
    * Permutez aléatoirement les cartes.
    */
   public void shuffle() {
      Random random = new Random();
      for (int i = size() - 1; i > 0; i--) {
         int j = random.nextInt(i);
         swapAt(i, j);
      }
   }

   public String toString() {
      return label + ": " + cartes.toString();
   }
  /**
    * Affiche l'étiquette et les cartes.
    */
   public void display() {
      System.out.println(label + ": ");
      for (Carte c: cartes){
         System.out.println(c);
      }
      System.out.println();
   }

    

}
