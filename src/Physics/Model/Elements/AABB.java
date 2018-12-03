package Physics.Model.Elements;

import Physics.Model.Computation.Vector2;

import java.awt.*;

import static java.lang.Math.abs;

public class AABB extends RigidBody implements Texture ,Geometry{
    public Vector2 min;
    public Vector2 max;
    public Color color;
    public double volume;
    public AABB(double m, Vector2 f, Vector2 v,double e, double x_min,double y_min,double width, double height) {
        super(m, f, v,e);
        color=new Color(0,0,0);
        min=new Vector2(x_min,y_min);
        max=new Vector2(x_min+width,y_min+height);
        volume=(max.x-min.x)*(max.y-min.y);
    }

    //Position update by ticks
    @Override
    public void update(double ticks) {
        min.x=min.x+velocity.x*ticks+0.5*force.x*massInv*ticks*ticks;
        min.y=min.y+velocity.y*ticks+0.5*force.y*massInv*ticks*ticks;
        max.x=max.x+velocity.x*ticks+0.5*force.x*massInv*ticks*ticks;
        max.y=max.y+velocity.y*ticks+0.5*force.y*massInv*ticks*ticks;

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
        min.x=min.x+vec.x;
        min.y=min.y+vec.y;
        max.x=max.x+vec.x;
        max.y=max.y+vec.y;
    }

    //Implements Interfaces of ITexture and IGeometry
    @Override
    public Vector2 getMin() {
        return new Vector2(min);
    }

    @Override
    public Vector2 getMax() {
        return new Vector2(max);
    }

    @Override
    public Vector2 getExtra() {
        return null;
    }

    @Override
    public Shape getShape() {
        return Shape.Rectangle;
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
