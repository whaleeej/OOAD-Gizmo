package Game.Model;

import Game.Controller.GameController;
import Game.Controller.PointListener;
import Physics.Controller.PhysicsEngineController;
import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.AABB;
import Physics.Model.Elements.Circle;
import Physics.Model.Elements.RigidBody;
import Physics.Model.Elements.Trigger;

import javax.swing.*;

public class Absorber extends AABB implements Trigger {
    //If circle collide this box
    //Smash will trigger the onTriggerEnter function

    //Set listener
    GameController gameController=null;
    PointListener pointListener=null;
    JTextField pointText=new JTextField(0);

    public int point;
    public int scale;

    public Absorber(GameController gameController,PointListener pointListener,double x_min, double y_min, double width, double height,int scale) {
        super(0, new Vector2(0,0), new Vector2(0,0), 1, x_min, y_min, width, height);
        this.gameController=gameController;
        this.pointListener=pointListener;
        this.point = 0;
        this.scale =scale;
    }

    @Override
    public void onTriggerEnter(RigidBody rigidBody) {
        if(rigidBody instanceof Circle)
        {
            //Destroy Circle
            PhysicsEngineController.getPhysicsEngineController().destroyRigid(rigidBody);
            pointListener.updatePoint(this);
        }
    }
}
