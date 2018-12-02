package Game.Model;

import Physics.Controller.PhysicsEngineController;
import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.AABB;
import Physics.Model.Elements.Circle;
import Physics.Model.Elements.RigidBody;
import Physics.Model.Elements.Trigger;

public class SmashBox extends AABB implements Trigger {
    //If circle collide this box
    //Smash will trigger the onTriggerEnter function
    public SmashBox(double x_min, double y_min, double width, double height) {
        super(0, new Vector2(0,0), new Vector2(0,0), 1, x_min, y_min, width, height);
    }

    @Override
    public void onTriggerEnter(RigidBody rigidBody) {
        if(rigidBody instanceof Circle)
        {
            //Destroy Circle
            PhysicsEngineController.getPhysicsEngineController().destroyRigid(rigidBody);
        }
    }
}
