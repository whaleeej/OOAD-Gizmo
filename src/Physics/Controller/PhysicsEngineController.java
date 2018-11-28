package Physics.Controller;

import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class   PhysicsEngineController implements ActionListener {
    public static int ticks=25; //frame rate is 40 FPS;

    Vector<RigidBody> rigids;
    Vector<RigidBody> flippers;
    private Timer timer;
    public  PhysicsRender phyRender;


    @Override
    public void actionPerformed(ActionEvent e) {
        phyRender.updateGeometries(rigids);
        phyRender.repaint();
        updateLocation();
    }

    public PhysicsEngineController(PhysicsRender pr)
    {
        //Initial the worlds
        rigids=new Vector<RigidBody>();
        worldInitial();

        //give renderer
        phyRender=pr;

        //Start the render
        timer = new Timer(ticks,this);
        timer.start();

    }

    //Update the Location of all objs in the world by three steps
    public void updateLocation()
    {
        for (int k = 1; k <=5 ; k++) {
            int millsec=ticks/5;
            if(k<=ticks%5) millsec++;
            for(RigidBody rigid:rigids)
            {
                rigid.update(millsec/1000.0);
            }
            //collision handler
            for(int i=0;i<rigids.size();i++)
            {
                for(int j=i+1;j<rigids.size();j++)
                {
                    //aa
                    if(!rigids.elementAt(i).isKinematic||!rigids.elementAt(j).isKinematic)
                        new ImpulseResolutionModel(rigids.elementAt(i),rigids.elementAt(j));
                }
            }
        }
    }

    //Initial physical objects in the whole world.
    public void worldInitial()
    {
        Vector2 gravity=new Vector2(0,100);
        Vector2 gravity0=new Vector2(0,0);
        RigidBody rigid;

        //walls
        rigid=new AABB(0,gravity0,new Vector2(0,0),1.0,0,0,1,63,false);//Left
        ((Texture)rigid).setColor(new Color(0,0,0));
        rigids.add(rigid);
        rigid=new AABB(0,gravity0,new Vector2(0,0),1.0,0,63,128,1,false);//Middle
        ((Texture)rigid).setColor(new Color(0,0,0));
        rigids.add(rigid);
        rigid=new AABB(0,gravity0,new Vector2(0,0),1.0,127,0,1,63,false);//Right
        ((Texture)rigid).setColor(new Color(0,0,0));
        rigids.add(rigid);
        rigid=new AABB(0,gravity0,new Vector2(0,0),1.0,0,-1,128,1,false);//Up
        ((Texture)rigid).setColor(new Color(0,0,0));
        rigids.add(rigid);

        //Circles
        rigid=new Circle(5,gravity,new Vector2(-5,5),0.7,100,0,2,false);
        ((Texture)rigid).setColor(new Color(255,0,0));
        rigids.add(rigid);
        rigid=new Circle(5,gravity,new Vector2(2,1),0.9,70,0,2,false);
        ((Texture)rigid).setColor(new Color(0,255,0));
        rigids.add(rigid);
        rigid=new Circle(5,gravity,new Vector2(-2,-1),0.9,65,10,2,false);
        ((Texture)rigid).setColor(new Color(255,0,255));
        rigids.add(rigid);

        //Boxes
        rigid=new AABB(20,gravity0,new Vector2(-10,0),0.7,90,20,2,3,true);
        ((Texture)rigid).setColor(new Color(255,0,0));
        rigids.add(rigid);
        rigid=new AABB(20,gravity0,new Vector2(10,0),0.7,20,20,5,4,true);
        ((Texture)rigid).setColor(new Color(0,255,0));
        rigids.add(rigid);
        rigid=new AABB(20,gravity0,new Vector2(0,0),0.7,100,55,4,2,true);
        ((Texture)rigid).setColor(new Color(255,0,255));
        rigids.add(rigid);

        //Flippers
        rigid=new RotationRectangle(54,50,2,10,false,'z');
        rigids.add(rigid);
        rigid=new RotationRectangle(76,50,2,10,true,'x');
        rigids.add(rigid);
    }
}
