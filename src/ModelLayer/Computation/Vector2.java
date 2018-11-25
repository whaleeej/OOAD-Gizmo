package ModelLayer.Computation;

import static java.lang.Math.sqrt;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x,double y)
    {
        this.x=x;
        this.y=y;
    }

    public Vector2(Vector2 vect2)
    {
        this.x=vect2.x;
        this.y=vect2.y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector2)
        {
            Vector2 v=(Vector2) obj;
            return v.x==x&&v.y==y;
        }
        return false;
    }

    public double dot(Vector2 vect2)
    {
        return x*vect2.x+y*vect2.y;
    }
    public double dot(double x2,double y2)
    {
        return x*x2+y*y2;
    }
    public double crossLength(Vector2 vec2)
    {
        double x2=vec2.x;
        double y2=vec2.y;
        return x*y2+y*x2;
    }

    public double lengthSquared()
    {
        return x*x+y*y;
    }

    public double length()
    {
        return sqrt(lengthSquared());
    }

    public void addby(Vector2 v)
    {
        x=x+v.x;
        y=y+v.y;
    }

    public void minusby(Vector2 v)
    {
        x=x-v.x;
        y=y-v.y;
    }

    public void multiplyBy(double coe)
    {
        x=x*coe;
        y=y*coe;
    }
    public void normalize()
    {
        double length=length();
        x=x/length;
        y=y/length;
    }

    //Static methods
    //simply operate two Vector2s into one.
    public static Vector2 add(Vector2 v1,Vector2 v2)
    {
        return new Vector2(v1.x+v2.x,v1.y+v2.y);
    }

    public static Vector2 minus(Vector2 v1,Vector2 v2)
    {
        return new Vector2(v1.x-v2.x,v1.y-v2.y);
    }
    public static Vector2 multiply(Vector2 v1,double coe)
    {
        return new Vector2(v1.x*coe,v1.y*coe);
    }
}
