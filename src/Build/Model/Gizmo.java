package Build.Model;

import java.awt.*;

public class Gizmo
{
    public static enum Shape{Ball,Circle, Square,RotationRectangle,Triangle};
    private Shape shape;
    private int size;
    private int x;//grid x
    private int y;//grid y
    private int rotation;
    private Color color;

    public Gizmo(Shape shape, int size, int x, int y, int rotation, Color color)
    {
        this.shape = shape;
        this.size = size;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.color = color;
    }

    public void rotate()
    {
        rotation++;
        if(rotation == 4)
            rotation = 0;
    }

    public Shape getShape()
    {
        return shape;
    }

    public void setShape(Shape shape)
    {
        this.shape = shape;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
