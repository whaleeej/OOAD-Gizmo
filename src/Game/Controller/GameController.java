package Game.Controller;

import Entrance.MainScene;
import Game.Model.Absorber;
import Game.Model.Pipe;
import Game.View.GameRender;
import Game.View.GameScene;
import Game.View.GameToolbar;
import Physics.Controller.PhysicsEngineController;
import Physics.Model.Computation.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GameController {



    private GameRender gameRender = null;
    private MainScene mainScene = null;
    private GameScene gameScene = null;
    private GameToolbar gameToolbar = null;
    private PhysicsEngineController pc=null;

    private int totlePoint=0;
    //Listener lists
    PointListener pointListener;

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
        pointListener=new PointListener(gameToolbar.getPointText());
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
        totlePoint=0;

//        //Eg. setBall(x,y,r,color)
//        pc.initialBall(1, -10, -10, 0.92, 80, 3, 2, new Color(255, 0, 0));
//        pc.initialBall(1, 20, 10, 0.92, 70, 3, 2, new Color(0, 255, 0));
//        pc.initialBall(1, 10, 30, 0.92, 50.3, 10, 2, new Color(0, 0, 255));
//        pc.initialBall(1, -10, -10, 0.92, 10, 8, 2, new Color(255, 0, 0));
//        pc.initialBall(1, 20, 10, 0.92, 20, 3, 6, new Color(0, 255, 0));
//        pc.initialBall(1, 10, 30, 0.92, 40, 30, 2, new Color(0, 0, 255));
//        //Eg. setBox()
//        pc.initialBox(0, -30, -10, 0.9, 70, 20, 2, 3, new Color(255, 255, 0));
//        pc.initialBox(0, 30, 0, 0.9, 60, 20, 5, 4, new Color(255, 0, 255));
//        pc.initialBox(0, -30, 20, 0.9, 100, 55, 4, 2, new Color(0, 255, 255));
//        //Eg. setSmashBox
//        pc.initialRigid(new Absorber(this,65, 70,3,3,1));
//        //Eg. SetTriangle
//        pc.initialTriangle(10, 0, 0, 1, 50, 40,10 , 10, 2, new Color(87,145,4));
//        //Eg. SetRotationRectangle
//        pc.initialRotationRectangle(49, 55, 2, 15, false, 'z');
//        pc.initialRotationRectangle(81, 55, 2, 15, true, 'x');
//        new Pipe(20,20,10,30,30,4);
    }

    //Step 3.Instantiate Objects
    //Demo 1
    public void setBall(double x,double y,double r,Color color)
    {
        pc.initialBall( 1, 0, 0, 0.93, x/10, y/10, r/10, color);
    }

    public void setBox(double x,double y,double width,double height,Color color,boolean isMovable)
    {
        double m=isMovable?10:0;
        pc.initialBox(m, 0, 0, 1.0,  x/10, y/10, width/10, height/10, color);
    }

    public void setTriangle(double x,double y,double len1,double len2,int type,Color color,boolean isMovable)
    {
        double m=isMovable?10:0;
        pc.initialTriangle( 10,0,0,1.0,x/10, y/10, len1/10, len2/10, type, color);
    }

    public void setPolygon(Vector2[] buf,Color color,boolean isMovable)
    {
        double m=isMovable?10:0;
        pc.initialPolygon(m,0,0,1,buf,color);
    }

    public void setpcRotationRectangle(double x,double y,double w,double l,boolean isLeft,char key)
    {
        pc.initialRotationRectangle(x/10, y/10, w/10, l/10, isLeft, key);
    }

    public void setAbsorber(double x_min, double y_min, double width, double height,int scale)
    {
        pc.initialRigid(new Absorber(this,x_min/10,  y_min/10,  width/10,  height/10,1));
    }

    public void setPipe(double x, double y,double width, double len1,double len2,int type)
    {
        new Pipe(x, y, width/10, len1/10, len2/10, type);
    }

    //Step4. Tell me Instantiation is completed
    //Show the first frame
    public void instantiateCompleted()
    {
        if(pc==null) return;
        pc.readyPhysicsRunning();
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



    //Change the value of totlePoint
    public void updateTotlePoint(int scale) {
        totlePoint += scale;
        pointListener.onUpdateEvent(totlePoint);
    }



}
