package Entrance;

import Build.Controller.BuildController;
import Build.View.BuildRender;
import Game.GameController;
import Game.GameRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScene extends JFrame
{
    private String mode;
    private JMenu menu;
    private BuildRender buildRender;
    private GameRender gameRender;

    public MainScene()
    {
        super("BuildScene");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1300, 720);
        this.setLocationRelativeTo(null);
        initialization();

        this.setVisible(true);
    }

    public void initialization()
    {
        mode = "Build";
        BuildController buildController = new BuildController(this);
        GameController gameController = new GameController(this);
        setMenu();
        changeToBuildMode();
    }

    private void setMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("File");

        JMenuItem newBoard = new JMenuItem("New");
        newBoard.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                changeToGameMode();
            }
        });
        //newBoard.addActionListener(listeners.get("nBL"));
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                changeToBuildMode();
            }
        });
        //load.addActionListener(listeners.get("lL"));
        JMenuItem save = new JMenuItem("Save");
        //save.addActionListener(listeners.get("sL"));

        menu.add(newBoard);
        menu.add(load);
        menu.add(save);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public void changeToBuildMode()
    {
        mode = "Build";
        setTitle("BuildScene");
        menu.setEnabled(true);
        //setContentPane(buildRender);
        gameRender.setVisible(false);
        gameRender.setEnabled(false);
        buildRender.setVisible(true);
        buildRender.setEnabled(true);
    }

    public void changeToGameMode()
    {
        mode = "Game";
        setTitle("GameScene");
        //menu.setEnabled(false);
        //setContentPane(gameRender);
        buildRender.setEnabled(false);
        buildRender.setVisible(false);
        gameRender.setVisible(true);
        gameRender.setEnabled(true);
    }

    public void setBuildRender(BuildRender buildRender)
    {
        this.buildRender = buildRender;
        add(buildRender);
    }

    public void setGameRender(GameRender gameRender)
    {
        this.gameRender = gameRender;
        add(gameRender);
    }
}
