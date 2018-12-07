package Build.Model;

import java.awt.*;

public class Gizmo
{
    public static final Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA, Color.GRAY, Color.BLACK};
    public static final String[] shapeArray = {"Ball","Circle","Square","Triangle","Hexagon",
            "Trapezoid","Pipe","Absorb","LeftFlipper","RightFlipper"};
    private String shape;
    private Color color;
    private int x;//grid x
    private int y;//grid y
    private int size;
    private int rotation;
    private char key;
    private boolean movable;

    public Gizmo(String shape, Color color,int x, int y)
    {
        this.shape = shape;
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = 1;
        this.rotation = 0;
        key = '\0';
        if(shape.equals("Ball"))
            movable = true;
        else
            movable = false;
    }

    public Gizmo(String shape, Color color, int x, int y, int size, int rotation, char key, boolean movable)
    {
        this.shape = shape;
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rotation = rotation;
        this.key = key;
        this.movable = movable;
    }

    public void rotate()
    {
        rotation++;
        if(rotation == 4)
            rotation = 0;
    }

    public boolean movableCanChange()
    {
        if(shape.equals("Square")||shape.equals("Triangle")||shape.equals("Hexagon")||shape.equals("Trapezoid"))
            return true;
        return false;
    }
    public void changeMovable()
    {
        movable = !movable;
    }

    public void resize(boolean flag)
    {
        if(flag)
            size++;
        else
            size--;
    }

    public char getKey()
    {
        return key;
    }

    public void setKey(char key)
    {
        this.key = key;
    }

    public boolean isMovable()
    {
        return movable;
    }

    public void setMovable(boolean movable)
    {
        this.movable = movable;
    }

    public String getShape()
    {
        return shape;
    }

    public void setShape(String shape)
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
