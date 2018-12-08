package Build.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Build.Controller.BuildController.Command.Add;

public class ColorListener implements ActionListener
{
    private BuildController buildController;

    public ColorListener(BuildController buildController)
    {
        this.buildController = buildController;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        buildController.setCommand(Add);
        buildController.getBuildRender().getAddingGizmo().setColor(((JButton)e.getSource()).getBackground());
    }
}
