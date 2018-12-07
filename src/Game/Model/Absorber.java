package Game.Model;

import Game.Controller.GameController;
import Game.Controller.PointListener;
import Physics.Controller.PhysicsEngineController;
import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.Rectangle;
import Physics.Model.Elements.Circle;
import Physics.Model.Elements.RigidBody;
import Physics.Model.Elements.Trigger;

import javax.swing.*;
import java.awt.*;

public class Absorber extends Rectangle implements Trigger {
    //If circle collide this box
    //Smash will trigger the onTriggerEnter function

    //Set listener
    GameController gameController=null;
    public int scale;

    public Absorber(GameController gameController, double x_min, double y_min, double width, double height, int scale, Color color) {
        super(0, new Vector2(0,0), new Vector2(0,0), 1, x_min, y_min, width, height);
        this.setColor(color);
        this.gameController=gameController;
        this.scale =scale;
    }

    @Override
    public void onTriggerEnter(RigidBody rigidBody) {
        if(rigidBody instanceof Circle)
        {
            //Destroy Circle
            PhysicsEngineController.getPhysicsEngineController().destroyRigid(rigidBody);
            gameController.updateTotlePoint(scale);
        }
    }

    @Override
    public Shape getShape() {
        return Shape.Absorber;
    }
}
