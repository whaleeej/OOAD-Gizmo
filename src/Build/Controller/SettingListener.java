package Build.Controller;

import Build.Model.Setting;
import Build.View.BuildToolbar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingListener implements ChangeListener,ActionListener
{
    private BuildController buildController;
    private BuildToolbar buildToolbar;
    private Setting setting;

    public SettingListener(BuildController buildController)
    {
        this.buildController = buildController;
        buildToolbar = buildController.getBuildToolbar();
        setting = buildController.getSetting();
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        JSlider slider = (JSlider)e.getSource();
        int value = slider.getValue();
        switch (slider.getName())
        {
            case "g":
                buildToolbar.getGravityLabel().setText("g: "+value);
                setting.setGravity(value);
                break;
            case "u":
                buildToolbar.getFrictionLabel().setText("u: "+value);
                setting.setU(value);
                break;
            case "c":
                buildToolbar.getAirFrictionLabel().setText("c: "+ value);
                setting.setC(value);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JSlider slider = null;
        switch (e.getActionCommand())
        {
            case "g":
                slider = buildToolbar.getGravitySlider();
                slider.setValue(10);
                //buildToolbar.getGravityLabel().setText("g: "+slider.getValue());
                //setting.setGravity(10);
                break;
            case "u":
                slider = buildToolbar.getFrictionSlider();
                slider.setValue(1);
                //buildToolbar.getFrictionLabel().setText("u: "+slider.getValue());
                //setting.setU(1);
                break;
            case "c":
                slider =buildToolbar.getAirFrictionSlider();
                slider.setValue(1);
                //buildToolbar.getAirFrictionLabel().setText("c: "+slider.getValue());
                //setting.setC(1);
                break;
        }
    }
}
