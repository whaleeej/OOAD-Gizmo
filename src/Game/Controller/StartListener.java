package Game.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {
    private GameController gameController;
    public StartListener(GameController gameController)
    {
        this.gameController = gameController;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.startGame();
    }
}
