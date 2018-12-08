package Build.Controller;

import Build.Model.Gizmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Build.Controller.BuildController.Command.Add;

public class ShapeListener implements ActionListener
{
    private BuildController buildController;
    private Gizmo addingGizmo;

    public ShapeListener(BuildController buildController)
    {
        this.buildController =buildController;
        addingGizmo = buildController.getBuildRender().getAddingGizmo();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        buildController.setCommand(Add);
        addingGizmo.setShape(e.getActionCommand());
        String shape = addingGizmo.getShape();
        if(shape.equals("LeftFlipper"))
            addingGizmo.setKey('z');
        else if(shape.equals("RightFlipper"))
            addingGizmo.setKey('x');
        else
            addingGizmo.setKey(' ');
        addingGizmo.setMovable(shape.equals("Ball"));
    }
}
