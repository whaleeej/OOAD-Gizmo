package Build.Controller;

import Build.Model.Gizmo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.sqrt;

public class SaveBoardListner implements ActionListener {
    private BuildController buildController;
    private FileWriter fw;//file output

    public SaveBoardListner(BuildController buildController) {
        this.buildController = buildController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //timer for file name
        // Select file
        File save=new File("./save");
        if(!save.exists())
        {
            save.mkdir();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
        try {
            fw = new FileWriter(save.getAbsolutePath()+"/"+df.format(new Date()) + ".gizmo");
            ArrayList<Gizmo> gizmos = buildController.getBuildRender().getGizmos();
            for(int i=0;i<gizmos.size();i++)
            {
                toSave(gizmos.get(i));
            }
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    public void toSave(Gizmo g) throws IOException {
        if(fw==null) return;
        fw.write(g.getShape()+" "+g.getColor().getRed()+" "+g.getColor().getGreen()+" "+g.getColor().getBlue()+ " "+
                g.getX()+" "+g.getY()+" "+g.getSize()+" "+g.getRotation()+" "+
                (int)(g.getKey()-'a')+" "+(g.isMovable()?1:0));
        fw.write("\r\n");
    }
    //    Gizmo(String shape, Color color, int x, int y, int size, int rotation, char key, boolean movable)
}