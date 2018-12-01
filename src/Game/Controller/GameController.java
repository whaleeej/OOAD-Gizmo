package Game.Controller;

import Entrance.MainScene;
import Game.View.GameRender;
import Game.View.GameScene;
import Game.View.GameToolbar;
import Physics.Controller.PhysicsEngineController;

public class GameController {
    private GameRender gameRender = null;
    private MainScene mainScene = null;
    private GameScene gameScene = null;
    private GameToolbar gameToolbar = null;
    //GameScene gs=null;
    public GameController(MainScene mainScene)
    {
        this.mainScene = mainScene;
        gameScene = new GameScene();
        gameRender =new GameRender();
        gameToolbar = new GameToolbar();
        gameScene.setLayout(null);
        gameRender.setBounds(0,0,1101,801);
        gameToolbar.setBounds(1101,0,81,801);
        gameScene.add(gameRender);
        gameScene.add(gameToolbar);
        setListeners();
        this.mainScene.addGameScene(gameScene);
        new PhysicsEngineController(gameRender);
    }

    private void setListeners()
    {
        gameToolbar.getBuildingButton().addActionListener(new BuildingListener(mainScene));
    }
}
