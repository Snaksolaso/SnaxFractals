package fractals;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class FractalExplorer{

    public static void main(String[] args){
        int width = 1920;
        int height = 1080;
        int iterations = 4000;
        int funky = 0;

        if(args.length != 0){
            if(args.length >= 2) {
                try {
                    if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
                        width = Integer.parseInt(args[0]);
                        height = Integer.parseInt(args[1]);
                    } else {
                        System.out.println("Resolution width and height must both be above 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("First two arguments are resolution width and height, respectively! Integers only!");
                }
            }if(args.length >= 3) {
                try {
                    if(Integer.parseInt(args[2]) > 0){
                        iterations = Integer.parseInt(args[2]);
                    }else{
                        System.out.println("Iteration number must be above 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Third argument must be number of iterations! Integers only!");

                }
            }if(args.length >= 4) {
                try {
                    if(Integer.parseInt(args[3]) > 0){
                        funky = Integer.parseInt(args[3]);
                    }else{
                        System.out.println("Funky number must be above 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Fourth argument must be funky! Integers only!");

                }
            }

        }






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

        FractalSet set1 = new MandelbrotSimple(width, height, -2.66666666,2.6666666,-1.5, 1.5,iterations);
        FractalSet set2 = new Mandelbrot(width, height, -2.66666666,2.6666666,-1.5, 1.5,iterations);


        FracMouse mouse = new FracMouse(set1,set2);
        panel.addMouseListener(mouse);

        FractalColoring frac = new FractalColoring(width,height, set1, set2, 1, funky);

        System.out.println("Test");
        boolean toProcess = true;
        boolean running = true;

        double zoomNum = set1.getZoomNum();
        while(running == true){

            if(toProcess){
                frac.drawFrame(buf, picLabel, 8, 50, 50,20);
                picLabel.repaint();
                toProcess = false;
            }

            System.out.print("");
            if(zoomNum != set1.getZoomNum()){
                zoomNum = set1.getZoomNum();
                toProcess = true;
            }

            picLabel.repaint();

        }
    }
}
