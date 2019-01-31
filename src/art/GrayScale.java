package art;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class GrayScale {
    private BufferedImage image;
    private boolean clean = false;
    private int width;
    private int height;

    private int scale = 3;

    public GrayScale() {
        try {
            File input = new File("a.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            image = imageToBufferedImage(image.getScaledInstance(width / scale, height * 3 / 7 / scale, 0));
            AsciiChar asciiCh = new AsciiChar();

            for (int i = 0; i < image.getHeight(); i++) {
                String string = "";
                for (int j = 0; j < image.getWidth(); j++) {
                    Color c = new Color(image.getRGB(j, i));
                    double red = c.getRed() * 0.299;
                    double green = c.getGreen() * 0.587;
                    double blue = c.getBlue() * 0.114;
                    int greyScale = (int) (red + green + blue);
                    char ch = asciiCh.getCharacter(greyScale);
                    string += ch;
                }
                string += "\n";
                addToFile(string);
            }
        } catch (Exception e) {
            System.out.println("Blad");
        }
    }

    public BufferedImage imageToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public void addToFile(String string) throws IOException {
        File f = new File("ascii.txt");
        if (!clean && f.exists() && !f.isDirectory()) {
            f.delete();
            clean = true;
        }
        FileWriter file = new FileWriter("ascii.txt", true);
        BufferedWriter out = new BufferedWriter(file);
        String updatedText = string.replaceAll("\n", System.lineSeparator());
        out.write(updatedText);
        out.close();
    }
}
