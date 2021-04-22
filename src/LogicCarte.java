
public class LogicCarte extends Carte{

   public LogicCarte(int couleur, int hauteur) {
      super(couleur, hauteur);
   }
    /**
    * Retourne "vrai" si la règle des huit fous correspond à "ça".
    * Sinon `false`.
   */

   public boolean matches(LogicCarte t){
      if (getCouleur() == t.getCouleur()) {
         return true;
      }
      if (getHauteur() == t.getHauteur()) {
         return true;
      }
      if (getHauteur() == 8) {
         return true;
      }
      return false;
   }

/**
    * Retourne le score de la carte
   */
   public int score(){
      int rank = getHauteur();
      if (rank == 8) {
         return -20;
      } 
      else if (rank > 10) {
         return -10;
      } 
      else {
         return -rank;
      }
   }
}