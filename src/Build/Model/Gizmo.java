package Build.Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Gizmo
{
    public static final Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA, Color.GRAY, Color.BLACK};
    public static final Map<Color,Color> colorMap;
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

    static
    {
        colorMap = new HashMap<>();
        for(int i = 0; i < colorArray.length; i++)
        {
            colorMap.put(colorArray[i],colorArray[(i+1)%colorArray.length]);
        }
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

    public void rotate(boolean isRight)
    {
        if(isRight)
        {
            rotation++;
            if(rotation == 4)
                rotation = 0;
        }else
        {
            if(rotation == 0)
                rotation = 4;
            rotation--;
        }
    }

    public boolean movableCanChange()
    {
        if(shape.equals("Square")||shape.equals("Triangle")||shape.equals("Hexagon")||shape.equals("Trapezoid"))
            return true;
        return false;
    }

    public boolean isFlipper()
    {
        if(shape.equals("LeftFlipper") || shape.equals("RightFlipper"))
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
