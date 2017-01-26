package fi.yussiv.minigolf.domain;

public class Player {
    private Ball ball;

    public Player() {
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
