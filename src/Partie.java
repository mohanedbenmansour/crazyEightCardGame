
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Partie {

 /**
    * Test avec `players` pour un nombre de fois
    */
   public static void test(ArrayList<LogicJoueur> joueurs, int count){
      int total = joueurs.size();   
      int[] scores = new int[total];
      int[] board = new int[total];
      for (int round=0;round<count;round++){
         Partie newGame = new Partie(joueurs);
         int[] result = newGame.play();
         for (int i = 0;i<total;i++){ scores[i] += result[i]; }
         board[newGame.getWinner()] += 1;
      }
      System.out.printf("\n\n----[nombre de test(s) :  %d]----\n", count);
      for (int i=0;i<total;i++){
         System.out.printf("joueur %s: %.2f pour cent, a perdu: %d points\n", joueurs.get(i).getNom(), board[i]*100/(double)count, scores[i]);
      }
   }

   public static boolean debug = false;
   private Scanner in;
   private Random random;
   private ArrayList<LogicJoueur> joueurs;
   private CollectionCartesLogic pioche;
   private CollectionCartesLogic defausse;
   private int current;
   private int winner;

      /**
    * Initialise l'état du jeu.
    */
   public Partie(ArrayList<LogicJoueur> players) {
      TasDeCarte deck = new TasDeCarte("Tas");
      deck.shuffle();
   
    
      int handSize = 5;
      this.joueurs = players;
      for (int i=0;i<players.size();i++)
         deck.deal(players.get(i).getHand(), handSize);
   
      random = new Random();
      current = random.nextInt(players.size());
   // retournez une carte face visible
      defausse = new CollectionCartesLogic("Defausse");
      deck.deal(defausse, 1);
         // Posez le reste du cartes face cachée.

      pioche = new CollectionCartesLogic("Pioche");
      deck.dealAll(pioche);
     // créer le scanner que nous utiliserons pour attendre l'utilisateur
      in = new Scanner(System.in);
   }

    /**
    * Joue le jeu.
    */

   public int[] play() {
      LogicJoueur joueur= joueurs.get(current);
            // continuer à jouer jusqu'à ce qu'il y ait un gagnant
      while (!isDone()) {
         displayState();
         waitForUser();
         takeTurn(joueur);
         joueur = nextPlayer();
      }
            // afficher le score final

      int[] result = new int[joueurs.size()];
      for (int i = 0;i<joueurs.size();i++){
         LogicJoueur user = joueurs.get(i);
         user.displayScore();
         result[i] = user.score();
      }
      winner = previousIndex();
      return result;
   }

 /**
    * Retourne vrai si l'une des deux mains est vide.
    */
   public boolean isDone() {
      return joueurs.get(previousIndex()).getHand().isEmpty();
   }
 /**
    * Affiche l'état du jeu.
    */

   public void displayState() {
      for (LogicJoueur user: joueurs){
         user.display();
      }
      pioche.display();
      System.out.print("pioche : ");
      System.out.println(pioche.size() + " cartes");
   }

   /**
    * Attend que l'utilisateur appuie sur la touche Entrée.
    */
   public void waitForUser() {
      if (!debug){ in.nextLine(); }
   }

   /**
    * Un joueur prend son tour.
    */
   public void takeTurn(LogicJoueur player) {
      LogicCarte prev = defausse.last();
      LogicCarte next = player.play(this, prev);
      defausse.append(next);
   
      System.out.println(player.getNom() + " joue " + next);
      System.out.println();
   }

 /**
    * Obtenez l'index du joueur actuel
    */
   public int getCurrent(){
      return current;
   }
 /**
    * Obtenir l'indice du gagnant
    */
   public int getWinner(){
      return winner;
   }

   /**
    * Augmente et retourne l'indice actuel
    */
   public int increaseIndex(){
      if (++current == joueurs.size()) { current = 0; }
      return current;
   }
  /**
    * Passez au joueur suivant
    */
  
   public LogicJoueur nextPlayer() {
      return joueurs.get(increaseIndex());
   }

   /**
    * Obtenir l'index du joueur précédent
    */
   public int previousIndex() {
      if (current == 0){
         return joueurs.size() - 1;
      }
      return current - 1;
   }
/**
    * Retourne une carte de la pile de tirage.
    */

   public LogicCarte draw() {
      if (pioche.isEmpty()) {
         reshuffle();
      }
      return pioche.removeLast();
   } 
  /**
    * Déplace les cartes de la défausse vers la pioche et les mélange.
    */
   public void reshuffle() {
      LogicCarte prev = defausse.removeLast();
   
      defausse.dealAll(pioche);
   
      defausse.append(prev);
   
      pioche.shuffle();
   }
}
