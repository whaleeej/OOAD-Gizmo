package Build.Controller;

import Build.Model.Gizmo;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadBoardListner implements ActionListener {
    private BuildController buildController;

    public LoadBoardListner(BuildController buildController)
    {
        this.buildController = buildController;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        // Select file
        File save=new File("./save");
        if(!save.exists())
        {
            save.mkdir();
        }
        JFileChooser jfc=new JFileChooser(save);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if(!file.getAbsolutePath().contains(".gizmo"))
        {
            buildController.getBuildRender().getGizmos().clear();
            buildController.getBuildRender().getGrid().clear();
            buildController.setChosenGizmo(null);
            buildController.getBuildRender().repaint();
        }
        else{
            buildController.getBuildRender().getGizmos().clear();
            buildController.setChosenGizmo(null);
            try {
                BufferedReader br=null;
                br=new BufferedReader(new FileReader(file));
                String str;
                while( (str=br.readLine())!=null)
                {
                    toload(str);
                }
                br.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            buildController.getBuildRender().repaint();
            buildController.getBuildRender().getGrid().clear();
            buildController.getBuildRender().getGrid().cover(buildController.getBuildRender().getGizmos());
        }
    }

    public void toload(String str)
    {
        String[] splited = str.split("\\s+");
        if(splited.length!=10) return;
        Color color=new Color(Integer.parseInt(splited[1]),Integer.parseInt(splited[2]),Integer.parseInt(splited[3]));
        int x=Integer.parseInt(splited[4]);
        int y=Integer.parseInt(splited[5]);
        int size=Integer.parseInt(splited[6]);
        int rotation=Integer.parseInt(splited[7]);
        char key=(char)(Integer.parseInt(splited[8])+'a');
        boolean movable=Integer.parseInt(splited[9])==1?true:false;
        String shape=splited[0];
        switch (shape) {
            case "Ball": {
                break;
            }
            case "Circle":{
                break;
            }
            case "Square":
                break;
            case "Triangle": {
                break;
            }
            case "Hexagon": {
                break;
            }
            case "Trapezoid": {
                break;
            }
            case "Pipe": {
                break;
            }
            case "Absorb": {
                break;
            }
            case "LeftFlipper": {
                break;
            }
            case "RightFlipper": {
                break;
            }
            default:
                return;
        }
        buildController.getBuildRender().getGizmos().add(new Gizmo(shape, color,x, y,size,rotation,key,movable));
    }


}
