package Physics.Controller;

import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class   PhysicsEngineController implements ActionListener {
    public static int ticks=20; //frame rate is 40 FPS;

    Vector<RigidBody> rigids;
    RigidBody rigid;
    Vector<RigidBody> flippers;
    private Timer timer;
    public  PhysicsRender phyRender;
    Vector2 gravity=new Vector2(0,100);
    Vector2 gravity0=new Vector2(0,0);

    @Override
    public void actionPerformed(ActionEvent e) {
        phyRender.updateGeometries(rigids);
        phyRender.repaint();
        updateLocation();
    }

    public PhysicsEngineController(PhysicsRender pr)
    {
        //give renderer
        phyRender=pr;
        //Initial the worlds
        rigids=new Vector<RigidBody>();
    }

    public void startPhysicsRunning()
    {
        //Start the render
        if(timer==null)
            timer = new Timer(ticks,this);
        timer.start();
    }
    public void pausePhysicsRunning()
    {
        //Start the render
        if(timer==null)
            return;
        timer.stop();
    }
    public void proceedOneFrame()
    {
        phyRender.updateGeometries(rigids);
        phyRender.repaint();
        updateLocation();
    }


    public void initialGravity(double g)
    {
        gravity=new Vector2(0,g);
        gravity0=new Vector2(0,0);
    }

    //Update the Location of all objs in the world by three steps
    public void updateLocation() {
        for (int k = 1; k <= 3; k++) {
            int millsec = ticks / 3;
            if (k <= ticks % 3) millsec++;
            for (RigidBody rigid : rigids) {
                rigid.update(millsec / 1000.0);
            }
            //collision handler
            for (int i = 0; i < rigids.size(); i++) {
                for (int j = i + 1; j < rigids.size(); j++) {
                        new ImpulseResolutionModel(rigids.elementAt(i), rigids.elementAt(j),millsec/1000.0);
                }
            }
        }
    }

    /////////add RigidBodies by users

    /**
     * Add walls
     * @param x_min
     * @param y_min
     * @param width
     * @param height
     */
    public void initialWall(double x_min,double y_min,double width,double height)
    {
        rigid=new AABB(0,gravity0,new Vector2(0,0),1.0,x_min,y_min,width,height);//Left
        ((Texture)rigid).setColor(new Color(0,0,0));
        rigids.add(rigid);
    }

    /**
     * add Balls
     * @param m
     * @param v_x
     * @param v_y
     * @param e
     * @param x
     * @param y
     * @param r
     * @param color
     */
    public void initialBall(double m,double v_x,double v_y,double e,double x,double y,double r,Color color )
    {
        Vector2 force=new Vector2(gravity);
        force.multiplyBy(m);
        rigid=new Circle(m,force,new Vector2(v_x,v_y),e,x,y,r);
        ((Texture)rigid).setColor(color);
        rigids.add(rigid);
    }

    /**
     * add Boxes
     * @param m
     * @param v_x
     * @param v_y
     * @param e
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public void initialBox(double m,double v_x,double v_y,double e,double x,double y,double width,double height,Color color)
    {
        Vector2 force=new Vector2(gravity0);
        force.multiplyBy(m);
        rigid=new AABB(m,force,new Vector2(v_x,v_y),e,x,y,width,height);
        ((Texture)rigid).setColor(color);
        rigids.add(rigid);
    }

    /**
     * add Flipper
     * @param x
     * @param y
     * @param w
     * @param l
     * @param isLeft
     * @param key
     */
    public void initialRotationRectangle(double x,double y,double w,double l,boolean isLeft,char key)
    {
        rigid=new RotationRectangle(x,y,w,l,isLeft,key);
        rigids.add(rigid);
    }

    public void initialTriangle(double m,double v_x,double v_y,double e,double x,double y,double len1,double len2,int type,Color color)
    {
        Vector2 force=new Vector2(gravity0);
        force.multiplyBy(m);
        rigid=new Triangle(m,force,new Vector2(v_x,v_y),e,x,y,len1,len2,type);
        ((Texture)rigid).setColor(color);
        rigids.add(rigid);
    }

}
