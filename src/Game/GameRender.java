package Game;

import Physics.Controller.PhysicsEngineController;
import Physics.Controller.PhysicsRender;
import Physics.Model.Elements.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

//The view of the game must implement Interface PhysicsRender to use the Physicworld
public class GameRender extends JPanel implements PhysicsRender
{

    private int frameCount;
    private double scale; // The scale of Screen size v.s. RealWorld
    private Vector<RigidBody> rigids;

    public GameRender()
    {
        //Initial member
        scale=1.0;
        //Count the frames
        frameCount=0;

    }

    public void updateGeometries(Vector<RigidBody> g)
    {
        rigids=g;
    }

    @Override
    public void paintComponent(Graphics g)//Render Methods: to render all acceptable Geometries by getMin and get Max
    {
        super.paintComponent(g);
        if(rigids==null) return;
        for(RigidBody rigid:rigids)
        {
            Geometry geo=(Geometry)rigid;
            g.setColor(((Texture)geo).getColor());
            Geometry.Shape shape=((Geometry)geo).getShape();
            switch (shape)
            {
                //if the Geometry of a Obj is Circle
                case Circle:
                {
                    int x1=(int)((geo.getMin().x)+0.5);
                    int y1=(int)((geo.getMin().y)+0.5);
                    int x2=(int)((geo.getMax().x)+0.5);
                    int y2=(int)((geo.getMax().y)+0.5);
                    g.fillOval(x1, y1, x2-x1, y2-y1);
                    break;
                }
                //if the Geometry of a Obj is Rectangle
                case Rectangle:
                {
                    int x1=(int)((geo.getMin().x)+0.5);
                    int y1=(int)((geo.getMin().y)+0.5);
                    int x2=(int)((geo.getMax().x)+0.5);
                    int y2=(int)((geo.getMax().y)+0.5);
                    g.fillRect(x1, y1, x2-x1, y2-y1);
                    break;
                }
                case RotationRectangle:
                {

                    double x1=((geo.getMin().x+geo.getMax().x/2.0*Math.sin(geo.getExtra().x)));
                    double y1=((geo.getMin().y-geo.getMax().x/2.0*Math.cos(geo.getExtra().x)));
                    double x2=((geo.getMin().x-geo.getMax().x/2.0*Math.sin(geo.getExtra().x)));
                    double y2=((geo.getMin().y+geo.getMax().x/2.0*Math.cos(geo.getExtra().x)));

                    double deltaX=geo.getMax().y*Math.cos(geo.getExtra().x);
                    double deltaY=geo.getMax().y*Math.sin(geo.getExtra().x);
                    int xBuffer[]={(int)(x2+0.5),(int)(x1+0.5),(int)(x1+deltaX+0.5),(int)(x2+deltaX+0.5),};
                    int yBuffer[]={(int)(y2+0.5),(int)(y1+0.5),(int)(y1+deltaY+0.5),(int)(y2+deltaY+0.5)};
                    g.fillPolygon(xBuffer,yBuffer,4);

                }
                case Triangle:
                {

                    double x1=geo.getMin().x;
                    double y1=geo.getMin().y;
                    double deltaX=geo.getMax().x*geo.getExtra().x;
                    double deltaY=geo.getMax().y*geo.getExtra().y;
                    if(geo.getExtra().x==geo.getExtra().y)
                    {
                        int xBuffer[]={(int)(x1+0.5),(int)(x1+deltaX+0.5),(int)(x1+0.5)};
                        int yBuffer[]={(int)(y1+0.5),(int)(y1+0.5),(int)(y1+deltaY+0.5)};
                        g.fillPolygon(xBuffer,yBuffer,3);
                    }
                    else {
                        int xBuffer[]={(int)(x1+0.5),(int)(x1+0.5),(int)(x1+deltaX+0.5)};
                        int yBuffer[]={(int)(y1+0.5),(int)(y1+deltaY+0.5),(int)(y1+0.5)};
                        g.fillPolygon(xBuffer,yBuffer,3);
                    }



                }
            }
        }
    }



}
