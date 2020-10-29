package fractals;

import java.awt.*;
import java.awt.image.BufferedImage;


/*
The thread object used in FractalColoring to improve performance via multithreading

 */
public class FractalThread extends Thread{

    FractalColoring parent;
    BufferedImage bi;
    int topBoundary;
    int bottomBoundary;
    boolean [][] doNotCalc;

    public FractalThread(FractalColoring parent, BufferedImage bi, int topBoundary, int bottomBoundary, boolean[][] doNotCalc){
        this.parent = parent;
        this.bi = bi;
        this.topBoundary = topBoundary;
        this.bottomBoundary = bottomBoundary;
        this.doNotCalc = doNotCalc;
    }

    public void run()
    {
        for(int i = 3; i < parent.width; i++){
            for(int j = bottomBoundary - 1; j >= topBoundary; j--){
                if(!doNotCalc[i][j]){
                    int color = parent.calcPixel(i,j);
                    bi.setRGB(i,j, color);
                    bi.setRGB(i-3,j, color);
                }else{
                    bi.setRGB(i-3,j,Color.BLACK.getRGB() );
                }
                bi.setRGB(i-1,j, Color.RED.getRGB());
            }
        }
    }
}
