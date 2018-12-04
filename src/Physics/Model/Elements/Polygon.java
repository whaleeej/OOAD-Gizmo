package Physics.Model.Elements;

import Physics.Model.Computation.Vector2;

import java.awt.*;

import static java.lang.Math.abs;

public class Polygon extends RigidBody  implements Texture ,Geometry {
    public Color color;
    public double volume;
    public Vector2[] buffer;//Extra info for polygon
    public int num;//number of points
    public Polygon(double m, Vector2 f, Vector2 v, double e,Vector2[] buf) {
        super(m, f, v, e);
        color=new Color(0,0,0);
        if(buf==null) return;

        //num buffer volume
        num=buf.length;
        buffer=new Vector2[num];
        double xmin=999999;
        double xmax=-999999;
        double ymin=999999;
        double ymax=-999999;

        //Estimate volume
        for(int i=0;i<num;i++)
        {
            buffer[i]=new Vector2(buf[i]);
            if(buffer[i].x<xmin)
                xmin=buffer[i].x;
            if(buffer[i].x>xmax)
                xmax=buffer[i].x;
            if(buffer[i].y<ymin)
                ymin=buffer[i].y;
            if(buffer[i].y>ymax)
                ymax=buffer[i].y;
        }
        volume=(xmax-xmin)*(ymax-ymin)/2;

    }

    @Override
    public Vector2 getMin() {
        return null;
    }

    @Override
    public Vector2 getMax() {
        return null;
    }

    @Override
    public Vector2 getExtra() {
        return null;
    }

    @Override
    public Shape getShape() {
        return Shape.Polygon;
    }

    @Override
    public Vector2[] getPolygonBuffer() {
        return buffer;
    }

    @Override
    public void update(double ticks) {
        double deltaX=velocity.x*ticks+0.5*force.x*massInv*ticks*ticks;
        double deltaY=velocity.y*ticks+0.5*force.y*massInv*ticks*ticks;

        for(int i=0;i<num;i++)
        {
            buffer[i].x+=deltaX;
            buffer[i].y+=deltaY;
        }

        //Vel changes
        Vector2 a=new Vector2(force.x*massInv,force.y*massInv);
        Vector2 deltaAcc=new Vector2(-c*volume*velocity.x*abs(velocity.x)*massInv,-c*volume*velocity.y*abs(velocity.y)*massInv);
        velocity.x=velocity.x+a.x*ticks;
        velocity.y=velocity.y+a.y*ticks;
        //air resistance
        if(velocity.x!=0)
        {
            double delta=deltaAcc.x*ticks;

            if(abs(delta)<0.005) { velocity.x=0;}//if the delta velocity is smaller than 0.005, then stop it to prevent chill
            else if((velocity.x+delta)*(velocity.x)<0)
                velocity.x=0;
            else
                velocity.x=velocity.x+delta;
        }
        if(velocity.y!=0)
        {
            double delta=deltaAcc.y*ticks;
            if(abs(delta)<0.005) {velocity.y=0;}
            else if((velocity.y+delta)*(velocity.y)<0)
                velocity.y=0;
            else
                velocity.y=velocity.y+delta;
        }
    }

    @Override
    public void update(Vector2 vec) {
        for(int i=0;i<num;i++)
        {
            buffer[i].x+=vec.x;
            buffer[i].y+=vec.y;
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color=color;
    }
}
