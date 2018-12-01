package Game.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener {
    private GameController gameController;
    public PauseListener(GameController gameController)
    {
        this.gameController = gameController;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.pauseGame();
    }
}
