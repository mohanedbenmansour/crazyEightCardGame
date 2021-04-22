
public class LogicJoueur extends Joueur{

   private CollectionCartesLogic mainCarte;

   public LogicJoueur(String nom){
      super(nom);
      mainCarte = new CollectionCartesLogic(nom);
   }
   

   public CollectionCartesLogic getHand(){
      return mainCarte;
   }

   public int score() {
      int sum = 0;
      for (int i = 0; i < getHand().size(); i++) {
         sum += getHand().getCardAt(i).score();
      }
      return sum;
   }


   public void display() {
      getHand().display();
   }


   public void displayScore() {
      System.out.println(getNom() + " a " + score() + " points");
   }

   public LogicCarte play(Partie partie, LogicCarte prev) {
      LogicCarte carte = searchForMatch(prev);
      if (carte == null) {
         carte = drawForMatch(partie, prev);
      }
      return carte;
   }

   
   public LogicCarte searchForMatch(LogicCarte prev) {
      for (int i = 0; i < getHand().size(); i++) {
         LogicCarte carte = getHand().getCardAt(i);
         if (carte.matches(prev)) {
            return getHand().removeAt(i);
         }
      }
      return null;
   }

 
   public LogicCarte drawForMatch(Partie partie, LogicCarte prev) {
      while (true) {
         LogicCarte card = partie.draw();
         System.out.println(getNom() + " draws " + card);
         if (card.matches(prev)) {
            return card;
         }
         getHand().append(card);
      }
   }
}