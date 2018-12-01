package Game.View;

import javax.swing.*;

public class GameToolbar extends JPanel
{
    private JButton buildingButton;

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getProceedButton() {
        return proceedButton;
    }

    private JButton startButton;
    private JButton pauseButton;
    private JButton proceedButton;

    public GameToolbar()
    {
        super();
        buildingButton = new JButton("Build");
        this.add(buildingButton);
        startButton = new JButton("Start");
        this.add(startButton);
        pauseButton = new JButton("Pause");
        this.add(pauseButton);
        proceedButton = new JButton("Proceed");
        this.add(proceedButton);

    }



    public JButton getBuildingButton()
    {
        return buildingButton;
    }
}
