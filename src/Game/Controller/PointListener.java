package Game.Controller;

import Game.Model.Absorber;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PointListener {
    //Subscriber-Listener Mode
    private GameController gameController;
    JTextField pointText;
    public PointListener(JTextField jTextField)
    {
        this.pointText=jTextField;
    }

    public void onUpdateEvent(int score)
    {
        pointText.setText(score+"");
    }

}