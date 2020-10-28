package fractals;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class FractalExplorer{

    public static void main(String[] args){

        int height = 1080;
        int width = 1920;

        //sets up a new BufferedImage to be edited
        BufferedImage buf = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        JFrame frame = new JFrame("Test");
        JLabel picLabel = new JLabel(new ImageIcon(buf));
        JPanel panel = new JPanel();
        panel.add(picLabel);
        frame.getContentPane().add(panel);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //FracMouse mouse = new FracMouse(set);
        //panel.addMouseListener(mouse);
        //FracKey key = new FracKey(set);
        //panel.addKeyListener(key);

        Mandelbrot set = new Mandelbrot(width, height, new BigDecimal( -2.6666666666), new BigDecimal(2.6666666666), new BigDecimal(-1.5), new BigDecimal(1.5),4000, 10);
        FractalColoring frac = new FractalColoring(1920,1080, set, Math.pow(Math.E, 2));

        System.out.println("Test");
        boolean toProcess = true;
        boolean running = true;
        while(running == true){

            if(toProcess){
                frac.drawFrame(buf);
                picLabel.repaint();
                toProcess = false;
            }
            picLabel.repaint();

        }
    }
}
