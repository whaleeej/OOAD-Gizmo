package Build.Controller;

import Entrance.MainScene;
import Game.Controller.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamingListener implements ActionListener
{
    private MainScene mainScene;
    public GamingListener(MainScene mainScene)
    {
        this.mainScene = mainScene;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mainScene.changeToGameMode();
        GameController gameController = mainScene.getGameController();
        gameController.resetGameController();
        gameController.initialGameWorld(200,0.1,0.05);
        gameController.instantiateCompleted();
    }
}
