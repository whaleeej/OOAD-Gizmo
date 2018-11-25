package ModelLayer.PhysicEngine;

import ModelLayer.Computation.Vector2;

import java.awt.*;

public class Circle extends RigidBody implements Texture, Geometry {
    public double radius;
    public Vector2 position;
    public Color color;

    public Circle(double m, Vector2 g,Vector2 v,double e,double posX,double posY,double r) {
        super(m, g, v,e);
        color=new Color(0,0,0);
        position=new Vector2(posX,posY);
        radius=r;
    }

    //Postion Update by Seconds
    @Override
    public void update(double ticks) {
        position.x=position.x+velocity.x*ticks+0.5*gravity.x*ticks*ticks;
        position.y=position.y+velocity.y*ticks+0.5*gravity.y*ticks*ticks;

        velocity.x=velocity.x+gravity.x*ticks;
        velocity.y=velocity.y+gravity.y*ticks;
    }

    @Override
    public void update(Vector2 vec) {
        position.x=position.x+vec.x;
        position.y=position.y+vec.y;

    }

    @Override
    public Vector2 getMin() {
        return new Vector2(position.x-radius,position.y-radius);
    }

    @Override
    public Vector2 getMax() {
        return new Vector2(position.x+radius,position.y+radius);
    }

    @Override
    public Vector2 getExtra() {
        return null;
    }

    @Override
    public Shape getShape() {
        return Shape.Circle;
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
