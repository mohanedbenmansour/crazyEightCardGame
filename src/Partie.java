
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Partie {

 
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

   
   public Partie(ArrayList<LogicJoueur> players) {
      TasDeCarte deck = new TasDeCarte("Tas");
      deck.shuffle();
   
    
      int handSize = 5;
      this.joueurs = players;
      for (int i=0;i<players.size();i++)
         deck.deal(players.get(i).getHand(), handSize);
   
      random = new Random();
      current = random.nextInt(players.size());
   
      defausse = new CollectionCartesLogic("Defausse");
      deck.deal(defausse, 1);
   
      pioche = new CollectionCartesLogic("Pioche");
      deck.dealAll(pioche);
   
      in = new Scanner(System.in);
   }

  
   public int[] play() {
      LogicJoueur joueur= joueurs.get(current);
      
      while (!isDone()) {
         displayState();
         waitForUser();
         takeTurn(joueur);
         joueur = nextPlayer();
      }
   
      int[] result = new int[joueurs.size()];
      for (int i = 0;i<joueurs.size();i++){
         LogicJoueur user = joueurs.get(i);
         user.displayScore();
         result[i] = user.score();
      }
      winner = previousIndex();
      return result;
   }

  
   public boolean isDone() {
      return joueurs.get(previousIndex()).getHand().isEmpty();
   }

   
   public void displayState() {
      for (LogicJoueur user: joueurs){
         user.display();
      }
      pioche.display();
      System.out.print("pioche : ");
      System.out.println(pioche.size() + " cartes");
   }

 
   public void waitForUser() {
      if (!debug){ in.nextLine(); }
   }

  
   public void takeTurn(LogicJoueur player) {
      LogicCarte prev = defausse.last();
      LogicCarte next = player.play(this, prev);
      defausse.append(next);
   
      System.out.println(player.getNom() + " joue " + next);
      System.out.println();
   }

 
   public int getCurrent(){
      return current;
   }

   public int getWinner(){
      return winner;
   }

   
   public int increaseIndex(){
      if (++current == joueurs.size()) { current = 0; }
      return current;
   }

  
   public LogicJoueur nextPlayer() {
      return joueurs.get(increaseIndex());
   }

  
   public int previousIndex() {
      if (current == 0){
         return joueurs.size() - 1;
      }
      return current - 1;
   }


   public LogicCarte draw() {
      if (pioche.isEmpty()) {
         reshuffle();
      }
      return pioche.removeLast();
   } 

   public void reshuffle() {
      LogicCarte prev = defausse.removeLast();
   
      defausse.dealAll(pioche);
   
      defausse.append(prev);
   
      pioche.shuffle();
   }
}
