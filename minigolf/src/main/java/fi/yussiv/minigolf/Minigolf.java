package fi.yussiv.minigolf;

/**
 * Main class. Only used to start the game.
 */
public class Minigolf {

    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println(" Welcome to Big League Golf - it's tremendous!");
        System.out.println("-----------------------------------------------\n");

        Game game = new Game();
        game.startGame();
    }
}
