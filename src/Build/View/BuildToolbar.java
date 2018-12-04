package Build.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildToolbar extends JPanel
{
    private JButton gamingButton;
    private JButton ballButton;
    private JButton circleButton;
    private JButton squareButton;
    private JButton[] colorButtons;

    public BuildToolbar()
    {
        super();
        this.setLayout(null);
        ballButton = new JButton("Ball");
        ballButton.setBounds(10,20,60,30);
        circleButton = new JButton("Circle");
        circleButton.setBounds(10,60,60,30);
        squareButton = new JButton("Square");
        squareButton.setBounds(10,100,60,30);
        gamingButton = new JButton("Run");
        gamingButton.setBounds(10,740,60,30);
        this.add(ballButton);
        this.add(circleButton);
        this.add(squareButton);
        this.add(gamingButton);
        Color[] colorArray =
                { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA,
                        Color.BLACK };
        colorButtons = new JButton[colorArray.length];
        for (int i = 0; i < colorArray.length; i++)
        {
            colorButtons[i] = new JButton();
            colorButtons[i].setBackground(colorArray[i]);
            colorButtons[i].setBounds(10,400+i*40,60,30);
            this.add(colorButtons[i]);
        }
        //this.setBackground(Color.yellow);
    }

    public JButton getGamingButton()
    {
        return gamingButton;
    }

    public JButton getBallButton()
    {
        return ballButton;
    }

    public JButton getCircleButton()
    {
        return circleButton;
    }

    public JButton getSquareButton()
    {
        return squareButton;
    }
}
