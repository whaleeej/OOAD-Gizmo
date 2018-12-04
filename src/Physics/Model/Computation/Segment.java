package Physics.Model.Computation;

public class Segment {
    //Data struct
    //A segement include two point and direction from p1 to p2
    public Vector2 p0;
    public Vector2 p1;
    public Vector2 direction;

    public Segment(Vector2 p0,Vector2 p1)
    {
        this.p0=new Vector2(p0);
        this.p1=new Vector2(p1);
        direction=Vector2.minus(p1,p0 );
    }
}
