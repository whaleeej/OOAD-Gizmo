package ModelLayer.Computation;

public class Matrix2 {
    public double x1,y1;
    public double x2,y2;

    public Matrix2(double d1,double d2,double d3,double d4)
    {
        x1=d1;y1=d2;x2=d3;y2=d4;
    }


    //Matrix * Vector = Vector
    public static Vector2 multiplyVector2(Matrix2 m,Vector2 v)
    {
        return new Vector2(  m.x1*v.x+m.y1*v.y                ,      m.x2*v.x+m.y2*v.y            );
    }

}
