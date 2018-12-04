package Game.Model;

import Physics.Controller.PhysicsEngineController;
import Physics.Model.Computation.Vector2;
import Physics.Model.Elements.Rectangle;

public class Pipe {
    //If circle collide the start of this pipe
    //pipe will trigger the onTriggerEnter function
    public PipePort A;     //left port
    public PipePort B;     //right port
    public Rectangle C;         //the longer one
    public Rectangle D;         //the other one
    public PhysicsEngineController pc=PhysicsEngineController.getPhysicsEngineController();//Edited by ceej

    public Pipe(double x, double y,double width, double len1,double len2,int type) {


        double thickness=1;
        switch (type) {
            case 1:
                //draw the pipe
                A = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y+len2-thickness, width, thickness);
                B = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x +len1- thickness, y, thickness, width);
                C=  new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y, len1-thickness, width);
                D =  new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y+ width, width, len2 - width-thickness);
                //bind two ports
                A.bindPort(B);
                B.bindPort(A);
                //set the initial direction of balls getting out of the port
                A.setDirection(0,1);
                B.setDirection(1,0);
                pc.initialRigid(A);
                pc.initialRigid(B);
                pc.initialRigid(C);
                pc.initialRigid(D);
                break;
            case 2:
                A = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y,thickness,width);
                B = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x +len1- width, y+len2-thickness, width,thickness);
                C = new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x+thickness, y, len1-thickness, width);
                D = new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x+len1-width, y+width, width, len2 - width-thickness);
                A.bindPort(B);
                B.bindPort(A);
                A.setDirection(-1,0);
                B.setDirection(0,1);
                pc.initialRigid(A);
                pc.initialRigid(B);
                pc.initialRigid(C);
                pc.initialRigid(D);
                break;
            case 3:
                A = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y+len2-width, thickness, width);
                B = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x+len1-width, y, width, thickness);
                C = new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x+thickness, y+len2-width, len1-thickness, width);
                D = new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x+len1-width, y+thickness, width, len2 - width-thickness);
                A.bindPort(B);
                B.bindPort(A);
                A.setDirection(-1,0);
                B.setDirection(0,-1);
                pc.initialRigid(A);
                pc.initialRigid(B);
                pc.initialRigid(C);
                pc.initialRigid(D);
                break;
            case 4:
                A = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y, width, thickness);
                B = new PipePort(0, new Vector2(0, 0), new Vector2(0, 0), 1, x+len1-thickness, y+len2-width, thickness, width);
                C = new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y+thickness, width,len2-width-thickness);
                D = new Rectangle(0, new Vector2(0, 0), new Vector2(0, 0), 1, x, y+len2-width, len2-thickness, width);
                A.bindPort(B);
                B.bindPort(A);
                A.setDirection(0,-1);
                B.setDirection(1,0);
                pc.initialRigid(A);
                pc.initialRigid(B);
                pc.initialRigid(C);
                pc.initialRigid(D);
                break;
        }




    }



}
