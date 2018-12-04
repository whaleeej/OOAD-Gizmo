package Physics.Controller;

import Physics.Model.Computation.MathD;
import Physics.Model.Computation.Matrix2;
import Physics.Model.Computation.OverlapModel;
import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class ImpulseResolutionController {
    //When get Penetration and normal from Overlap Model, manage the vel and position transition
    RigidBody A;
    RigidBody B;
    double penetration;
    Vector2 normal;
    double ticks;
    public boolean isCollided;
    public ImpulseResolutionController(RigidBody a, RigidBody b, double ticks)
    {
        isCollided=false;
        if(a instanceof Circle)
        {
            //if There is a circle, overlap model requires circle at the second place;
            A=b;
            B=a;
        }
        else{
            A=a;
            B=b;
        }
        this.ticks=ticks;

        OverlapModel om=new OverlapModel(A,B);//Use Overlap model to calculate AB pentration and normal
        isCollided=om.isCollided();
        if(isCollided)
        {
            normal=om.getNormal();
            penetration=om.getPenetration();
            ResolveCollision();
            PositionalCorrection();
        }
    }


    void PositionalCorrection( )
    {
        if(A.massInv+B.massInv==0) return;
        double percent = 0.2; // usually 20% to 80%
        double slop = 0.01; // usually 0.01 to 0.1
        double thres= (penetration - slop)>= 0.0?(penetration - slop):0.0;
        double coeff= thres / (A.massInv + B.massInv) * percent;
        Vector2 correction =Vector2.multiply(normal, coeff);
//        if(A.massInv!=0&&B.massInv==0)
//        {
//            A.update(Vector2.multiply(correction, -A.massInv));
//            return;
//        }
//        if(A.massInv==0&&B.massInv!=0)
//        {
//            B.update(Vector2.multiply(correction, B.massInv));
//            return ;
//        }
        if(A.massInv!=0)
            A.update(Vector2.multiply(correction, -A.massInv));
        if(B.massInv!=0)
            B.update(Vector2.multiply(correction, B.massInv));


    }

    void ResolveCollision()
    {
        //For bounce velocity change
        // Calculate relative velocity
        Vector2 rv =Vector2.minus(B.velocity, A.velocity);

        // Calculate relative velocity in terms of the normal direction
        double velAlongNormal = rv.dot(normal);

        // Do not resolve if velocities are separating
        if(velAlongNormal > 0)
            return;

        // Calculate restitution
        double e = min(A.epsilon, B.epsilon);

        // Calculate impulse scalar
        double j = -(1 + e) * velAlongNormal;
        if(A.massInv==0&& B.massInv==0) {
            j /= 2;
            Vector2 impulse =Vector2.multiply(normal, j);
            A.velocity.minusby(impulse);
            B.velocity.addby(impulse);
        }
        else
        {
            j /=( A.massInv + B.massInv);
            // Apply impulse
            Vector2 impulse =Vector2.multiply(normal, j);
            A.velocity.minusby(Vector2.multiply(impulse, A.massInv));
            B.velocity.addby(Vector2.multiply(impulse, B.massInv));
        }

        //for friction velocity change
        //Solve for tangent Vector
        Vector2 tangent=Vector2.minus(rv, Vector2.multiply(normal, rv.dot(normal)));

        //Solve for j tangent
        double jt=-rv.dot(tangent);
        jt=jt/(A.massInv+B.massInv);

        //Use (A.mu^2+B.mu^2)^(1/2)
        double mu=(A.mu+B.mu)/2;

        //Clamp jt to maximize friction provided by mu*j;
        Vector2 frictionImpulse;
        if(abs(jt)<abs(j)*mu)
            frictionImpulse=Vector2.multiply(tangent, jt);
        else
        {
            frictionImpulse = Vector2.multiply(tangent, j*mu);
        }
        //for a multiple ticks due to F/m=a and acceleration should be multiplied with with time interval to achieve delta velocity
        Vector2 vTangentModification=Vector2.multiply(frictionImpulse, A.massInv*ticks);
        if(A.velocity.x==0){}
        //and if delta velocity would modify the direction of original velocity
        //the velocity shoul be equal to zero(0)
        //this is the special case for discrete simulation
        //becasue friction will decrese the velocity, but will not redirecte the velocity
        else if((A.velocity.x+vTangentModification.x)*(A.velocity.x)<0)
        {
            A.velocity.x=0;
        }
        else{
            A.velocity.x=A.velocity.x+vTangentModification.x;
        }

        if(A.velocity.y==0){}
        else if((A.velocity.y+vTangentModification.y)*(A.velocity.y)<0)
        {
            A.velocity.y=0;
        }
        else{
            A.velocity.y=A.velocity.y+vTangentModification.y;
        }
        //for b
        vTangentModification=Vector2.multiply(frictionImpulse, B.massInv*ticks);

        if(B.velocity.x==0){}
        else if((B.velocity.x-vTangentModification.x)*(B.velocity.x)<0)
        {
            B.velocity.x=0;
        }
        else{
            B.velocity.x=B.velocity.x-vTangentModification.x;
        }
        if(B.velocity.y==0){}
        else if((B.velocity.y-vTangentModification.y)*(B.velocity.y)<0)
        {
            B.velocity.y=0;
        }
        else{
            B.velocity.y=B.velocity.y-vTangentModification.y;
        }

    }


}
