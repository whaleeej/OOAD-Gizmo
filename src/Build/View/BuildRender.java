package Build.View;

import Build.Controller.BuildController;
import Build.Model.Gizmo;
import Build.Model.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Build.Controller.BuildController.Command.*;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class BuildRender extends JPanel
{
    private ArrayList<Gizmo> gizmos;
    private Gizmo addingGizmo;
    private Grid grid;
    private BuildController buildController;

    public BuildRender(BuildController buildController)
    {
        super();
        this.buildController = buildController;
        grid = new Grid();
        addingGizmo = new Gizmo("Ball",Color.red,0,0);
        gizmos = new ArrayList<Gizmo>();
        Gizmo gizmo = new Gizmo("Ball",Color.red,3,3);
        gizmos.add(gizmo);
        gizmo = new Gizmo("Square",Color.yellow,5,3,2,0,'\0',true);
        gizmos.add(gizmo);
        gizmo = new Gizmo("Square",Color.CYAN,3,6);
        gizmos.add(gizmo);
        gizmo = new Gizmo("Square",Color.ORANGE,3,10);
        gizmos.add(gizmo);
        gizmo = new Gizmo("Circle",Color.green,3,13);
        gizmos.add(gizmo);
        gizmo = new Gizmo("Circle",Color.blue,17,5,3,0,'\0',false);
        gizmos.add(gizmo);
        grid.cover(gizmos);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        int scale = Grid.SCALE;
        for(int i = 0; i <= 1100; i += scale)
            g.drawLine(i,0,i,800);
        for(int i = 0; i <= 800; i += scale)
            g.drawLine(0,i,1100,i);
        for(Gizmo gizmo : gizmos)
        {
            draw(gizmo,g);
        }

        if(buildController.getCommand().equals(Add))
        {
            draw(addingGizmo,g);
        }else
        {

            Gizmo chosenGizmo = buildController.getChosenGizmo();
            if(chosenGizmo != null)
            {
                draw(chosenGizmo,g);
                g.setColor(new Color(255,154,97));
                int x = chosenGizmo.getX()*scale;
                int y = chosenGizmo.getY()*scale;
                int size = chosenGizmo.getSize()*scale;
                g.drawRect(x,y,size,size);
                g.drawRect(x+1,y+1,size-2,size-2);
                g.drawRect(x+2,y+2,size-4,size-4);
            }
        }
    }

    private void draw(Gizmo gizmo, Graphics g)
    {
        g.setColor(gizmo.getColor());
        int size = gizmo.getSize();
        int scale = Grid.SCALE;
        int rotation = gizmo.getRotation();
        int x = gizmo.getX()*scale;
        int y = gizmo.getY()*scale;
        switch (gizmo.getShape())
        {
            case "Ball":
            {
                int space = scale/5;
                g.fillOval(x+space*size,y+space*size,size*scale*3/5,size*scale*3/5);
                break;
            }
            case "Circle":
                g.fillOval(x,y,size*scale,size*scale);
                break;
            case "Square":
                g.fillRect(x,y,size*scale,size*scale);
                break;
            case "Triangle":
            {
                int[] pointX = new int[3];
                int[] pointY = new int[3];
                int[] bufferX = {x,x+size*scale,x+size*scale,x};
                int[] bufferY = {y,y,y+size*scale,y+size*scale};
                for(int i = 0; i < 3; i++)
                {
                    pointX[i] = bufferX[(i+rotation+2)%4];
                    pointY[i] = bufferY[(i+rotation+2)%4];
                }
                g.fillPolygon(pointX,pointY,3);
                break;
            }
            case "Hexagon":
            {
                int dis = scale*size/2;
                int space = (int)(dis/2*sqrt(3));
                int cx = x+dis;
                int cy = y+dis;
                if(rotation % 2 == 0)
                {
                    int[] pointX = {x,x+dis/2,x+dis*3/2,x+dis*2,x+dis*3/2,x+dis/2};
                    int[] pointY = {cy,cy-space,cy-space,cy,cy+space,cy+space};
                    g.fillPolygon(pointX,pointY,6);
                }else
                {
                    int[] pointX = {cx,cx+space,cx+space,cx,cx-space,cx-space};
                    int[] pointY = {y,y+dis/2,y+dis*3/2,y+dis*2,y+dis*3/2,y+dis/2};
                    g.fillPolygon(pointX,pointY,6);
                }
                break;
            }
            case "Trapezoid":
            {
                int dis = scale*size/2;
                if(rotation == 0)
                {
                    int[] pointX = {x,x+dis/2,x+dis*3/2,x+dis*2};
                    int[] pointY = {y+dis*3/2,y+dis/2,y+dis/2,y+dis*3/2};
                    g.fillPolygon(pointX,pointY,4);
                }else if(rotation == 1)
                {
                    int[] pointX = {x+dis/2,x+dis*3/2,x+dis*3/2,x+dis/2};
                    int[] pointY = {y,y+dis/2,y+dis*3/2,y+dis*2};
                    g.fillPolygon(pointX,pointY,4);
                }else if(rotation == 2)
                {
                    int[] pointX = {x,x+dis*2,x+dis*3/2,x+dis/2};
                    int[] pointY = {y+dis/2,y+dis/2,y+dis*3/2,y+dis*3/2};
                    g.fillPolygon(pointX,pointY,4);
                }else
                {
                    int[] pointX = {x+dis/2,x+dis/2,x+dis*3/2,x+dis*3/2};
                    int[] pointY = {y+dis*3/2,y+dis/2,y,y+dis*2};
                    g.fillPolygon(pointX,pointY,4);
                }
                break;
            }
            case "Pipe":
            {
                int dis = scale*size/2;
                if(rotation == 0)
                {
                    int[] pointX = {x,x+dis/2,x+dis/2,x+dis*2,x+dis*2,x};
                    int[] pointY = {y,y,y+dis*3/2,y+dis*3/2,y+dis*2,y+dis*2};
                    g.fillPolygon(pointX,pointY,6);
                    g.setColor(Gizmo.colorMap.get(gizmo.getColor()));
                    g.fillRect(x,y,dis/2,10);
                    g.fillRect(x+dis*2-10,y+dis*3/2,10,dis/2);
                }else if(rotation == 1)
                {
                    int[] pointX = {x,x+dis*2,x+dis*2,x+dis/2,x+dis/2,x};
                    int[] pointY = {y,y,y+dis/2,y+dis/2,y+dis*2,y+dis*2};
                    g.fillPolygon(pointX,pointY,6);
                    g.setColor(Gizmo.colorMap.get(gizmo.getColor()));
                    g.fillRect(x,y+dis*2-10,dis/2,10);
                    g.fillRect(x+dis*2-10,y,10,dis/2);
                }else if(rotation == 2)
                {
                    int[] pointX = {x,x+dis*2,x+dis*2,x+dis*3/2,x+dis*3/2,x};
                    int[] pointY = {y,y,y+dis*2,y+dis*2,y+dis/2,y+dis/2};
                    g.fillPolygon(pointX,pointY,6);
                    g.setColor(Gizmo.colorMap.get(gizmo.getColor()));
                    g.fillRect(x+dis*3/2,y+dis*2-10,dis/2,10);
                    g.fillRect(x,y,10,dis/2);
                }else
                {
                    int[] pointX = {x,x+dis*3/2,x+dis*3/2,x+dis*2,x+dis*2,x};
                    int[] pointY = {y+dis*3/2,y+dis*3/2,y,y,y+dis*2,y+dis*2};
                    g.fillPolygon(pointX,pointY,6);
                    g.setColor(Gizmo.colorMap.get(gizmo.getColor()));
                    g.fillRect(x+dis*3/2,y,dis/2,10);
                    g.fillRect(x,y+dis*3/2,10,dis/2);
                }
                break;
            }
            case "Absorb":
            {
                g.fillRect(x,y,size*scale,size*scale);
                g.setColor(Color.white);
                g.fillOval(x+size*scale/6,y+size*scale/6,size*scale*2/3,size*scale*2/3);
                break;
            }

        }
    }

    public Gizmo getAddingGizmo()
    {
        return addingGizmo;
    }

    public ArrayList<Gizmo> getGizmos()
    {
        return gizmos;
    }

    public Grid getGrid()
    {
        return grid;
    }
}
