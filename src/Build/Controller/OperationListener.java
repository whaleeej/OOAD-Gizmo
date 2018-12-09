package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Build.Controller.BuildController.Command.*;

public class OperationListener implements ActionListener
{
    private BuildController buildController;

    public OperationListener(BuildController buildController)
    {
        this.buildController = buildController;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(buildController.getCommand().equals(Bind))
        {
            buildController.setCommand(Choose);
        }
        if(buildController.getChosenGizmo() == null)
        {
            JOptionPane.showMessageDialog(buildController.getMainScene(),"No gizmo is selected!");
            return;
        }
        Grid grid = buildController.getBuildRender().getGrid();
        Gizmo chosenGizmo = buildController.getChosenGizmo();
        switch (e.getActionCommand())
        {
            case "RotateRight":
                chosenGizmo.rotate(true);
                break;
            case "RotateLeft":
                chosenGizmo.rotate(false);
                break;
            case "+":
                chosenGizmo.resize(true);
                if(buildController.getCommand().equals(Choose))
                {
                    if (grid.check(chosenGizmo))
                    {
                        grid.replace(chosenGizmo);
                    } else
                    {
                        chosenGizmo.resize(false);
                        JOptionPane.showMessageDialog(buildController.getMainScene(), "This grid has been covered by other gizmo!");
                    }
                }
                break;
            case "-":
                if (chosenGizmo.getSize()>1)
                {
                    chosenGizmo.resize(false);
                    if(buildController.getCommand().equals(Choose))
                       grid.replace(chosenGizmo);
                } else
                {
                    JOptionPane.showMessageDialog(buildController.getMainScene(), "This gizmo can't be smaller!");
                }
                break;
            case "Delete":
                ArrayList<Gizmo> gizmos = buildController.getBuildRender().getGizmos();
                gizmos.remove(chosenGizmo);
                grid.remove(chosenGizmo);
                buildController.setChosenGizmo(null);
                break;
            case "Movable":
                chosenGizmo.changeMovable();
                buildController.getBuildToolbar().setMovableIcon(chosenGizmo.isMovable());
                break;
            case "Bind":
                buildController.setCommand(Bind);
                break;
        }
        buildController.getBuildRender().repaint();
    }
}
