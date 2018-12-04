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
    private JTextField point;


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
        point=new JTextField("0",2);
        this.add(point);

    }

    //Edicted by vicki
    public void changPointText(int totlePoint)
    {
        this.point.setText(String.valueOf(totlePoint));
    }

    public JButton getBuildingButton()
    {
        return buildingButton;
    }
}
