

import java.util.ArrayList;

public class CollectionCartesLogic extends CollectionCartes{
   
   private ArrayList<LogicCarte> LogicCarte;
   

   public CollectionCartesLogic(String label) {
      super(label);
      this.LogicCarte = new ArrayList<LogicCarte>();
   }
 /**
     * Retourne la carte avec l'index donné.
     */
  
   public LogicCarte getCardAt(int i) {
      return LogicCarte.get(i);
   }
  /**
     * Retourne la dernière carte.
     */

   public LogicCarte last() {
      int i = size() - 1;
      return getCardAt(i);
   }
     /**
     * Ajoute la carte donnée à la collection.
     */

   public void append(LogicCarte card) {
      LogicCarte.add(card);
   }

   /**
     * Supprime et retourne la carte avec l'index donné.
     */
   public LogicCarte removeAt(int i) {
      return LogicCarte.remove(i);
   }

   /**
     * Retire et retourne la dernière carte.
     */
   public LogicCarte removeLast() {
      int i = size() - 1;
      return removeAt(i);
   }

 /**
    * Retourne le nombre de cartes.
    */
   public int size() {
      return LogicCarte.size();
   }

      /**
     * Déplace n cartes de cette collection vers la collection donnée.
     */
   public void deal(CollectionCartesLogic c, int n) {
      for (int i = 0; i < n; i++) {
         LogicCarte card = removeLast();
         c.append(card);
      }
   }

    
    /**
     * Déplace toutes les cartes restantes vers la collection donnée.
     */
   public void dealAll(CollectionCartesLogic c) {
      int n = size();
      deal(c, n);
   }

   /**
    * Intervertit les cartes aux index i et j.
    */
   public void swapAt(int i, int j) {
      append(LogicCarte.get(i));
      LogicCarte.set(i, getCardAt(j));
      LogicCarte.set(j, removeLast());
   }

   public String toString() {
      return getLabel() + ": " + LogicCarte.toString();
   }

    /**
    * Imprime l'étiquette et les cartes.
    */
   
   public void display() {
      System.out.println(getLabel() + ": ");
      for (Carte c: LogicCarte){
         System.out.println(c);
      }
      System.out.println();
   }

}
