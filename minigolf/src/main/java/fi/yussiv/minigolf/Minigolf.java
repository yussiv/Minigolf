
package fi.yussiv.minigolf;

import fi.yussiv.minigolf.domain.LevelArea;
import fi.yussiv.minigolf.domain.Player;
import fi.yussiv.minigolf.domain.Position;

public class Minigolf {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println(" Welcome to Big League Golf - it's tremendous!");
        System.out.println("-----------------------------------------------\n");
        
        LevelArea area = new LevelArea(100, 100);
        area.setStart(new Position(50, 10));
        area.setTarget(new Position(30, 450));
        
        Game game = new Game(area);
        Player player = new Player();
        game.addPlayer(player);
        
        game.startGame();
        
        System.out.println("Golf Ball starting position " + player.getBall().getPosition());
        System.out.println("Target position " + area.getTarget() + "\n");
        System.out.print("Putting with force 10 in a  0 degree angle... ");
        game.excecutePutt(10, 0);
        System.out.print("Golf Ball end position " + player.getBall().getPosition());
        System.out.println(", game over=" + game.targetReached());
        System.out.print("Putting with force 30 in a -3 degree angle... ");
        game.excecutePutt(30, -3);
        System.out.print("Golf Ball end position " + player.getBall().getPosition());
        System.out.println(", game over=" + game.targetReached());
        System.out.print("Putting with force 50 in a -5 degree angle... ");
        game.excecutePutt(50, -5);
        System.out.print("Golf Ball end position " + player.getBall().getPosition());
        System.out.println(", game over=" + game.targetReached());
        System.out.print("Putting with force  2 in a 60 degree angle... ");
        game.excecutePutt(2, 60);
        System.out.print("Golf Ball end position " + player.getBall().getPosition());
        System.out.println(", game over=" + game.targetReached());
    }
}
