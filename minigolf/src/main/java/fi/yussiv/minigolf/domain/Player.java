package fi.yussiv.minigolf.domain;

/**
 * Class containing information about the player.
 */
public class Player {
    private Ball ball;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
