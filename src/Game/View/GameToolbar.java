package Game.View;

import javax.swing.*;

public class GameToolbar extends JPanel
{
    public JButton getStartButton() { return startButton; }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getProceedButton() {
        return proceedButton;
    }

    public JTextField getPointText() { return pointText; }

    public JButton getBuildingButton()
    {
        return buildingButton;
    }

    private JButton buildingButton;
    private JButton startButton;
    private JButton pauseButton;
    private JButton proceedButton;
    private JTextField pointText;


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

        //Edicted by vicki
        this.add(new JLabel("总分"));
        pointText=new JTextField("0",2);
        this.add(pointText);

    }
}
