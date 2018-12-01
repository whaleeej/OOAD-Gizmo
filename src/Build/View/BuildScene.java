package Build.View;

import javax.swing.*;
import java.awt.*;

public class BuildScene extends JPanel
{
    public BuildScene()
    {
        super();
        this.setBackground(Color.green);
        JButton hello = new JButton("Hello");
        this.add(hello);
    }
}
