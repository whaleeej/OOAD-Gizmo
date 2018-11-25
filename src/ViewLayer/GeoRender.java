package ViewLayer;

import ModelLayer.Computation.Vector2;
import ModelLayer.PhysicEngine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class GeoRender extends JPanel
{
    private int frameCount;
    private double scale; // The scale of Screen size v.s. RealWorld
    private Vector<RigidBody> rigids;
    public GeoRender()
    {
        //Initial member
        scale=10.0;
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
        frameCount++;
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
                    int xBuffer[]={(int)(x2),(int)(x1),(int)(x1+deltaX),(int)(x2+deltaX),};
                    int yBuffer[]={(int)(y2),(int)(y1),(int)(y1+deltaY),(int)(y2+deltaY)};
                    g.fillPolygon(xBuffer,yBuffer,4);

                }
            }
        }
    }


}
