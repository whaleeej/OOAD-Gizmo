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
    private JMenuItem load;
    private JMenuItem save;

    public Scene(JPanel jp)
    {
        super("GizmoScene");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1300, 720);
        this.setLocationRelativeTo(null);
        this.add(jp);

        initialise();
        this.setVisible(true);

    }

    private void initialise() {
       addMenuBar();
    }

    private void addMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem newBoard = new JMenuItem("New");
        //newBoard.addActionListener(listeners.get("nBL"));
        load = new JMenuItem("Load");
        //load.addActionListener(listeners.get("lL"));
        save = new JMenuItem("Save");
        //save.addActionListener(listeners.get("sL"));
        JMenuItem exit = new JMenuItem("Exit");
        //exit.addActionListener(listeners.get("eL"));

        menu.add(newBoard);
        menu.add(load);
        menu.add(save);
        menu.addSeparator();
        menu.add(exit);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }
}
