package Game.View;

import Physics.Controller.PhysicsEngineController;
import Physics.Controller.PhysicsRender;
import Physics.Model.Computation.Vector2;
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
        scale=10.0;
        //Count the frames
        frameCount=0;
        this.setSize(1100, 800);

    }

    @Override
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
                    int x1=(int)((geo.getMin().x)*scale);
                    int y1=(int)((geo.getMin().y)*scale);
                    int x2=(int)((geo.getMax().x)*scale);
                    int y2=(int)((geo.getMax().y)*scale);
                    g.fillOval(x1, y1, x2-x1, y2-y1);
                    break;
                }
                //if the Geometry of a Obj is Rectangle
                case Rectangle:
                {
                    int x1=(int)((geo.getMin().x)*scale);
                    int y1=(int)((geo.getMin().y)*scale);
                    int x2=(int)((geo.getMax().x)*scale);
                    int y2=(int)((geo.getMax().y)*scale);
                    g.fillRect(x1, y1, x2-x1, y2-y1);
                    break;
                }
                case RotationRectangle:
                {

                    double x1=((geo.getMin().x+geo.getMax().x/2.0*Math.sin(geo.getExtra().x))*scale);
                    double y1=((geo.getMin().y-geo.getMax().x/2.0*Math.cos(geo.getExtra().x))*scale);
                    double x2=((geo.getMin().x-geo.getMax().x/2.0*Math.sin(geo.getExtra().x))*scale);
                    double y2=((geo.getMin().y+geo.getMax().x/2.0*Math.cos(geo.getExtra().x))*scale);

                    double deltaX=geo.getMax().y*Math.cos(geo.getExtra().x)*scale;
                    double deltaY=geo.getMax().y*Math.sin(geo.getExtra().x)*scale;

                    double radian=geo.getExtra().x;
                    double radius=geo.getMax().x/2*scale;

                    double minordeltaX=radius*Math.cos(radian);
                    double minordeltaY=radius*Math.sin(radian);
//                    int xBuffer[]={(int)(x2),(int)(x1),(int)(x1+deltaX),(int)(x2+deltaX),};
//                    int yBuffer[]={(int)(y2),(int)(y1),(int)(y1+deltaY),(int)(y2+deltaY)};
//                    g.drawPolygon(xBuffer,yBuffer,4);
                    int xBuffer[]={(int)(x1+minordeltaX),(int)(x1+deltaX-minordeltaX) ,(int)(x2+deltaX-minordeltaX) ,(int)(x2+minordeltaX)};
                    int yBuffer[]={(int)(y1+minordeltaY),(int)(y1+deltaY-minordeltaY) ,(int)(y2+deltaY-minordeltaY) ,(int)(y2+minordeltaY )};
                    g.fillPolygon(xBuffer,yBuffer,4);
//                    g.drawLine((int)(x1+minordeltaX),(int)(y1+minordeltaY ),(int)(x1+deltaX-minordeltaX) ,(int)(y1+deltaY-minordeltaY) );
//                    g.drawLine((int)(x2+minordeltaX),(int)(y2+minordeltaY ),(int)(x2+deltaX-minordeltaX) ,(int)(y2+deltaY-minordeltaY) );
                    g.fillOval((int)((x1+x2)/2+minordeltaX-radius),(int)((y1+y2)/2+minordeltaY-radius),(int)(2*radius),(int)(2*radius));
                    g.fillOval((int)((x1+x2)/2-minordeltaX+deltaX-radius),(int)((y1+y2)/2-minordeltaY+deltaY-radius),(int)(2*radius),(int)(2*radius));
                    break;

                }
                case Triangle:
                {

                    double x1=geo.getMin().x*scale;
                    double y1=geo.getMin().y*scale;
                    double deltaX=geo.getMax().x*geo.getExtra().x*scale;
                    double deltaY=geo.getMax().y*geo.getExtra().y*scale;
                    if(geo.getExtra().x==geo.getExtra().y)
                    {
                        int xBuffer[]={(int)(x1),(int)(x1+deltaX),(int)(x1)};
                        int yBuffer[]={(int)(y1),(int)(y1),(int)(y1+deltaY)};
                        g.fillPolygon(xBuffer,yBuffer,3);
                    }
                    else {
                        int xBuffer[]={(int)(x1),(int)(x1),(int)(x1+deltaX)};
                        int yBuffer[]={(int)(y1),(int)(y1+deltaY),(int)(y1)};
                        g.fillPolygon(xBuffer,yBuffer,3);
                    }
                    break;
                }
                case Polygon:
                {
                    Vector2[] buf=geo.getPolygonBuffer();
                    int xBuffer[]=new int[buf.length];
                    int yBuffer[]=new int[buf.length];
                    for(int i=0;i<buf.length;i++)
                    {
                        xBuffer[i]=(int)(buf[i].x*scale);
                        yBuffer[i]=(int)(buf[i].y*scale);
                    }
                    g.fillPolygon(xBuffer,yBuffer,buf.length);
                    break;
                }
                case Absorber:
                {
                    int x1=(int)((geo.getMin().x)*scale);
                    int y1=(int)((geo.getMin().y)*scale);
                    int x2=(int)((geo.getMax().x)*scale);
                    int y2=(int)((geo.getMax().y)*scale);
                    g.fillRect(x1, y1, x2-x1, y2-y1);
                    g.setColor(new Color(255,255,255));
                    g.fillOval(5*x1/6+x2/6, 5*y1/6+y2/6, 2*(x2-x1)/3, 2*(y2-y1)/3);
                    break;
                }

            }
        }
    }



}
