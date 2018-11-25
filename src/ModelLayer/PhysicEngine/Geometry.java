package ModelLayer.PhysicEngine;

import ModelLayer.Computation.Vector2;

public interface Geometry {
    public static enum Shape{Circle, Rectangle,RotationRectangle};
    //Get the upper-left point position info
    public Vector2 getMin();
    //Get the bottom-right point position info
    public Vector2 getMax();
    public Vector2 getExtra();
    public Shape getShape();
}
