package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;
import Build.Model.Setting;
import Build.View.BuildRender;
import Build.View.BuildScene;
import Build.View.BuildToolbar;
import Entrance.MainScene;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static Build.Controller.BuildController.Command.*;

public class BuildController
{
    private BuildRender buildRender;
    private MainScene mainScene;
    private BuildScene buildScene;
    private BuildToolbar buildToolbar;
    private Gizmo chosenGizmo;
    public static enum Command {Add, Choose,Bind};
    private Command command = Choose;
    private Setting setting;

    public BuildController(MainScene mainScene)
    {
        this.mainScene = mainScene;
        setting = new Setting(10,1,1);
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
        //File
        mainScene.getNewBoard().addActionListener(new NewBoardListener(this));

        //buildToolbar set Listeners
        //Shape and Color
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
        //operation
        buildToolbar.getChooseButton().addActionListener(new ChooseListener(this));
        OperationListener operationListener = new OperationListener(this);
        buildToolbar.getRotateRightButton().addActionListener(operationListener);
        buildToolbar.getRotateRightButton().registerKeyboardAction(operationListener,"RotateRight",KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        buildToolbar.getRotateLeftButton().addActionListener(operationListener);
        buildToolbar.getRotateLeftButton().registerKeyboardAction(operationListener,"RotateLeft",KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        buildToolbar.getDeleteButton().addActionListener(operationListener);
        buildToolbar.getDeleteButton().registerKeyboardAction(operationListener,"Delete",KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        buildToolbar.getLargeButton().addActionListener(operationListener);
        buildToolbar.getLargeButton().registerKeyboardAction(operationListener,"+",KeyStroke.getKeyStroke('+'),JComponent.WHEN_IN_FOCUSED_WINDOW);
        buildToolbar.getMovableButton().addActionListener(operationListener);
        buildToolbar.getMovableButton().registerKeyboardAction(operationListener,"Movable",KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        buildToolbar.getSmallButton().addActionListener(operationListener);
        buildToolbar.getSmallButton().registerKeyboardAction(operationListener,"-",KeyStroke.getKeyStroke('-'),JComponent.WHEN_IN_FOCUSED_WINDOW);
        buildToolbar.getBindButton().addActionListener(operationListener);
        buildToolbar.getBindButton().addKeyListener(new BindListener(this));
        //setting
        SettingListener settingListener = new SettingListener(this);
        buildToolbar.getGravityButton().addActionListener(settingListener);
        buildToolbar.getGravitySlider().addChangeListener(settingListener);
        buildToolbar.getFrictionButton().addActionListener(settingListener);
        buildToolbar.getFrictionSlider().addChangeListener(settingListener);
        buildToolbar.getAirFrictionButton().addActionListener(settingListener);
        buildToolbar.getAirFrictionSlider().addChangeListener(settingListener);
        //run
        buildToolbar.getGamingButton().addActionListener(new GamingListener(mainScene));

        //buildRender set Listeners
        GridListenner gridListenner = new GridListenner(this);
        buildRender.addMouseListener(gridListenner);
        buildRender.addMouseMotionListener(gridListenner);
    }

    public Setting getSetting()
    {
        return setting;
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
