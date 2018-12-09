package Game.Controller;

import Game.Model.Absorber;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PointListener {
    //Subscriber-Listener Mode
    private GameController gameController;
    JLabel pointText;
    public PointListener(JLabel pointText)
    {
        this.pointText = pointText;
    }

    public void onUpdateEvent(int score)
    {
        pointText.setText("Score: "+score);
    }

}