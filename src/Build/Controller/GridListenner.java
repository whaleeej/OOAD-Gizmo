package Build.Controller;

import Build.Model.Gizmo;
import Build.Model.Grid;
import Build.View.BuildToolbar;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Build.Controller.BuildController.Command.*;

public class GridListenner implements MouseInputListener
{
    private BuildController buildController;
    private Grid grid;
    private ArrayList<Gizmo> gizmos;
    private Gizmo addingGizmo;
    private Gizmo tempGizmo;
    private int markX;
    private int markY;
    private int spaceX;
    private int spaceY;
    private int scale;
    private JButton movableButton;
    private JButton bindButton;
    private JLabel movableLable;
    private JLabel bindLable;

    public GridListenner(BuildController buildController)
    {
        this.buildController = buildController;
        grid = buildController.getBuildRender().getGrid();
        gizmos = buildController.getBuildRender().getGizmos();
        addingGizmo = buildController.getBuildRender().getAddingGizmo();
        scale = Grid.SCALE;
        BuildToolbar buildToolbar = buildController.getBuildToolbar();
        movableButton = buildToolbar.getMovableButton();
        movableLable = buildToolbar.getMovableLabel();
        bindButton = buildToolbar.getBindButton();
        bindLable = buildToolbar.getBindLabel();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        switch (buildController.getCommand())
        {
            case Add:
                if (grid.check(addingGizmo))
                {
                    Gizmo gizmo = new Gizmo(addingGizmo.getShape(), addingGizmo.getColor(), addingGizmo.getX(), addingGizmo.getY());
                    grid.cover(gizmo);
                    gizmos.add(gizmo);
                } else
                {
                    JOptionPane.showMessageDialog(buildController.getMainScene(), "This grid has been covered by other gizmo!");
                }
                break;
            case Choose:
                int x = e.getX() / scale;
                int y = e.getY() / scale;
                tempGizmo = grid.getGizmo(x, y);
                buildController.setChosenGizmo(tempGizmo);
                if(tempGizmo == null)
                    return;
                markX = tempGizmo.getX();
                markY = tempGizmo.getY();
                spaceX = x-markX;
                spaceY = y-markY;
                movableLable.setText("  "+(tempGizmo.isMovable()?"√":"×"));
                if(tempGizmo.movableCanChange())
                    movableButton.setEnabled(true);
                else
                    movableButton.setEnabled(false);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (buildController.getCommand().equals(Choose))
        {
            if(tempGizmo != null)
            {
                if (grid.check(tempGizmo))
                {
                    grid.replace(tempGizmo);
                } else
                {
                    tempGizmo.setX(markX);
                    tempGizmo.setY(markY);
                    JOptionPane.showMessageDialog(buildController.getMainScene(), "This grid has been covered by other gizmo!");
                }
            }
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if (buildController.getCommand().equals(Add))
        {
            addingGizmo.setX(-1);
            addingGizmo.setY(-1);
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (buildController.getCommand().equals(Choose))
        {
            if(tempGizmo == null)
                return;
            int newX = e.getX() / scale;
            int newY = e.getY() / scale;
            tempGizmo.setX(newX-spaceX);
            tempGizmo.setY(newY-spaceY);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (buildController.getCommand().equals(Add))
        {
            int x = e.getX();
            int y = e.getY();
            addingGizmo.setX(x/ scale);
            addingGizmo.setY(y/ scale);
            repaint();
        }
    }

    private void repaint()
    {
        buildController.getBuildRender().repaint();
    }
}
