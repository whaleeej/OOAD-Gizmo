package Game;

import Entrance.MainScene;
import Physics.Controller.PhysicsEngineController;

public class GameController {
    private GameRender gr = null;
    private MainScene mainScene = null;
    //GameScene gs=null;
    public GameController(MainScene mainScene)
    {
        //gs=new GameScene();
        this.mainScene = mainScene;
        gr=new GameRender();
        this.mainScene.setGameRender(gr);
        //gs.add(gr);
        //gs.setVisible(true);
        new PhysicsEngineController(gr);
    }
}
