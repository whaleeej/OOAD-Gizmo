package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;
import Build.View.BuildRender;
import Build.View.BuildScene;
import Build.View.BuildToolbar;
import Entrance.MainScene;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.ArrayList;

import static Build.Controller.BuildController.Command.*;

public class BuildController
{
    private BuildRender buildRender;
    private MainScene mainScene;
    private BuildScene buildScene;
    private BuildToolbar buildToolbar;
    private Gizmo chosenGizmo;
    public static enum Command {Add, Choose};
    private Command command = Choose;

    public BuildController(MainScene mainScene)
    {
        this.mainScene = mainScene;
        buildRender = new BuildRender(this);
        buildToolbar = new BuildToolbar();
        buildScene = new BuildScene();
        buildScene.setLayout(null);
        buildRender.setBounds(0,0,1101,801);
        buildToolbar.setBounds(1101,0,120,801);
        buildScene.add(buildRender);
        buildScene.add(buildToolbar);
        setListeners();
        mainScene.addBuildScene(buildScene);
    }

    private void setListeners()
    {
        //buildToolbar set Listeners
        buildToolbar.getGamingButton().addActionListener(new GamingListener(mainScene));
        ShapeListener shapeListener = new ShapeListener(this);
        for(JButton jButton : buildToolbar.getShapeButtons())
        {
            jButton.addActionListener(shapeListener);
        }
        ColorListener colorListener = new ColorListener(this);
        for(JButton jButton : buildToolbar.getColorButtons())
        {
            jButton.addActionListener(colorListener);
        }
        buildToolbar.getChooseButton().addActionListener(new ChooseListener(this));
        OperationListener operationListener = new OperationListener(this);
        buildToolbar.getRotateButton().addActionListener(operationListener);
        buildToolbar.getDeleteButton().addActionListener(operationListener);
        buildToolbar.getLargeButton().addActionListener(operationListener);
        buildToolbar.getMovableButton().addActionListener(operationListener);
        buildToolbar.getSmallButton().addActionListener(operationListener);

        //buildRender set Listeners
        GridListenner gridListenner = new GridListenner(this);
        buildRender.addMouseListener(gridListenner);
        buildRender.addMouseMotionListener(gridListenner);
    }

    public BuildRender getBuildRender()
    {
        return buildRender;
    }

    public Gizmo getChosenGizmo()
    {
        return chosenGizmo;
    }

    public void setChosenGizmo(Gizmo chosenGizmo)
    {
        this.chosenGizmo = chosenGizmo;
    }

    public MainScene getMainScene()
    {
        return mainScene;
    }

    public Command getCommand()
    {
        return command;
    }

    public void setCommand(Command command)
    {
        this.command = command;
    }

    public BuildToolbar getBuildToolbar()
    {
        return buildToolbar;
    }
}
