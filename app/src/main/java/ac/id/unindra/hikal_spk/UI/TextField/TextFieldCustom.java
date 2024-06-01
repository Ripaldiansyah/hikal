package ac.id.unindra.hikal_spk.UI.TextField;

import javax.swing.Icon;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatTextField;

import java.awt.*;

public class TextFieldCustom extends FlatTextField {

    public TextFieldCustom(
            String placeholder,
            Icon icon,
            boolean isLeadingIcon)

    {
        setPlaceholderText(placeholder);
        if (isLeadingIcon) {
            setLeadingIcon(icon);
        } else {
            setTrailingIcon(icon);
        }
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setShowClearButton(true);
        setPreferredSize(new Dimension(getPreferredSize().width, 45));
        setSelectAllOnFocusPolicy(SelectAllOnFocusPolicy.always);
        putClientProperty(FlatClientProperties.STYLE, ""
                + "focusColor:#e7000a;"
                + "focusedBorderColor:#e7000a");

    }

    public FlatTextField getTextField() {
        return this;
    }

}
