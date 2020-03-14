package main.models.supportModels;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ExtendedJSlider {

    private JPanel panel = new JPanel(new BorderLayout());
    private JButton smallerButton = new JButton("<");
    private JButton moreButton = new JButton(">");
    private JSlider slider = new JSlider();


    private ExtendedJSlider(Builder builder){
       slider.setMinimum(builder.minimumValue);
       slider.setMaximum(builder.maximumValue);
       slider.setValue(builder.currentValue);

       panel.add(smallerButton, BorderLayout.WEST);
       panel.add(moreButton, BorderLayout.EAST);
       panel.add(slider, BorderLayout.CENTER);

       smallerButton.addActionListener(actionEvent -> slider.setValue(slider.getValue() - 1));
       moreButton.addActionListener(actionEvent -> slider.setValue(slider.getValue() + 1));
    }

    public void setVisible(boolean status) {
        smallerButton.setVisible(status);
        slider.setVisible(status);
        moreButton.setVisible(status);
        panel.setVisible(status);
    }

    public int getValue() {
        return slider.getValue();
    }

    public JPanel toJPanel() {
        return this.panel;
    }

    public void addChangeListener(ChangeListener changeListener) {
        slider.addChangeListener(changeListener);
    }



    public static class Builder {

        private int minimumValue = 0;
        private int maximumValue = 100;
        private int currentValue = 50;


        public Builder setMinimum(int num) {
            minimumValue = num;
            return this;
        }

        public Builder setMaximum(int maximum) {
            maximumValue = maximum;
            return this;
        }

        public Builder setCurrentValue(int currentValue) {
            this.currentValue = currentValue;
            return this;
        }



        public ExtendedJSlider build() {
            if (minimumValue > maximumValue) {
                int tmp = minimumValue;
                minimumValue = maximumValue;
                maximumValue = tmp;
            }

            if (currentValue < minimumValue || currentValue > maximumValue)
                currentValue = (minimumValue + maximumValue) / 2;

            return new ExtendedJSlider(this);
        }
    }
}
