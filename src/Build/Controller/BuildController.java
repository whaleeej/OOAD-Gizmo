package Build.Controller;

import Build.View.BuildRender;
import Build.View.BuildScene;
import Build.View.BuildToolbar;
import Entrance.MainScene;

import javax.swing.*;
import java.awt.*;

public class BuildController
{
    private BuildRender buildRender;
    private MainScene mainScene;
    private BuildScene buildScene;
    private BuildToolbar buildToolbar;

    public BuildController(MainScene mainScene)
    {
        this.mainScene = mainScene;
        buildRender = new BuildRender();
        buildToolbar = new BuildToolbar();
        buildScene = new BuildScene();
        buildScene.setLayout(null);
        buildRender.setBounds(0,0,1101,801);
        buildToolbar.setBounds(1101,0,81,801);
        buildScene.add(buildRender);
        buildScene.add(buildToolbar);
        setListeners();
        mainScene.addBuildScene(buildScene);
    }

    private void setListeners()
    {
        buildToolbar.getGamingButton().addActionListener(new GamingListener(mainScene));
    }
}
