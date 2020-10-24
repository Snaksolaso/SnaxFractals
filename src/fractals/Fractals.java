/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Snaxolas
 */
public class Fractals {

    public static boolean render(Mandelbrot set, int i, int j, double toAdd, BufferedImage BI, int[] intRGB, JLabel piclabel){
        
        boolean inSet = false;
        
        int black = new Color(0, 0, 0).getRGB();
        int iter = set.calcPixel(i, j);
        double pix = iter * toAdd + toAdd;
        int pixint = (int)((double)(pix)%510);
        if(pix == set.maxIterations*toAdd + toAdd){
            BI.setRGB(i,j,black);
            inSet = true;


        }else{
            BI.setRGB(i, j, intRGB[pixint]);
        }
        piclabel.repaint();
        return inSet;
    }
    
    public static boolean everyIsFalse(boolean[][] check){
        for(int i = 0; i< check.length; i++){
            for(int j = 0; j< check[i].length; j++){
                if(check[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    
    
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;

        //gets an image up in a new frame
        BufferedImage BI = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        JFrame frame = new JFrame("Test");
        JLabel piclabel = new JLabel(new ImageIcon(BI));
        JPanel panel = new JPanel();
        panel.add(piclabel);
        frame.getContentPane().add(panel);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Gradients gradientMaker = new Gradients();
        
        int[] intRGB = gradientMaker.rainbow(0);
        
        Mandelbrot set = new Mandelbrot(width, height, -2.6666666666, 2.6666666666, -1.5, 1.5,4000);
        
        
        int[][] fractal = new int[width][height];

        
        FracMouse mouse = new FracMouse(set);
        panel.addMouseListener(mouse);
        FracKey key = new FracKey(set);
        panel.addKeyListener(key);
        double toAdd = Math.pow(Math.E, 2);
        int zoom = 0;
        
        int black = new Color(0, 0, 0).getRGB();
        boolean[][] inSet = new boolean[width][height];
        boolean[][] lastInSet = new boolean[width][height];
        for(int i = 0; i < inSet.length; i++){
            for(int j = 0; j < inSet[i].length; j++){
                lastInSet[i][j] = true;
            }
        }
        
        int red = new Color(255, 0, 0).getRGB();
        int passes = 0;
        boolean running = true;
        while(running == true){
            while(passes <= 2){
                for(int i = 0; i < width; i++){
                    if (passes == 0){
                        for(int k = 0; k < height; k++){
                            
                        }
                    }
                    for(int j = 0; j < height; j++){
                        
                        switch(passes){
                            case(0):
                                if(i % 2 == j % 2){
                                   inSet[i][j] = render(set, i, j, toAdd,BI, intRGB, piclabel); 
                                }
                                break;
                            case(1):
                                if(i > 0 && j > 0 && i < width-1 && j < height-1 && inSet[i-1][j] && inSet[i][j-1] && inSet[i+1][j] && inSet[i][j+1]){
                                    inSet[i][j] = true;
                                    BI.setRGB(i,j,black);
                                    piclabel.repaint();
                                }
                                break;
                            case(2):
                                if (!inSet[i][j] && j%2 != i%2){
                                    render(set,i,j,toAdd,BI,intRGB,piclabel);
                                }
                                break;
                        }                        
                        
                    }
                }
                passes ++;
                //File output = new File("E://FractalImages/Mandelbrot1:" + zoom + ".png");
                //try{
                    //System.out.println(ImageIO.write(BI, "png", output));
                    
                //}catch(IOException ne){
                //    ne.printStackTrace();
                //}
                       
            }
            for(int i = 0; i < inSet.length; i++){
                for(int j = 0; j < inSet[i].length; j++){
                    inSet[i][j] = false;
                }
            }
            //I can't remove this print statement or the zoom wont work
            
            
            if (set.zoomNum != zoom){
                System.out.println("Huh?");
                passes = 0;
                zoom = set.zoomNum;
            }

        }
            

        
    }
    
}
