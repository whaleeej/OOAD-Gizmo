package Game.Controller;

import Entrance.MainScene;
import Game.Model.SmashBox;
import Game.Model.Pipe;
import Game.View.GameRender;
import Game.View.GameScene;
import Game.View.GameToolbar;
import Physics.Controller.PhysicsEngineController;

import java.awt.*;

public class GameController {



    private GameRender gameRender = null;
    private MainScene mainScene = null;
    private GameScene gameScene = null;
    private GameToolbar gameToolbar = null;
    private PhysicsEngineController pc=null;
    //GameScene gs=null;
    public GameController(MainScene mainScene)
    {
        //Frame and Jpanel instantiate and layout
        this.mainScene = mainScene;
        gameScene = new GameScene();
        gameRender =new GameRender();
        gameToolbar = new GameToolbar();
        gameScene.setLayout(null);
        gameRender.setBounds(0,0,1101,801);
        gameToolbar.setBounds(1101,0,81,801);
        gameScene.add(gameRender);
        gameScene.add(gameToolbar);
        this.mainScene.addGameScene(gameScene);
        //Toggle View button to Listener in Controller
        setListeners();

        //StartGameControllerDemo
//        resetGameController();
//        initialGameWorld(200,0.1,0.1);

//        instantiateCompleted();
    }

    //For adding all listners
    private void setListeners()
    {
        //Intial button Listener action to perform logic operation
        //Avoid logic opetation
        gameToolbar.getBuildingButton().addActionListener(new BuildingListener(mainScene,this));
        gameToolbar.getPauseButton().addActionListener(new PauseListener(this));
        gameToolbar.getProceedButton().addActionListener(new ProceedListener(this));
        gameToolbar.getStartButton().addActionListener(new StartListener(this));
    }

    //Step 1.
    public void resetGameController()
    {
        pc=PhysicsEngineController.getPhysicsEngineController();
        pc.setRender(gameRender);
    }

    //Sterp 2
    public void initialGameWorld(double g,double u,double c)
    {
        if(pc==null) return;
        pc.initialGravity(g);
        pc.initialResistance(u,c);
        pc.initialWall(-1, 0, 1, 80);//Left
        pc.initialWall(0, 80, 110, 1);//Bottom
        pc.initialWall(110, 1, 1, 80);//Right
        pc.initialWall(0, -1, 110, 1);//Up

        //TODO::Change these intialrigid to the moethod you provide for Build Level
        //Edited by ceej
        //Eg. setBall(x,y,r,color)
        pc.initialBall(1, -10, -10, 0.92, 80, 3, 2, new Color(255, 0, 0));
        pc.initialBall(1, 20, 10, 0.92, 70, 3, 2, new Color(0, 255, 0));
        pc.initialBall(1, 10, 30, 0.92, 50.3, 10, 2, new Color(0, 0, 255));
        pc.initialBall(1, -10, -10, 0.92, 10, 8, 2, new Color(255, 0, 0));
        pc.initialBall(1, 20, 10, 0.92, 20, 3, 6, new Color(0, 255, 0));
        pc.initialBall(1, 10, 30, 0.92, 40, 30, 2, new Color(0, 0, 255));

        //Eg. setBox()
        pc.initialBox(0, -30, -10, 0.9, 70, 20, 2, 3, new Color(255, 255, 0));
        pc.initialBox(0, 30, 0, 0.9, 60, 20, 5, 4, new Color(255, 0, 255));
        pc.initialBox(0, -30, 20, 0.9, 100, 55, 4, 2, new Color(0, 255, 255));


        //Eg. setSmashBox
        //Edited by ceej
        pc.initialRigid(new SmashBox(80, 40,3,3));


        //Eg. SetTriangle
        pc.initialTriangle(10, 0, 0, 1, 50, 40,10 , 10, 2, new Color(87,145,4));

        //Eg. SetRotationRectangle
        pc.initialRotationRectangle(49, 55, 2, 15, false, 'z');
        pc.initialRotationRectangle(81, 55, 2, 15, true, 'x');

        //Edited by ceej
        //TODO: to create a new method to setPipe, provided for build layer
        new Pipe(5,20,10,30,30,4);

    }

    //Step 3.Instantiate Objects
    //TODO:: Added method for instantiation command in Build Layer
    //Demo 1
    public void setBall(double x,double y,double r,Color color)
    {
        pc.initialBall( 1, 0, 0, 0.92, x/10, y/10, r/10, color);
    }

    public void setBox(double x,double y,double width,double height,Color color)
    {
        pc.initialBox(5, 0, 0, 0.9,  x/10, y/10, width/10, height/10, color);
    }

    public void setTriangle(double x,double y,double len1,double len2,int type,Color color)
    {
        pc.initialTriangle( 10,0,0,1,x/10, y/10, len1/10, len2/10, type, color);
    }

    public void setpcRotationRectangle(double x,double y,double w,double l,boolean isLeft,char key)
    {
        pc.initialRotationRectangle(x/10, y/10, w/10, l/10, isLeft, key);
    }

    public void setSmashBox(double x_min, double y_min, double width, double height)
    {
        pc.initialRigid(new SmashBox(x_min,  y_min,  width,  height));
    }


    //Step4. Tell me Instantiation is completed
    //Show the first frame
    public void instantiateCompleted()
    {
        if(pc==null) return;
        startGame();
        pauseGame();
        proceedGame();
    }

    public void startGame()
    {
        if(pc==null) return;
        pc.startPhysicsRunning();
    }
    public void  pauseGame()
    {
        if(pc==null) return;
        pc.pausePhysicsRunning();
    }

    public void proceedGame()
    {
        if(pc==null) return;
        pc.proceedOneFrame();
    }

    public void destroyPhysicEngine()
    {
        if(pc==null) return;
        pc.resetPhysicEngine();
    }
}
