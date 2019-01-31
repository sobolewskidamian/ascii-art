package art;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private JTextArea a;
    String path;

    public ButtonListener(JTextArea a) {
        this.a = a;
        path = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!a.getText().equals(""))
            path = a.getText();
    }
}
