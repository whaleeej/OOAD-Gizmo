package Game.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPauseListener implements ActionListener {
    private GameController gameController;
    public StartPauseListener(GameController gameController)
    {
        this.gameController = gameController;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameController.isRunning())
            gameController.pauseGame();
        else
            gameController.startGame();
    }
}
