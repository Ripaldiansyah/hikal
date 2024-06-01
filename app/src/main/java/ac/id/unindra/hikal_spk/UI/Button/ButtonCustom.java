package ac.id.unindra.hikal_spk.UI.Button;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.Icon;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatButton;

public class ButtonCustom extends FlatButton {

    public ButtonCustom(String label, Icon icon, String color, ActionListener actionListener) {
        setText(label);
        setSquareSize(true);
        setIcon(icon);
        setForeground(Color.white);
        putClientProperty(FlatClientProperties.STYLE, ""

                + "margin:1,2,1,2;"
                + "arc:20;"
                + "borderWidth:0;"
                + "background:" + color + ";"
                + "focusWidth:0;"
                + "innerFocusWidth:0");

        addActionListener(actionListener);
    }

    public FlatButton getButton() {
        return this;
    }
}
