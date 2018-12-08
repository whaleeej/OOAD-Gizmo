package Build.View;

import Build.Controller.BuildController;
import Build.Model.Gizmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildToolbar extends JPanel
{
    //gizmo
    private JButton[] shapeButtons;
    private JButton[] colorButtons;
    //opration
    private JButton chooseButton;
    private JButton largeButton;
    private JButton smallButton;
    private JButton deleteButton;
    private JButton rotateButton;
    private JButton bindButton;
    private JLabel bindLabel;
    private JButton movableButton;
    private JLabel movableLabel;
    //setting
    private JButton gravityButton;
    private JButton frictionButton;
    private JButton airFrictionButton;
    //run
    private JButton gamingButton;

    public BuildToolbar()
    {
        super();
        this.setLayout(null);
        setGizmoButton();
        setColorButton();
        setOperationButton();
        setSettingButton();
        setRunButton();
    }

    private void setColorButton()
    {
        Color[] colorArray = Gizmo.colorArray;
        colorButtons = new JButton[colorArray.length];
        for (int i = 0; i < colorArray.length; i++)
        {
            colorButtons[i] = new JButton();
            colorButtons[i].setBackground(colorArray[i]);
            colorButtons[i].setBounds(10 + i % 3 * 35, 250 + i / 3 * 40, 30, 30);
            this.add(colorButtons[i]);
        }
    }

    private void setGizmoButton()
    {
        String[] shapeArray = Gizmo.shapeArray;
        shapeButtons = new JButton[shapeArray.length];
        for(int i = 0; i < shapeArray.length; i++)
        {
            shapeButtons[i] = new JButton(new ImageIcon(getClass().getResource("/Icon/gizmo/"+shapeArray[i]+".png")));
            shapeButtons[i].setActionCommand(shapeArray[i]);
            shapeButtons[i].setContentAreaFilled(false);
//            shapeButtons[i].setBorderPainted(false);
            if(i == 0)
                shapeButtons[i].setBounds(45,30,30,30);
            else
                shapeButtons[i].setBounds(10+(i-1)%3*35,100+(i-1)/3*40,30,30);
            this.add(shapeButtons[i]);
        }
    }

    private void setOperationButton()
    {
        chooseButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Choose.png")));
        chooseButton.setContentAreaFilled(false);
        chooseButton.setBounds(10, 400, 30, 30);
        rotateButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/RotateRight.png")));
        rotateButton.setActionCommand("RotateRight");
        rotateButton.setContentAreaFilled(false);
        rotateButton.setBounds(45, 400, 30, 30);
        deleteButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Delete.png")));
        deleteButton.setActionCommand("Delete");
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBounds(80, 400, 30, 30);
        largeButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/+.png")));
        largeButton.setActionCommand("+");
        largeButton.setContentAreaFilled(false);
        largeButton.setBounds(10, 440, 30, 30);
        bindButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Bind.png")));
        bindButton.setActionCommand("Bind");
        bindButton.setContentAreaFilled(false);
        bindButton.setBounds(45, 440, 30, 30);
        bindLabel = new JLabel("   z");
        bindLabel.setBounds(80,440,30,30);

        smallButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/-.png")));
        smallButton.setActionCommand("-");
        smallButton.setContentAreaFilled(false);
        smallButton.setBounds(10, 480, 30, 30);
        movableButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Movable.png")));
        movableButton.setActionCommand("Movable");
        movableButton.setContentAreaFilled(false);
        movableButton.setBounds(45, 480, 30, 30);
        movableLabel = new JLabel("  √");
        movableLabel.setBounds(80,480,30,30);

        this.add(chooseButton);
        this.add(rotateButton);
        this.add(movableButton);
        this.add(largeButton);
        this.add(smallButton);
        this.add(deleteButton);
        this.add(bindButton);
        this.add(bindLabel);
        this.add(movableLabel);
    }

    private void setSettingButton()
    {
        gravityButton = new JButton(new ImageIcon(getClass().getResource("/Icon/setting/Gravity.png")));
        gravityButton.setContentAreaFilled(false);
        gravityButton.setBounds(10, 550, 30, 30);
        frictionButton = new JButton(new ImageIcon(getClass().getResource("/Icon/setting/Friction.png")));
        frictionButton.setContentAreaFilled(false);
        frictionButton.setBounds(10, 590, 30, 30);
        airFrictionButton = new JButton(new ImageIcon(getClass().getResource("/Icon/setting/AirFriction.png")));
        airFrictionButton.setContentAreaFilled(false);
        airFrictionButton.setBounds(10,630,30,30);

        this.add(gravityButton);
        this.add(frictionButton);
        this.add(airFrictionButton);
    }

    private void setRunButton()
    {
        gamingButton = new JButton("Run");
        gamingButton.setBounds(30, 700, 60, 60);
        this.add(gamingButton);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setFont(new Font("微软雅黑", 1, 15));
        //0
        g.drawString("Ball", 5, 20);
        //30-60
        g.drawLine(5, 70, 115, 70);
        g.drawString("Gizmo", 5, 90);
        //100-210
        g.drawLine(5, 220, 115, 220);
        g.drawString("Color", 5, 240);
        //250-360
        g.drawLine(5, 370, 115, 370);
        g.drawString("Operation", 5, 390);
        //400-510
        g.drawLine(5, 520, 115, 520);
        g.drawString("Setting", 5, 540);
        //550-660
        g.drawLine(5, 670, 115, 670);
        g.drawString("Run", 5, 690);
        //700-760
    }

    public JButton getGamingButton()
    {
        return gamingButton;
    }

    public JButton[] getColorButtons()
    {
        return colorButtons;
    }

    public JButton[] getShapeButtons()
    {
        return shapeButtons;
    }

    public JButton getChooseButton()
    {
        return chooseButton;
    }

    public JButton getMovableButton()
    {
        return movableButton;
    }

    public JButton getLargeButton()
    {
        return largeButton;
    }

    public JButton getSmallButton()
    {
        return smallButton;
    }

    public JButton getDeleteButton()
    {
        return deleteButton;
    }

    public JButton getRotateButton()
    {
        return rotateButton;
    }

    public JButton getBindButton()
    {
        return bindButton;
    }

    public JLabel getBindLabel()
    {
        return bindLabel;
    }

    public JLabel getMovableLabel()
    {
        return movableLabel;
    }

    public JButton getGravityButton()
    {
        return gravityButton;
    }

    public JButton getFrictionButton()
    {
        return frictionButton;
    }

    public JButton getAirFrictionButton()
    {
        return airFrictionButton;
    }
}
