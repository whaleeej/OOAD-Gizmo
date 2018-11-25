package ViewLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Scene extends JFrame
{
    private static final long serialVersionUID = 1L;// serialVersionUID唯一的可串行化的版本
    private GeoRender geoRender = null;

    public Scene(JPanel jp)
    {
        super("GizmoScene");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1300, 720);
        this.setLocationRelativeTo(null);
        this.add(jp);
        this.setVisible(true);

    }

}
