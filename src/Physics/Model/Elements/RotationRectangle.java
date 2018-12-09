package Physics.Model.Elements;

import Physics.Model.Computation.Vector2;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class RotationRectangle extends RigidBody implements Geometry, Texture{
    public Vector2 endPoint;
    public double widtd;
    public double length;
    public double radian;
    public static int roundInterval=200;

    //for all direction ,true indicates clockwise, false indicates vise clockvise
    public boolean direction=false;
    public boolean isRotate=false;
    public boolean isLeft=true;
    public boolean frozen=true;
    public Color color;

    public RotationRectangle(double endx,double endy, double w,double l,boolean isleft,char key,Color color) {

        super(0, new Vector2(0,0), new Vector2(0,0), 1);
        this.color=color;
        endPoint=new Vector2(endx,endy);
        widtd=w;
        length=l;
        radian=Math.PI/2.0;
        this.isLeft=isleft;

        //Add a Keyboard Listener
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                //System.out.println(((KeyEvent) event).getKeyChar());
                if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
                    if(!isLeft&&((KeyEvent) event).getKeyChar()==key) // right
                    {
                        direction=false;
                        isRotate=true;
                        frozen=true;
                    }
                    else if(isLeft&&((KeyEvent) event).getKeyChar()==key){ // left
                        direction=true;
                        isRotate=true;
                        frozen=true;
                    }

                }
                if (((KeyEvent) event).getID() == KeyEvent.KEY_RELEASED) {
                    if(!isLeft&&((KeyEvent) event).getKeyChar()==key) // right
                    {
                        frozen=false;
                    }
                    else if(isLeft&&((KeyEvent) event).getKeyChar()==key){ // left
                        frozen=false;
                    }

                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    @Override
    public void update(double ticks) {
        if(!isLeft) //Right direction rotate
        {
            if(isRotate)
            {
                if(direction)
                    radian+=(Math.PI/2.0/roundInterval*ticks*1000);
                else
                    radian-=(Math.PI/2.0/roundInterval*ticks*1000);
            }
            //Radian restricts
            if(radian>=Math.PI/2.0)
            {
                radian=Math.PI/2.0;
                direction=false;
                isRotate=false;
            }
            if(radian<=0)
            {
                radian=0;
                direction=true;
                if(frozen) isRotate=false;
                else isRotate=true;
            }
        }
        if(isLeft) //Right direction rotate
        {
            //Rotate state
            if(isRotate)
            {
                if(direction)
                    radian+=(Math.PI/2.0/roundInterval*ticks*1000);
                else
                    radian-=(Math.PI/2.0/roundInterval*ticks*1000);
            }
            //Radian restricts
            if(radian<=Math.PI/2.0)
            {
                radian=Math.PI/2.0;
                direction=true;
                isRotate=false;
            }
            if(radian>=Math.PI)
            {
                radian=Math.PI;
                direction=false;
                if(frozen) isRotate=false;
                else isRotate=true;
            }
        }


    }

    @Override
    public void update(Vector2 vec) {
        endPoint.x+=vec.x;
        endPoint.y+=vec.y;
    }

    @Override
    public Vector2 getMin() {
        return new Vector2(endPoint);
    }

    @Override
    public Vector2 getMax() {
        return new Vector2(widtd,length);
    }

    @Override
    public Vector2 getExtra() {
        return new Vector2(radian,0);
    }

    @Override
    public Shape getShape() {
        return Shape.RotationRectangle;
    }

    @Override
    public Vector2[] getPolygonBuffer() {
        return null;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color=color;
    }

    public Vector2 getSpeed(double l)
    {


        if(isRotate)
        {
            double coe= Math.PI/2.0/roundInterval*750*l;
            double ncoe=-1.0*coe;
            if(direction==true)
                return new Vector2(Math.sin(radian)*ncoe,Math.cos(radian)*coe);
            else
               return  new Vector2(Math.sin(radian)*coe,Math.cos(radian)*ncoe);
        }
        return new Vector2(0,0);
    }


}
