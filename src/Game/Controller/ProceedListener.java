package Game.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProceedListener implements ActionListener {
    private GameController gameController;
    public ProceedListener(GameController gameController)
    {
        this.gameController = gameController;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.proceedGame();
    }
}
