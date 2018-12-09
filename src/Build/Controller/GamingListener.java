package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;
import Build.Model.Setting;
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
        Setting setting = buildController.getSetting();
        //gameController.initialGameWorld(200,0.1,0.1);
        gameController.initialGameWorld(setting.getRealGravity(), setting.getRealU(), setting.getRealC());
        ArrayList<Gizmo> gizmos = buildController.getBuildRender().getGizmos();
        for (Gizmo gizmo : gizmos)
        {
            double length = gizmo.getSize()*Grid.SCALE;
            double x = (double) gizmo.getX() * Grid.SCALE;
            double y = (double) gizmo.getY() * Grid.SCALE;
            Color color = gizmo.getColor();
            int rotation = gizmo.getRotation() + 1;
            boolean movable = gizmo.isMovable();
            switch (gizmo.getShape())
            {
                case "Ball":
                    gameController.setBall(0, 0, x + length / 2, y + length / 2, length * 3 / 10, color);
                    break;
                case "Circle":
                    gameController.setCircle(x + length / 2, y + length / 2, length / 2, color);
                    break;
                case "Square":
                    gameController.setBox(x, y, length, length, color, movable);
                    break;
                case "Triangle":
                {
                    if (rotation == 1)
                    {
                        y += length;
                        rotation = 2;
                    } else if(rotation == 2)
                    {
                        rotation = 1;
                    }else if(rotation == 3)
                    {
                        x += length;
                    }else
                    {
                        x += length;
                        y += length;
                    }
                    gameController.setTriangle(x, y, length, length, rotation, color, movable);
                    break;
                }
                case "Hexagon":
                {
                    double dis = length/2;
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
                    double dis = length/2;
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
                    gameController.setPipe(x,y,length/4,length,length,rotation,color2,color);
                    break;
                }
                case "Absorb":
                {
                    gameController.setAbsorber(x,y,length, length, color);
                    break;
                }
            }
        }
        for(Gizmo gizmo : gizmos)
        {
            double length = gizmo.getSize() * Grid.SCALE;
            double x = (double) gizmo.getX() * Grid.SCALE;
            double y = (double) gizmo.getY() * Grid.SCALE;
            Color color = gizmo.getColor();
            char key = gizmo.getKey();
            switch (gizmo.getShape())
            {
                case "LeftFlipper":
                {
                    gameController.setRotationRectangle(x+length/12,y+length/12, length/6,length-length/12,false,key,color);
                    break;
                }
                case "RightFlipper":
                {
                    gameController.setRotationRectangle(x+length-length/12,y+length/12, length/6,length-length/12,true,key,color);
                    break;
                }
            }
        }
        gameController.instantiateCompleted();
    }
}
