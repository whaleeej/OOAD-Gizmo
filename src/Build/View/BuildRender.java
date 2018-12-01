package Build.View;

import javax.swing.*;
import java.awt.*;

public class BuildRender extends JPanel
{
    public BuildRender()
    {
        super();
        //this.setBackground(Color.red);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for(int i = 0; i <= 1100; i += 50)
            g.drawLine(i,0,i,800);
        for(int i = 0; i <= 800; i += 50)
            g.drawLine(0,i,1100,i);
    }
}
