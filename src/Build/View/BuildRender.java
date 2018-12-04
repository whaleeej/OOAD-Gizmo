package Build.View;

import Build.Model.Gizmo;
import Build.Model.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Build.Model.Gizmo.Shape.Ball;
import static Build.Model.Gizmo.Shape.Square;

public class BuildRender extends JPanel
{
    private ArrayList<Gizmo> gizmos;
    private Grid grid;
    public BuildRender()
    {
        super();
        gizmos = new ArrayList<Gizmo>();
        Gizmo gizmo = new Gizmo(Ball,1,3,3,0,Color.red);
        gizmos.add(gizmo);
        gizmo = new Gizmo(Square,2,5,3,0,Color.yellow);
        gizmos.add(gizmo);
        gizmo = new Gizmo(Square,1,1,1,0,Color.CYAN);
        gizmos.add(gizmo);
        gizmo = new Gizmo(Gizmo.Shape.Circle,1,10,5,0,Color.green);
        gizmos.add(gizmo);
        gizmo = new Gizmo(Gizmo.Shape.Circle,3,17,5,0,Color.blue);
        gizmos.add(gizmo);
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
            g.setColor(gizmo.getColor());
            int size = gizmo.getSize();
            int rotatin = gizmo.getRotation();
            switch (gizmo.getShape())
            {
                case Ball:
                    int space = scale*3/10;
                    g.fillOval(gizmo.getX()*scale+space*size,gizmo.getY()*scale+space*size,size*scale*2/5,size*scale*2/5);
                    break;
                case Circle:
                    g.fillOval(gizmo.getX()*scale,gizmo.getY()*scale,size*scale,size*scale);
                    break;
                case Square:
                    g.fillRect(gizmo.getX()*scale,gizmo.getY()*scale,size*scale,size*scale);
                    break;
            }
        }
    }
}
