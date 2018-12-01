package Game;

import Physics.Controller.PhysicsEngineController;

import javax.swing.*;

public class GameScene extends JFrame
{
    private static final long serialVersionUID = 1L;// serialVersionUID唯一的可串行化的版本

    public GameScene()
    {
        super("GizmoScene");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1201, 801);
        this.setLocationRelativeTo(null);
    }


}
