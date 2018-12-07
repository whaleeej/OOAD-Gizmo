package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;
import Entrance.MainScene;
import Game.Controller.GameController;
import Physics.Model.Computation.Vector2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

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
        gameController.initialGameWorld(200, 0, 0);
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
                {
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
                }
                case "Hexagon":
                {
                    int dis = scale*size/2;
                    double space = dis/2*sqrt(3);
                    double cx = x+dis;
                    double cy = y+dis;
                    Vector2[] buf = new Vector2[6];
                    if(rotation % 2 == 1)
                    {
                        double[] pointX = {x,x+dis/2,x+dis*3/2,x+dis*2,x+dis*3/2,x+dis/2};
                        double[] pointY = {cy,cy-space,cy-space,cy,cy+space,cy+space};
                        for(int i = 0; i < 6; i++)
                            buf[i] = new Vector2(pointX[i],pointY[i]);
                    }else
                    {
                        double[] pointX = {cx,cx+space,cx+space,cx,cx-space,cx-space};
                        double[] pointY = {y,y+dis/2,y+dis*3/2,y+dis*2,y+dis*3/2,y+dis/2};
                        for(int i = 0; i < 6; i++)
                            buf[i] = new Vector2(pointX[i],pointY[i]);
                    }
                    gameController.setPolygon(buf,color,movable);
                    break;
                }
                case "Trapezoid":
                {
                    double dis = scale*size/2;
                    Vector2[] buf = new Vector2[4];
                    if(rotation == 1)
                    {
                        double[] pointX = {x,x+dis/2,x+dis*3/2,x+dis*2};
                        double[] pointY = {y+dis*3/2,y+dis/2,y+dis/2,y+dis*3/2};
                        for(int i = 0; i < 4; i++)
                            buf[i] = new Vector2(pointX[i],pointY[i]);
                    }else if(rotation == 2)
                    {
                        double[] pointX = {x+dis/2,x+dis*3/2,x+dis*3/2,x+dis/2};
                        double[] pointY = {y,y+dis/2,y+dis*3/2,y+dis*2};
                        for(int i = 0; i < 4; i++)
                            buf[i] = new Vector2(pointX[i],pointY[i]);
                    }else if(rotation == 3)
                    {
                        double[] pointX = {x,x+dis*2,x+dis*3/2,x+dis/2};
                        double[] pointY = {y+dis/2,y+dis/2,y+dis*3/2,y+dis*3/2};
                        for(int i = 0; i < 4; i++)
                            buf[i] = new Vector2(pointX[i],pointY[i]);
                    }else
                    {
                        double[] pointX = {x+dis/2,x+dis/2,x+dis*3/2,x+dis*3/2};
                        double[] pointY = {y+dis*3/2,y+dis/2,y,y+dis*2};
                        for(int i = 0; i < 4; i++)
                            buf[i] = new Vector2(pointX[i],pointY[i]);
                    }
                    gameController.setPolygon(buf,color,movable);
                    break;
                }
                case "Pipe":
                {
                    Color color2 = Gizmo.colorMap.get(color);
                    rotation--;
                    if(rotation == 0)
                        rotation = 4;
                    gameController.setPipe(x,y,(double)size*scale/4,(double)size*scale,(double) size*scale,rotation,color2,color);
                    break;
                }
                case "Absorb":
                {
                    gameController.setAbsorber(x,y,(double) scale * size, (double) scale * size, color);
                    break;
                }
            }
        }
        gameController.instantiateCompleted();
    }
}
