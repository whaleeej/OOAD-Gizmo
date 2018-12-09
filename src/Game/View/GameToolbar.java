package Game.View;

import javax.swing.*;
import java.awt.*;

public class GameToolbar extends JPanel
{
    private JButton buildingButton;
    private JButton startPauseButton;
    private JButton proceedButton;
    private JLabel pointText;


    public GameToolbar()
    {
        super();
        this.setLayout(null);
        buildingButton = new JButton(new ImageIcon(getClass().getResource("/Icon/game/back.png")));
        buildingButton.setBounds(10,690,100,100);
        buildingButton.setContentAreaFilled(false);
        this.add(buildingButton);
        startPauseButton = new JButton(new ImageIcon(getClass().getResource("/Icon/game/start.png")));
        startPauseButton.setBounds(10,100,100,100);
        startPauseButton.setContentAreaFilled(false);
        this.add(startPauseButton);
        proceedButton = new JButton(new ImageIcon(getClass().getResource("/Icon/game/next.png")));
        proceedButton.setContentAreaFilled(false);
        proceedButton.setBounds(10,300,100,100);
        this.add(proceedButton);

        //Edicted by vicki
        pointText=new JLabel("Score: 0");
        pointText.setBounds(10,500,100,40);
        pointText.setFont(new Font("宋体",1,20));
        this.add(pointText);


    }

    public void setRunning(boolean running)
    {
        if(running)
            startPauseButton.setIcon(new ImageIcon(getClass().getResource("/Icon/game/pause.png")));
        else
            startPauseButton.setIcon(new ImageIcon(getClass().getResource("/Icon/game/start.png")));
    }

    public JButton getStartPauseButton()
    {
        return startPauseButton;
    }

    public JButton getProceedButton() {
        return proceedButton;
    }

    public JLabel getPointText() { return pointText; }

    public JButton getBuildingButton()
    {
        return buildingButton;
    }

}
