package Game;

import Physics.Controller.PhysicsEngineController;

import java.awt.*;

public class GameController {
    GameRender gr=null;
    GameScene gs=null;
    PhysicsEngineController pc=null;
    public GameController() {
        gs = new GameScene();//JFrame
        gr = new GameRender();//Jpanel
        gs.add(gr);
        gs.setVisible(true);
        //bind gameRneder(Interface Ehysics Eender) to Physics Engine
        pc = new PhysicsEngineController(gr);
        //initial objs
        initialGameWorld();
        //start running
        pc.startPhysicsRunning();
    }

    //To initial the world add objs into physics engine and set gravity
    public void initialGameWorld()
    {
        pc.initialGravity(1000);

        pc.initialWall(0, 10, 10, 640);//Left
        pc.initialWall(0, 650, 1276, 10);//Bottom
        pc.initialWall(1266, 10, 10, 640);//Right
        pc.initialWall(0, 0, 1276, 10);//Up

//        pc.initialBall(1, -500, 500, 0.9, 1000, 30, 20, new Color(255, 0, 0));
//        pc.initialBall(1, 200, 100, 0.9, 700, 30, 20, new Color(0, 255, 0));
        pc.initialBall(1, 0, 30, 0.9, 503, 100, 20, new Color(0, 0, 255));
//
//        pc.initialBox(1, 0, 0, 0.7, 500, 200, 20, 30, new Color(255, 255, 0));
//        pc.initialBox(1, 100, 0, 0.7, 200, 200, 50, 40, new Color(255, 0, 255));
//        pc.initialBox(1, 0, 0, 0.7, 1000, 550, 40, 20, new Color(0, 255, 255));

        pc.initialTriangle(0, 0, 0, 1, 500, 500,50 , 100, 2, new Color(87,145,4));

//        pc.initialRotationRectangle(540, 500, 20, 100, false, 'z');
//        pc.initialRotationRectangle(760, 500, 20, 100, true, 'x');
    }
}
