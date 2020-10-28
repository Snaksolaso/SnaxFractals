/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import javax.swing.*;

/**
 * Handles the interface between colors and the set.
 *
 *
 * @author Snaxolas
 */
public class FractalColoring {

    Mandelbrot set;

    int width;
    int height;
    int[] intRGB;
    double toAdd;


    public FractalColoring(int width, int height, Mandelbrot set, double iteration){
        this.width = width;
        this.height = height;
        this.set = set;
        this.toAdd = iteration;

        Gradients gradientMaker = new Gradients();
        this.intRGB = gradientMaker.rainbow(0);
    }


    //calculates the RGB value of the given pixel
    public int calcPixel(int i, int j){
        int rgb;
        int black = new Color(0, 0, 0).getRGB();
        int iter = set.calcPixel(i, j);


        double pix = iter * toAdd + toAdd;
        int pixint = (int)(pix % 510);

        if(pix == set.maxIterations * toAdd + toAdd){
            rgb = black;
        }else{
            rgb = intRGB[pixint];
        }

        return rgb;
    }

    public void clickedAt(int x, int y){

    }

    public void drawFrame(BufferedImage toSlap, JLabel picLabel){

        boolean[][] inSet = new boolean[width][height];





        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int color = calcPixel(i,j);
                toSlap.setRGB(i,j, color);
                picLabel.repaint();
            }
        }
    }






}
