
public class Carte implements Comparable<Carte>{

  public static final String[] HAUTEUR = {
      null, "Ac", "2", "3", "4", "5", "6", "7",
      "8", "9", "10", "Valet", "Dame", "Roi"};

   public static final String[] COULEUR = {
      "Pique", "Carreau", "Coeur", "Trefle"};


   private final int couleur;
   private final int hauteur;

  
 /**
    * Construit une carte de la hauteur et couleur donnés.
    */


   public Carte( int hauteur,int couleur) {
      this.couleur = couleur;
      this.hauteur = hauteur;
   }
/**
    * Retourne un entier négatif si cette carte vient avant
    * la carte donnée, zéro si les deux cartes sont egaux, ou
    * un nombre entier positif si cette carte vient après la carte.
    */

   public int compareTo(Carte c) {
      if (couleur < c.couleur) {
         return -1;
      }
      if (couleur > c.couleur) {
         return 1;
      }
      if (hauteur < c.hauteur) {
         return -1;
      }
      if (hauteur > c.hauteur) {
         return 1;
      }
      return 0;
   }

    /**
    * Retourne vrai si la carte donnée a le même couleur et la même hauteur.
    */
   public boolean equals(Carte c) {
      return hauteur == c.hauteur
         && couleur == c.couleur;
   }

   public int getHauteur() {
      return hauteur;
   }

  
   public int getCouleur() {
      return couleur;
   }

     /**
    * Retourne le score de la carte
   */
   public int score(){
      return hauteur;
   }

   
  @Override
   public String toString() {
      return COULEUR[couleur] + " de "+HAUTEUR[hauteur];
   }

}
