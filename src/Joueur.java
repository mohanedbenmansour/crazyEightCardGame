
public class Joueur {

   private String nom;
   private CollectionCartes main;


   public Joueur(String nom) {
      this.nom = nom;
      this.main = new CollectionCartes(nom);
   }


   public String getNom() {
      return nom;
   }

   public CollectionCartes getMain() {
      return main;
   }


   public int score() {
      int sum = 0;
      for (int i = 0; i < getMain().size(); i++) {
         sum += getMain().getCardAt(i).score();
      }
      return sum;
   }


   public void display() {
      getMain().display();
   }

  
   public void displayScore() {
      System.out.println(nom + " a " + score() + " points");
   }

}
