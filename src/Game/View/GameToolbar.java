package Game.View;

import javax.swing.*;

public class GameToolbar extends JPanel
{
    private JButton buildingButton;

    public GameToolbar()
    {
        super();
        buildingButton = new JButton("Build");
        this.add(buildingButton);
    }

    public JButton getBuildingButton()
    {
        return buildingButton;
    }
}
