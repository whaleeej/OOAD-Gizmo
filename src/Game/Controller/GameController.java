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
import java.awt.event.KeyEvent;

public class GameController {



    private GameRender gameRender = null;
    private MainScene mainScene = null;
    private GameScene gameScene = null;
    private GameToolbar gameToolbar = null;
    private PhysicsEngineController pc=null;
    private boolean running;

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
        gameToolbar.setBounds(1101,0,120,801);
        gameScene.add(gameRender);
        gameScene.add(gameToolbar);
        this.mainScene.addGameScene(gameScene);
        running = false;
        //Toggle View button to Listener in Controller
        setListeners();
    }

    //For adding all listners
    private void setListeners()
    {
        //Intial button Listener action to perform logic operation
        //Avoid logic opetation
        gameToolbar.getBuildingButton().addActionListener(new BuildingListener(mainScene,this));
        gameToolbar.getProceedButton().addActionListener(new ProceedListener(this));
        StartPauseListener startPauseListener = new StartPauseListener(this);
        gameToolbar.getStartPauseButton().addActionListener(startPauseListener);
        //gameToolbar.getStartPauseButton().registerKeyboardAction(startPauseListener, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0),JComponent.WHEN_IN_FOCUSED_WINDOW);
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
        pc.initialWall(110, 0, 1, 80);//Right
        pc.initialWall(0, -1, 110, 1);//Up
        totlePoint=0;
        updateTotlePoint(0);
        running = false;
        gameToolbar.setRunning(running);

        //Eg. setBall(x,y,r,color)
//        setBall(0,0,800, 30, 20, new Color(255, 0, 0));
//        setBall(0,0,700, 30, 20, new Color(0, 255, 0));
//        setBall(0,0,503, 100,20, new Color(0, 0, 255));
//        setBall(0,0,100, 80, 20, new Color(255, 0, 0));
//        setBall(0,0, 200, 30, 30, new Color(0, 255, 0));
//        //Eg. setCircle
//        setCircle(700, 300, 40, new Color(0, 0, 255));
//        //Eg. setBox()
//        setBox(700, 200, 20, 30, new Color(255, 255, 0),true);
//        setBox(600, 200, 50, 40, new Color(255, 0, 255),true);
//        setBox(1000, 550, 40, 20, new Color(0, 255, 255),true);
//        //Eg. setAbsorber
//        setAbsorber(650, 700,30,30,1);
//        //Eg. SetTriangle
//        setTriangle(500, 400,100, 100, 2, new Color(87,145,4),true);
//        //Eg. SetPipe
//        setPipe(200,200,100,300,300,4);
//        //Eg. SetPolygon
//        Vector2[] buf=new Vector2[6];
//        buf[0]=new Vector2(700,200);
//        buf[1]=new Vector2(750,200);
//        buf[2]=new Vector2(780,240);
//        buf[3]=new Vector2(750,280);
//        buf[4]=new Vector2(700,280);
//        buf[5]=new Vector2(670,240);
//        setPolygon(buf,new Color(87,125,125),true);
//        //Eg. SetRotationRectangle
//        setRotationRectangle(490, 550, 20, 150, false, 'z',new Color(255,0,0));
//        setRotationRectangle(290, 550, 20, 150, false, 'a');
//        setRotationRectangle(810, 550, 20, 150, true, 'x');
    }

    //Step 3.Instantiate Objects
    //Demo 1

    //Please set RotationRectangle at last
    public void setRotationRectangle(double x,double y,double w,double l,boolean isLeft,char key,Color color)
    {
        pc.initialRotationRectangle(x/10, y/10, w/10, l/10, isLeft, key,color);
    }

    public void setBall(double v_x,double v_y,double x,double y,double r,Color color)
    {
        pc.initialBall( 1, v_x/10.0, v_y/10.0, 0.95, x/10, y/10, r/10, color);
    }

    public void setCircle(double x,double y,double r,Color color)
    {
        pc.initialCircle( 0, 0, 0, 0.93, x/10, y/10, r/10, color);
    }

    public void setBox(double x,double y,double width,double height,Color color,boolean isMovable)
    {
        double m=isMovable?10:0;
        pc.initialBox(m, 0, 0, 1.0,  x/10, y/10, width/10, height/10, color);
    }

    public void setTriangle(double x,double y,double len1,double len2,int type,Color color,boolean isMovable)
    {
        double m=isMovable?10:0;
        pc.initialTriangle( m,0,0,1.0,x/10, y/10, len1/10, len2/10, type, color);
    }

    public void setPolygon(Vector2[] buf,Color color,boolean isMovable)
    {
        Vector2 buf2[]=new Vector2[buf.length];
        for(int i=0;i<buf.length;i++)
        {
            buf2[i]=new Vector2(buf[i]);
            buf2[i].x/=10;
            buf2[i].y/=10;
        }
        double m=isMovable?10:0;
        pc.initialPolygon(m,0,0,1,buf2,color);
    }

    public void setAbsorber(double x_min, double y_min, double width, double height, Color color)
    {
        pc.initialRigid(new Absorber(this,x_min/10,  y_min/10,  width/10,  height/10,1,color));
    }

    public void setPipe(double x, double y,double width, double len1,double len2,int type,Color color1, Color color2)
    {
        new Pipe(x/10, y/10,width/10, len1/10, len2/10, type,color1,color2);
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
        running = true;
        gameToolbar.setRunning(running);
    }
    public void  pauseGame()
    {
        if(pc==null) return;
        pc.pausePhysicsRunning();
        running = false;
        gameToolbar.setRunning(running);
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

    public boolean isRunning()
    {
        return running;
    }

    //Change the value of totlePoint
    public void updateTotlePoint(int scale) {
        totlePoint += scale;
        pointListener.onUpdateEvent(totlePoint);
    }
}
