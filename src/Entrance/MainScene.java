package Entrance;

import Build.Controller.BuildController;
import Build.View.BuildScene;
import Game.Controller.GameController;
import Game.View.GameScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScene extends JFrame
{
    private String mode;
    private JMenu menu;
    private JPanel mainpanel;
    private BuildController buildController;
    private GameController gameController;

    public MainScene()
    {
        super("BuildScene");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1239, 871);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.green);
        mainpanel = new JPanel();
        this.setContentPane(mainpanel);
        System.out.println(mainpanel.getWidth()+" "+ mainpanel.getHeight());
        mainpanel.setLayout(new CardLayout());
        initialization();
        this.setVisible(true);
    }

    public void initialization()
    {
        mode = "Build";
        buildController = new BuildController(this);
        gameController = new GameController(this);

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
        CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
        cardLayout.show(mainpanel,mode);
    }

    public void changeToGameMode()
    {
        mode = "Game";
        setTitle("GameScene");
        //menu.setEnabled(false);
        CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
        cardLayout.show(mainpanel,mode);
    }

    public void addBuildScene(BuildScene buildScene)
    {
        mainpanel.add(buildScene,"Build");
    }

    public void addGameScene(GameScene gameScene)
    {
        mainpanel.add(gameScene,"Game");
    }

    public GameController getGameController() {
        return gameController;
    }

    public BuildController getBuildController()
    {
        return buildController;
    }
}
