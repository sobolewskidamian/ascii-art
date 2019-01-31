package art;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GrayScale {
    private File input;
    private BufferedImage image;
    private JLabel label;
    private long percent;
    private boolean clean = false;
    private int width;
    private int height;
    private String scaleString;
    private int scale;

    GrayScale(String path, JLabel label, String scaleString) {
        input = new File(path);
        this.label = label;
        this.scaleString = scaleString;
        scale = 1;
        percent = 0;
    }

    public void draw() {
        try {
            scale = Integer.parseInt(scaleString);
            image = ImageIO.read(input);
            width = image.getWidth() / scale;
            height = image.getHeight() * 3 / 7 / scale;
            image = imageToBufferedImage(image.getScaledInstance(width, height, 0));
            AsciiChar asciiCh = new AsciiChar();

            for (int i = 0; i < height; i++) {
                String string = "";
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    double red = c.getRed() * 0.299;
                    double green = c.getGreen() * 0.587;
                    double blue = c.getBlue() * 0.114;
                    int greyScale = (int) (red + green + blue);
                    char ch = asciiCh.getCharacter(greyScale);
                    string += ch;
                    percent = 100 * (i * width + j + 1) / (width * height);
                    label.setText(percent + "%");
                }
                string += "\n";
                addToFile(string);
            }
        } catch (IOException e) {
            label.setText(e.getMessage());
        } finally {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage imageToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    private String parsePath() {
        String path = input.getPath();
        return path.substring(0, path.lastIndexOf('.')) + ".txt";
    }

    private void addToFile(String string) throws IOException {
        String txtPath = parsePath();
        File f = new File(txtPath);
        if (!clean && f.exists() && !f.isDirectory()) {
            f.delete();
            clean = true;
        }
        FileWriter file = new FileWriter(txtPath, true);
        BufferedWriter out = new BufferedWriter(file);
        String updatedText = string.replaceAll("\n", System.lineSeparator());
        out.write(updatedText);
        out.close();
    }
}
