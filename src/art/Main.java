package art;

import javax.swing.*;
import java.awt.*;

public class Main {
    static int width;
    static int height;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;

        JFrame frame = new JFrame();
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);

        JLabel labelscale = createLabel(width - 80, 28, 40, 20, "Scale (1 - no scaling, 2 - 2 times smaller, etc.):");
        JTextArea inputscale = createTextArea(40, 20, 40, 50);
        JLabel label1 = createLabel(width - 80, 28, 40, height / 2 - 20, "Enter path to image: ");
        JTextArea a = createTextArea(width - 80, 28, 40, height / 2 + 20);
        JButton b = createButton(width / 3, 35, width / 2 - (width / 3) / 2, height / 2 + 80, "Ascii art!");

        labelscale.setFont(new Font("Arial", Font.PLAIN, 15));
        inputscale.setFont(new Font("Arial", Font.PLAIN, 15));
        a.setFont(new Font("Arial", Font.PLAIN, 20));
        b.setFont(new Font("Arial", Font.PLAIN, 20));
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setVisible(true);

        inputscale.setText("1");

        frame.add(labelscale);
        frame.add(inputscale);
        frame.add(label1);
        frame.add(a);
        frame.add(b);
        frame.setVisible(true);

        ButtonListener listener = new ButtonListener(a);
        b.addActionListener(listener);

        String path = listener.path;

        while (path.equals("")) {
            path = listener.path;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        a.setVisible(false);
        b.setVisible(false);
        inputscale.setVisible(false);
        labelscale.setVisible(false);
        label1.setText("0%");

        new GrayScale(path, label1, inputscale.getText()).draw();

        System.exit(0);
    }

    public static JTextArea createTextArea(int width, int height, int xPos, int yPos) {
        JTextArea txt = new JTextArea();
        txt.setBackground(Color.WHITE);
        txt.setBounds(xPos, yPos, width, height);
        return txt;
    }

    public static JButton createButton(int width, int height, int xPos, int yPos, String text) {
        JButton button = new JButton(text);
        button.setBounds(xPos, yPos, width, height);
        return button;
    }

    public static JLabel createLabel(int width, int height, int xPos, int yPos, String text) {
        JLabel label = new JLabel(text);
        label.setBounds(xPos, yPos, width, height);
        return label;
    }
}
