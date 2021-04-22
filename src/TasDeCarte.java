
public class TasDeCarte extends CollectionCartesLogic {

   public TasDeCarte(String label) {
      super(label);
      initialize();
   }

/**
    * Construit un jeu standard de 52 cartes.
    */
   private void initialize(){
      for (int suit = 0; suit <= 3; suit++) {
         for (int rank = 1; rank <= 13; rank++) {
            append(new LogicCarte(rank, suit));
         }
      }
   }

}
