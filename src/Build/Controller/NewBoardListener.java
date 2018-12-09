package Build.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewBoardListener implements ActionListener
{
    private BuildController buildController;

    public NewBoardListener(BuildController buildController)
    {
        this.buildController = buildController;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        buildController.getBuildRender().getGizmos().clear();
        buildController.getBuildRender().getGrid().clear();
        buildController.setChosenGizmo(null);
        buildController.getBuildRender().repaint();
    }
}
