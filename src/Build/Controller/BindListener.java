package Build.Controller;

import Build.Model.Gizmo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Build.Controller.BuildController.Command.*;

public class BindListener implements KeyListener
{
    private BuildController buildController;

    public BindListener(BuildController buildController)
    {
        this.buildController = buildController;
    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(buildController.getCommand().equals(Bind))
        {
            //System.out.println("P "+e.getKeyChar()+ " "+e.getKeyCode());
            int key = e.getKeyCode();
            if(key > 64 && key < 91)
            {
                Gizmo chooseGizmo = buildController.getChosenGizmo();
                chooseGizmo.setKey(e.getKeyChar());
                buildController.getBuildToolbar().getBindLabel().setText("key:"+chooseGizmo.getKey());
            }else
            {
                JOptionPane.showMessageDialog(buildController.getMainScene(), "Please type a key between 'a' and 'z'!");
            }
            buildController.setCommand(Choose);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
