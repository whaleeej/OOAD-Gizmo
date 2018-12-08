package Build.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Build.Controller.BuildController.Command.Choose;

public class ChooseListener implements ActionListener
{
    private BuildController buildController;

    public ChooseListener(BuildController buildController)
    {
        this.buildController = buildController;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        buildController.setCommand(Choose);
    }
}
