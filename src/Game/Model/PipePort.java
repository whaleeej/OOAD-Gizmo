package Game.Model;

import Physics.Controller.PhysicsEngineController;
import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.AABB;
import Physics.Model.Elements.Circle;
import Physics.Model.Elements.RigidBody;
import Physics.Model.Elements.Trigger;
import java.awt.*;

public class PipePort extends AABB implements Trigger  {
    public PipePort(double m, Vector2 f, Vector2 v, double e, double x_min, double y_min, double width, double height) {
        super(0, new Vector2(0,0), new Vector2(0,0), 1, x_min, y_min, width, height);
    }

    //bind with the other one
    public PipePort pp;
    public void bindPort(PipePort pp){this.pp=pp;}

    //the direction of the ball geting out from this port
    public Vector2 direction;
    public void setDirection(int x,int y){this.direction= new Vector2(x,y);}

    @Override
    public void onTriggerEnter(RigidBody rigidBody) {
        if(rigidBody instanceof Circle) {
            //for test
             //PhysicsEngineController.getPhysicsEngineController().destroyRigid(rigidBody);

            //change the direction of velocity
            double vel = rigidBody.velocity.length();
            rigidBody.velocity.x = vel * pp.direction.x;
            rigidBody.velocity.y = vel * pp.direction.y;

            //change the position
            switch ((int) (2*pp.direction.x - pp.direction.y)) {
                case 2://right port
                    ((Circle) rigidBody).position.x = pp.max.x+((Circle) rigidBody).radius+0.01;
                    ((Circle) rigidBody).position.y = (pp.min.y + pp.max.y) * 0.5;
                    break;
                case -2://left port
                    ((Circle) rigidBody).position.x = pp.min.x-((Circle) rigidBody).radius-0.01;
                    ((Circle) rigidBody).position.y = (pp.min.y + pp.max.y) * 0.5;
                    break;
                case 1://top port
                    ((Circle) rigidBody).position.y = pp.min.y-((Circle) rigidBody).radius-0.01;
                    ((Circle) rigidBody).position.x = (pp.min.x + pp.max.x) * 0.5;
                    break;
                case -1://bottom port
                    ((Circle) rigidBody).position.y = pp.max.y+((Circle) rigidBody).radius+0.01;
                    ((Circle) rigidBody).position.x = (pp.min.x + pp.max.x) * 0.5;
            }
        }
    }

}
