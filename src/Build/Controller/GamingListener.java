package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;
import Entrance.MainScene;
import Game.Controller.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamingListener implements ActionListener
{
    private MainScene mainScene;

    public GamingListener(MainScene mainScene)
    {
        this.mainScene = mainScene;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mainScene.changeToGameMode();
        GameController gameController = mainScene.getGameController();
        BuildController buildController = mainScene.getBuildController();
        gameController.resetGameController();
        gameController.initialGameWorld(200, 0.1, 0.1);
        ArrayList<Gizmo> gizmos = buildController.getBuildRender().getGizmos();
        for (Gizmo gizmo : gizmos)
        {
            int size = gizmo.getSize();
            int scale = Grid.SCALE;
            double x = (double) gizmo.getX() * scale;
            double y = (double) gizmo.getY() * scale;
            Color color = gizmo.getColor();
            int rotation = gizmo.getRotation() + 1;
            boolean movable = gizmo.isMovable();
            switch (gizmo.getShape())
            {
                case "Ball":
                    gameController.setBall(0, 0, x + scale * size / 2, y + scale * size / 2, scale * size * 3 / 10, color);
                    break;
                case "Circle":
                    gameController.setCircle(x + scale * size / 2, y + scale * size / 2, scale * size / 2, color);
                    break;
                case "Square":
                    gameController.setBox(x, y, (double) scale * size, (double) scale * size, color, movable);
                    break;
                case "Triangle":
                    if (rotation == 1)
                    {
                        y += scale * size;
                        rotation = 2;
                    } else if(rotation == 2)
                    {
                        rotation = 1;
                    }else if(rotation == 3)
                    {
                        x += scale * size;
                    }else
                    {
                        x += scale * size;
                        y += scale * size;
                    }
                    gameController.setTriangle(x, y, (double) scale * size, (double) scale * size, rotation, color, movable);
                    break;
                case "Hexagon":
                    break;

            }
        }
        gameController.instantiateCompleted();
    }
}
