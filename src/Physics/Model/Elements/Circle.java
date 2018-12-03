package Physics.Model.Elements;

import Physics.Model.Computation.Vector2;

import java.awt.*;

public class Circle extends RigidBody implements Texture, Geometry {
    public double radius;
    public Vector2 position;
    public Color color;
    public double volume;

    public Circle(double m, Vector2 f,Vector2 v,double e,double posX,double posY,double r) {
        super(m, f, v,e);
        color=new Color(0,0,0);
        position=new Vector2(posX,posY);
        radius=r;
        volume=Math.PI*radius*radius;
    }

    public Circle(Circle circle) {
        super(circle.massInv,circle.force,circle.velocity,circle.epsilon);
        this.color=circle.getColor();
        this.position=new Vector2(circle.position);
        this.radius=circle.radius;
        this.volume=circle.volume;
    }

    //Postion Update by Seconds
    @Override
    public void update(double ticks) {
        position.x=position.x+velocity.x*ticks+0.5*force.x*massInv*ticks*ticks;
        position.y=position.y+velocity.y*ticks+0.5*force.y*massInv*ticks*ticks;

        velocity.x=velocity.x+force.x*massInv*ticks;
        velocity.y=velocity.y+force.y*massInv*ticks;
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
