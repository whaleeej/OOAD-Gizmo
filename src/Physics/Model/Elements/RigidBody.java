package Physics.Model.Elements;

import Physics.Model.Computation.Vector2;

//Abstract class of all objects in the Physical world
//Mass=0 -> Mass=Inf -> Mass= 0
//Eplsilon is the coefficient of Newton's Law of Restitution
public abstract class RigidBody {
    public double mass;
    public double massInv;
    public Vector2 force;
    public Vector2 velocity;
    public double epsilon;
    public static double mu=0.1;
    public static double c=0.2;

    //m=0, mass will be infinity
    public RigidBody(double m,Vector2 f,Vector2 v,double e)
    {
        if(m==0)
        {
            mass=0;
            massInv=0;
        }
        else
        {
            mass=m;
            massInv=1.0/mass;
        }
        force=new Vector2(f);
        velocity=new Vector2(v);
        if(m==0)
            velocity=new Vector2(0,0);
        epsilon=e;
    }
    public static void setMu(double u)
    {
        mu=u;
    }

    public static void setC(double c)
    {
        RigidBody.c=c;
    }

    //Force add on a new Velocity manually
    public void appendVelocity(Vector2 v)
    {
        velocity.addby(v);
    }

    //Update by ticks(s) abstract
    public abstract void update(double ticks);

    //Update by ticks(s) abstract
    public abstract void update(Vector2 vec);

}
