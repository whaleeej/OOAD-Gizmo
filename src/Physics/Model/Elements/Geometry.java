package Physics.Model.Elements;

import Physics.Model.Computation.Vector2;

public interface Geometry {
    public static enum Shape{Circle, Rectangle,RotationRectangle,Triangle,Polygon};
    //Get the upper-left point position info
    public Vector2 getMin();
    //Get the bottom-right point position info
    public Vector2 getMax();
    public Vector2 getExtra();
    public Shape getShape();
    public Vector2[] getPolygonBuffer();
}
