package Game.Controller;

import Entrance.MainScene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingListener implements ActionListener
{
    private MainScene mainScene;
    public BuildingListener(MainScene mainScene)
    {
        this.mainScene = mainScene;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mainScene.changeToBuildMode();
    }
}
