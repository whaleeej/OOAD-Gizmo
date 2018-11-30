package Build.Controller;

import Build.View.BuildRender;
import Entrance.MainScene;

import javax.swing.*;

public class BuildController
{
    private BuildRender buildRender = null;
    private MainScene mainScene = null;

    public BuildController(MainScene mainScene)
    {
        this.mainScene = mainScene;
        buildRender = new BuildRender();
        this.mainScene.setBuildRender(buildRender);
    }
}
