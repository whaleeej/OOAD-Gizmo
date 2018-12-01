package Build.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildToolbar extends JPanel
{
    private JButton gamingButton;

    public BuildToolbar()
    {
        super();
        gamingButton = new JButton("Run");
        this.add(gamingButton);
        //this.setBackground(Color.yellow);
    }

    public JButton getGamingButton()
    {
        return gamingButton;
    }
}
