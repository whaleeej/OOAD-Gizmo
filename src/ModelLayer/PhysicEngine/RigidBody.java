package ModelLayer.PhysicEngine;

import ModelLayer.Computation.Vector2;

import java.awt.*;

//Abstract class of all objects in the Physical world
//Mass=0 -> Mass=Inf -> Mass= 0
//Eplsilon is the coefficient of Newton's Law of Restitution
public abstract class RigidBody {
    public double mass;
    public double massInv;
    public Vector2 gravity;
    public Vector2 velocity;
    public double epsilon;
    public boolean isKinematic=false;

    //m=0, mass will be infinity
    public RigidBody(double m,Vector2 g,Vector2 v,double e)
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
        gravity=new Vector2(g);
        velocity=new Vector2(v);
        epsilon=e;
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
