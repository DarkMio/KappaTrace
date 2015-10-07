package Vorbereitung;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Mio on 07/10/2015.
 */
public class ImageMenuBar extends JMenuBar {

    public ImageMenuBar(ActionListener al) {
        add(factoryButton("save", "save", al));
    }

    protected static JMenuItem factoryButton(String text, String actionCommand, ActionListener al) {
        final JMenuItem jm = new JMenuItem(text);
        jm.setActionCommand(actionCommand);
        jm.addActionListener(al);
        return jm;
    }
}
