package Game;

import Physics.Controller.PhysicsEngineController;

public class GameController {
    GameRender gr=null;
    GameScene gs=null;
    public GameController()
    {
        gs=new GameScene();
        gr=new GameRender();
        gs.add(gr);
        gs.setVisible(true);
        new PhysicsEngineController(gr);
    }
}
