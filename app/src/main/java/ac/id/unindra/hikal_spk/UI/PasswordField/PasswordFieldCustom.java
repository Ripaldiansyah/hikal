package ac.id.unindra.hikal_spk.UI.PasswordField;

import javax.swing.Icon;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatPasswordField;
import com.formdev.flatlaf.extras.components.FlatTextField.SelectAllOnFocusPolicy;

import java.awt.*;

public class PasswordFieldCustom extends FlatPasswordField {

    public PasswordFieldCustom(
            String placeholder,
            Icon icon,
            boolean isLeadingIcon)

    {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "focusColor:#e7000a;"
                + "focusedBorderColor:#e7000a");
        setPlaceholderText(placeholder);
        if (isLeadingIcon) {
            setLeadingIcon(icon);
        } else {
            setTrailingIcon(icon);
        }
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(getPreferredSize().width, 45));
        setForeground(Color.BLACK);
        setShowClearButton(true);
        setSelectAllOnFocusPolicy(SelectAllOnFocusPolicy.always);

    }

    public FlatPasswordField getPasswordField() {
        return this;
    }

}
