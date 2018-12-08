package Build.View;

import Build.Controller.BuildController;
import Build.Model.Gizmo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

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
    private JButton rotateRightButton;
    private JButton rotateLeftButton;
    private JButton bindButton;
    private JLabel bindLabel;
    private JButton movableButton;
    //setting
    private JButton gravityButton;
    private JButton frictionButton;
    private JButton airFrictionButton;
    private JSlider gravitySlider;
    private JSlider frictionSlider;
    private JSlider airFrictionSlider;
    private JLabel gravityLabel;
    private JLabel frictionLabel;
    private JLabel airFrictionLabel;
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
            shapeButtons[i].setToolTipText(shapeArray[i]);
            shapeButtons[i].setContentAreaFilled(false);
//            shapeButtons[i].setBorderPainted(false);
            if(i == 0)
                shapeButtons[i].setBounds(10,30,30,30);
            else
                shapeButtons[i].setBounds(10+(i-1)%3*35,100+(i-1)/3*40,30,30);
            this.add(shapeButtons[i]);
        }
    }

    private void setOperationButton()
    {
        chooseButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Choose.png")));
        chooseButton.setToolTipText("Choose Gizmo to operate,or drag gizmo to move.");
        chooseButton.setContentAreaFilled(false);
        chooseButton.setBounds(10, 400, 30, 30);
        rotateRightButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/RotateRight.png")));
        rotateRightButton.setToolTipText("RotateRigth（→）");
        rotateRightButton.setActionCommand("RotateRight");
        rotateRightButton.setContentAreaFilled(false);
        rotateRightButton.setBounds(80, 400, 30, 30);
        rotateLeftButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/RotateLeft.png")));
        rotateLeftButton.setToolTipText("RotateLeft(←)");
        rotateLeftButton.setActionCommand("RotateLeft");
        rotateLeftButton.setContentAreaFilled(false);
        rotateLeftButton.setBounds(45, 400, 30, 30);
        deleteButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Delete.png")));
        deleteButton.setToolTipText("Delete(Del)");
        deleteButton.setActionCommand("Delete");
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBounds(80, 440, 30, 30);
        largeButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/+.png")));
        largeButton.setToolTipText("Larger size (+)");
        largeButton.setActionCommand("+");
        largeButton.setContentAreaFilled(false);
        largeButton.setBounds(10, 440, 30, 30);
        bindButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Bind.png")));
        bindButton.setActionCommand("Bind");
        bindButton.setContentAreaFilled(false);
        bindButton.setBounds(45, 480, 30, 30);
        bindLabel = new JLabel("key:");
        bindLabel.setFont(new Font("宋体",1,10));
        bindLabel.setToolTipText("Bind.(Click and then type the key(a-z))");
        bindLabel.setBounds(80,480,30,30);

        smallButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/-.png")));
        smallButton.setToolTipText("Smaller size(-)");
        smallButton.setActionCommand("-");
        smallButton.setContentAreaFilled(false);
        smallButton.setBounds(10, 480, 30, 30);
        movableButton = new JButton(new ImageIcon(getClass().getResource("/Icon/operation/Movable.png")));
        movableButton.setActionCommand("Movable(↓)");
        movableButton.setContentAreaFilled(false);
        movableButton.setBounds(45, 440, 30, 30);


        this.add(chooseButton);
        this.add(rotateRightButton);
        this.add(rotateLeftButton);
        this.add(movableButton);
        this.add(largeButton);
        this.add(smallButton);
        this.add(deleteButton);
        this.add(bindButton);
        this.add(bindLabel);
    }

    private void setSettingButton()
    {
        gravityButton = new JButton(new ImageIcon(getClass().getResource("/Icon/setting/Gravity.png")));
        gravityButton.setContentAreaFilled(false);
        gravityButton.setActionCommand("g");
        gravityButton.setBounds(10, 550, 30, 30);
        frictionButton = new JButton(new ImageIcon(getClass().getResource("/Icon/setting/Friction.png")));
        frictionButton.setContentAreaFilled(false);
        frictionButton.setActionCommand("u");
        frictionButton.setBounds(10, 590, 30, 30);
        airFrictionButton = new JButton(new ImageIcon(getClass().getResource("/Icon/setting/AirFriction.png")));
        airFrictionButton.setContentAreaFilled(false);
        airFrictionButton.setActionCommand("c");
        airFrictionButton.setBounds(10,630,30,30);

        gravitySlider = new JSlider(0,20,10);
        gravitySlider.setBounds(45,548,65,40);
        gravitySlider.setLabelTable(gravitySlider.createStandardLabels(10));
        gravitySlider.setPaintLabels(true);
        gravitySlider.setName("g");
        frictionSlider = new JSlider(1,99,1);
        frictionSlider.setBounds(45,588,65,40);
        frictionSlider.setLabelTable(frictionSlider.createStandardLabels(49));
        frictionSlider.setPaintLabels(true);
        frictionSlider.setName("u");
        airFrictionSlider = new JSlider(1,99,1);
        airFrictionSlider.setBounds(45,628,65,40);
        airFrictionSlider.setLabelTable(airFrictionSlider.createStandardLabels(49));
        airFrictionSlider.setPaintLabels(true);
        airFrictionSlider.setName("c");

        Font font = new Font("宋体",1,10);
        gravityLabel = new JLabel("g: 10");
        gravityLabel.setBackground(Color.gray);
        gravityLabel.setBounds(10,670,30,30);
        frictionLabel = new JLabel("u: 1");
        frictionLabel.setBounds(48,670,30,30);
        airFrictionLabel = new JLabel("c: 1");
        airFrictionLabel.setBounds(84,670,30,30);

        this.add(gravityButton);
        this.add(frictionButton);
        this.add(airFrictionButton);
        this.add(gravitySlider);
        this.add(frictionSlider);
        this.add(airFrictionSlider);
        this.add(gravityLabel);
        this.add(frictionLabel);
        this.add(airFrictionLabel);
    }

    private void setRunButton()
    {
        gamingButton = new JButton("Run");
        gamingButton.setBounds(30, 740, 60, 60);
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
        //550-660+40
        g.drawLine(5, 710, 115, 710);
        g.drawString("Run", 5, 730);
        //740-770
    }

    public void setMovableIcon(boolean movable)
    {
        if(movable)
            movableButton.setIcon(new ImageIcon(getClass().getResource("/Icon/operation/Movable.png")));
        else
            movableButton.setIcon(new ImageIcon(getClass().getResource("/Icon/operation/Immovable.png")));
    }

    public void setOperation(Gizmo gizmo)
    {
        rotateLeftButton.setEnabled(!gizmo.isFlipper());
        rotateRightButton.setEnabled(!gizmo.isFlipper());
        this.setMovableIcon(gizmo.isMovable());
        movableButton.setEnabled(gizmo.movableCanChange());
        bindButton.setEnabled(gizmo.isFlipper());
        bindLabel.setText("key:"+gizmo.getKey());
    }

    public JSlider getGravitySlider()
    {
        return gravitySlider;
    }

    public JSlider getFrictionSlider()
    {
        return frictionSlider;
    }

    public JSlider getAirFrictionSlider()
    {
        return airFrictionSlider;
    }

    public JLabel getGravityLabel()
    {
        return gravityLabel;
    }

    public JLabel getFrictionLabel()
    {
        return frictionLabel;
    }

    public JLabel getAirFrictionLabel()
    {
        return airFrictionLabel;
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

    public JButton getRotateRightButton()
    {
        return rotateRightButton;
    }

    public JButton getRotateLeftButton()
    {
        return rotateLeftButton;
    }

    public JButton getBindButton()
    {
        return bindButton;
    }

    public JLabel getBindLabel()
    {
        return bindLabel;
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
