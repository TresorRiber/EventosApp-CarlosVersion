package Windows.User.Refactored;

import javax.swing.*;
import java.awt.*;

public class PanelUtils {
    public static JPanel createPanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    public static JPanel createCenteredPanel(JComponent component, Dimension preferredSize) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        component.setPreferredSize(preferredSize);
        panel.add(component);
        return panel;
    }
}
