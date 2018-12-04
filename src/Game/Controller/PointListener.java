package Game.Controller;

import Game.Model.Absorber;

import java.util.Vector;

public class PointListener {
    private GameController gameController;
    public PointListener(GameController gameController)
    {
        this.gameController = gameController;
    }

    public void updatePoint(Absorber ab)
    {
        //Update the value
        ab.point+=ab.scale;
        gameController.updateTotlePoint(ab.scale);
        //Update the text
        gameController.updateTotlePointText();
    }

}