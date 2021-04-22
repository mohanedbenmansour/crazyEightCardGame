
public class LogicJoueur extends Joueur{

   private CollectionCartesLogic mainCarte;

   public LogicJoueur(String nom){
      super(nom);
      mainCarte = new CollectionCartesLogic(nom);
   }
   
/**
    * Obtient la main du joueur.
    */
   public CollectionCartesLogic getHand(){
      return mainCarte;
   }
/**
    * Calcule le score du joueur (points de pénalité).
    */
   public int score() {
      int sum = 0;
      for (int i = 0; i < getHand().size(); i++) {
         sum += getHand().getCardAt(i).score();
      }
      return sum;
   }

 /**
    * Affiche la main du joueur.
    */
   public void display() {
      getHand().display();
   }

/**
    * Affiche le nom du joueur et son score.
    */
   public void displayScore() {
      System.out.println(getNom() + " a " + score() + " points");
   }
   /**
    * Retirer et retourner une carte légale de la main du joueur.
    */
   public LogicCarte play(Partie partie, LogicCarte prev) {
      LogicCarte carte = searchForMatch(prev);
      if (carte == null) {
         carte = drawForMatch(partie, prev);
      }
      return carte;
   }
/**
    * Cherche une carte correspondante dans la main du joueur.
    */
   
   public LogicCarte searchForMatch(LogicCarte prev) {
      for (int i = 0; i < getHand().size(); i++) {
         LogicCarte carte = getHand().getCardAt(i);
         if (carte.matches(prev)) {
            return getHand().removeAt(i);
         }
      }
      return null;
   }

  /**
    * Tire des cartes jusqu'à ce qu'une correspondance soit trouvée.
    */
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