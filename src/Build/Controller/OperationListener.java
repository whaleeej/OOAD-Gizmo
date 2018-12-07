package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Build.Controller.BuildController.Command.Add;

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
        if(buildController.getCommand().equals(Add))
        {
            JOptionPane.showMessageDialog(buildController.getMainScene(),"Use choose button to choose a gizmo first!");
            return;
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
            case "Rotate":
                chosenGizmo.rotate();
                break;
            case "+":
                chosenGizmo.resize(true);
                if (grid.check(chosenGizmo))
                {
                    grid.replace(chosenGizmo);
                } else
                {
                    chosenGizmo.resize(false);
                    JOptionPane.showMessageDialog(buildController.getMainScene(), "This grid has been covered by other gizmo!");
                }
                break;
            case "-":
                if (chosenGizmo.getSize()>1)
                {
                    chosenGizmo.resize(false);
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
                buildController.getBuildToolbar().getMovableLabel().setText("  "+(chosenGizmo.isMovable()?"√":"×"));
                break;
        }
        buildController.getBuildRender().repaint();
    }
}
