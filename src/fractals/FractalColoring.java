/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Handles the interface between colors and the set.
 *
 *
 * @author Snaxolas
 */
public class FractalColoring {

    FractalSet set1;
    FractalSet set2;

    int width;
    int height;
    int[] intRGB;
    double toAdd;
    int funky;


    public FractalColoring(int width, int height, FractalSet set1, FractalSet set2, double iteration, int funky){
        this.width = width;
        this.height = height;
        this.set1 = set1;
        this.set2 = set2;
        this.toAdd = iteration;
        this.funky = funky;

        Gradients gradientMaker = new Gradients();
        this.intRGB = gradientMaker.rainbow(0);
    }


    //calculates the RGB value of the given pixel
    public int calcPixel(int i, int j){
        int rgb;
        int black = new Color(0, 0, 0).getRGB();
        int iter;
        if(set1.getZoomNum() > 14){
            iter = set2.calcPixel(i, j);
        }else{
            iter = set1.calcPixel(i, j);
        }


        double pix = iter * toAdd + toAdd;
        int pixint = (int)(pix % 510);

        if(pix == set1.getMaxIterations() * toAdd + toAdd){
            rgb = black;
        }else{
            rgb = intRGB[pixint];
        }

        return rgb + funky;
    }

    public void clickedAt(int x, int y){

    }

    public void fillCell(int i, int j, int lineDistanceX, int lineDistanceY, boolean[][] inSet, BufferedImage bi){
        boolean toFill = true;

        //upper
        int y = j * lineDistanceY;
        for(int x = (i * lineDistanceX + 1); x <((i+1) * lineDistanceX); x++){
            if(!inSet[x][y]) {
                toFill = false;
                break;
            }
        }

        //right
        int x = (i + 1) * lineDistanceX;
        for(y = (j * lineDistanceY + 1); y <((j+1) * lineDistanceY); y++){
            if(!inSet[x][y]) {
                toFill = false;
                break;
            }
        }

        //down
        y = (j + 1) * lineDistanceY;
        for(x = (i * lineDistanceX + 1); x <((i+1) * lineDistanceX); x++){
            if(!inSet[x][y]) {
                toFill = false;
                break;
            }
        }
        x = i * lineDistanceX;
        //left
        for(y = (j * lineDistanceY + 1); y <((j+1) * lineDistanceY); y++){
            if(!inSet[x][y]) {
                toFill = false;
                break;
            }
        }


        if(toFill){
            for(x = (i * lineDistanceX + 1); x <((i+1) * lineDistanceX); x++){
                for(y = (j * lineDistanceY + 1); y <((j+1) * lineDistanceY); y++){
                    inSet[x][y] = true;
                    bi.setRGB(x,y,Color.black.getRGB());

                }
            }
        }


    }

    //draws the set
    public void drawFrame(BufferedImage toSlap, JLabel picLabel,int numThreads, int numCellsX, int numCellsY, int wiggle){


        /*
        int iter = (int)Math.floor((double)width / numThreads);
        int currval = 0;

        for(int i = 0; i < numThreads; i++){
            FractalThread object = new FractalThread(this, toSlap, currval, currval + iter);
            object.start();
            currval += iter;
        }
         */



        //Calculate "strips" of pixels, both vertically and horizontally, spaced according to the width and heights of the cells they form.
        //The cells formed by these cells will be individually tested. If all pixels of the surrounding walls are black, they are filled in.


        boolean[][] inSet = new boolean[width][height];

        //Calculate maximizing values for the widths and heights of cells

        int maxCellSizeX = (int)Math.floor((double) width / numCellsX);
        int maxCellSizeY = (int)Math.floor((double) height / numCellsY);

        int maxiWidth = maxCellSizeX - wiggle;
        double maxRatioWidth = 0.0;

        int maxiHeight = maxCellSizeY - wiggle;
        double maxRatioHeight = 0.0;


        for(int i = maxCellSizeX - wiggle; i <= maxCellSizeX; i++){
            double ratio = ((double)(i + 1) * numCellsX + 1) / width;
            if(ratio > maxRatioWidth && ratio <= 1){
                maxiWidth = i;
                maxRatioWidth = ratio;
            }
        }

        for(int i = maxCellSizeY - wiggle; i <= maxCellSizeY; i++){
            double ratio = ((double)(i + 1) * numCellsY + 1) / height;
            if(ratio > maxRatioHeight && ratio <= 1){
                maxiHeight = i;
                maxRatioHeight = ratio;
            }
        }


        int lineDistanceX = maxiWidth + 1;
        int lineDistanceY = maxiHeight + 1;

        int black = Color.black.getRGB();

        //calculates the "Strips," draws them, and stores the positions "in the set" in inSet
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(i % lineDistanceX == 0){
                    int calc = calcPixel(i, j);
                    toSlap.setRGB(i, j, calc);
                    for(int x = i + 1; x < width; x++){
                            toSlap.setRGB(x, j, calc);

                    }

                    if(calc == black){
                        inSet[i][j] = true;
                    }
                }else if(j % lineDistanceY == 0){
                    int calc = calcPixel(i, j);
                    toSlap.setRGB(i, j, calc);
                    if(calc == black) {
                        inSet[i][j] = true;
                    }
                }
            }
            picLabel.repaint();
        }

        //fills the cells made by the strips
        for(int i = 0; i < numCellsX; i++){
            for(int j = 0; j < numCellsY; j++){
                fillCell(i,j,lineDistanceX,lineDistanceY,inSet, toSlap);
            }
        }
        picLabel.repaint();


        int iter = (int)Math.floor((double)height / numThreads);
        int currval = 0;

        for(int i = 0; i < numThreads; i++){
            FractalThread object = new FractalThread(this, toSlap, currval, currval + iter, inSet);
            object.start();
            currval += iter;
        }

        System.out.println(set1.getZoomNum());
    }






}
