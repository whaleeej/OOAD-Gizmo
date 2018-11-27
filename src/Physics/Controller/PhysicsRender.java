package Physics.Controller;

import Physics.Model.Elements.RigidBody;

import java.util.Vector;

public interface PhysicsRender {
    public void updateGeometries(Vector<RigidBody> g);

    public void repaint() ;
}
