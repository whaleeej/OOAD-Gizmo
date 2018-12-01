package Build.Controller;

import Entrance.MainScene;

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
    }
}
