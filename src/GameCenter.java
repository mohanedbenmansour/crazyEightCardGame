

import java.util.ArrayList;

public class GameCenter{
   public static void main(String args[]){
      Partie.debug = true;
      int count = 2;
      ArrayList<LogicJoueur> players = new ArrayList<>();
      players.add(new LogicJoueur("test"));
      players.add(new LogicJoueur("Random"));
      players.add(new LogicJoueur("mohaned"));

      Partie.test(players, count);
   }
}