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
        pc.initialGravity(100);

        pc.initialWall(0, 1, 1, 64);//Left
        pc.initialWall(0, 65, 127.6, 1);//Bottom
        pc.initialWall(126.6, 1, 1, 64);//Right
        pc.initialWall(0, 0, 127.6, 1);//Up

        pc.initialBall(1, -50, 50, 0.8, 100, 3, 2, new Color(255, 0, 0));
        pc.initialBall(1, 20, 10, 0.9, 70, 3, 2, new Color(0, 255, 0));
        pc.initialBall(1, 0, 3, 0.9, 50.3, 10, 2, new Color(0, 0, 255));

        pc.initialBox(1, 0, 0, 0.7, 50, 20, 2, 3, new Color(255, 255, 0));
        pc.initialBox(1, 10, 0, 0.7, 20, 20, 5, 4, new Color(255, 0, 255));
        pc.initialBox(1, 0, 0, 0.7, 100, 55, 4, 2, new Color(0, 255, 255));

        pc.initialTriangle(0, 0, 0, 1, 50, 50,5 , 10, 2, new Color(87,145,4));

        pc.initialRotationRectangle(54, 50, 2, 10, false, 'z');
        pc.initialRotationRectangle(76, 50, 2, 10, true, 'x');
    }
}
