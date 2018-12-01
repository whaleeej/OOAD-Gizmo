package Game.Controller;

import Entrance.MainScene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingListener implements ActionListener
{
    private MainScene mainScene;
    private GameController gc;
    public BuildingListener(MainScene mainScene,GameController gc)
    {
        this.mainScene = mainScene;
        this.gc=gc;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mainScene.changeToBuildMode();
        gc.destroyPhysicEngine();
    }
}
