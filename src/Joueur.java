
public class Joueur {

   private String nom;
   private CollectionCartes main;


   public Joueur(String nom) {
      this.nom = nom;
      this.main = new CollectionCartes(nom);
   }

 /**
    * Obtient le nom du joueur.
    */
   public String getNom() {
      return nom;
   }
 /**
    * Obtient la main du joueur.
    */
   public CollectionCartes getMain() {
      return main;
   }

 /**
    * Calcule le score du joueur (points de pénalité).
    */
   public int score() {
      int sum = 0;
      for (int i = 0; i < getMain().size(); i++) {
         sum += getMain().getCardAt(i).score();
      }
      return sum;
   }

/**
    * Affiche la main du joueur.
    */
   public void display() {
      getMain().display();
   }
 /**
    * Affiche le nom du joueur et son score.
    */
  
   public void displayScore() {
      System.out.println(nom + " a " + score() + " points");
   }

}
