/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals;


/**
 *
 * @author Nikolas
 */
public class Mandelbrot {


    int pixWidth;
    int pixHeight;
    double realStart;
    double realEnd;
    double iStart;
    double iEnd;
    int maxIterations;
    double toAdd;
    int zoomNum;

    public Mandelbrot(int ipixWidth, int ipixHeight, double irealStart, double irealEnd, double iiStart, double iiEnd, int maxIterations){
        this.pixWidth = ipixWidth;
        this.pixHeight = ipixHeight;
        this.realStart = irealStart;
        this.realEnd = irealEnd;
        this.iStart = iiStart;
        this.iEnd = iiEnd;
        this.maxIterations = maxIterations;
        zoomNum = 0;
    }



    public double scaleX(int x){
        return this.realStart + ((double)x / (double)this.pixWidth) * (this.realEnd - this.realStart);
    }
    public double scaleY(int y){
        return this.iStart + ((double)y / (double)this.pixHeight) * (this.iEnd - this.iStart);
    }

    public int calcPixel(int x,int y){
        double cI = scaleY(y);
        double cR = scaleX(x);
        double zI = 0;
        double zR = 0;
        double zrSqr = 0;
        double ziSqr = 0;
        int iteration = 0;
        while(zrSqr + ziSqr <= 4 && iteration < maxIterations){

            /*

            z.r = 0;
            z.i = 0;
            zrsqr = z.r * z.r;
            zisqr = z.i * z.i;
            while (zrsqr + zisqr <= 4.0)
            {
                z.i = z.r * z.i;
                z.i += z.i; // Multiply by two
                z.i += c.i;
                z.r = zrsqr – zisqr + c.r;
                zrsqr = square(z.r);
                zisqr = square(z.i);
             }
             */
            zI = zR * zI;
            zI += zI;
            zI += cI;
            zR = zrSqr - ziSqr + cR;
            zrSqr = zR * zR;
            ziSqr = zI * zI;

            iteration++;
        }
        return iteration;
    }

    public void zoom(int x, int y,double ratio){
        double comRealWidth = this.realEnd - this.realStart;
        double comIHeight = this.iEnd - this.iStart;

        double comOldRealCenter = this.realStart + comRealWidth/2;
        double comOldICenter = this.iStart + comIHeight/2;
        double comRectRealWidth = comRealWidth*ratio;
        double comRectIHeight = comIHeight*ratio;
        double comRealCoord = (comRealWidth * ((double)x/pixWidth)) - comRealWidth/2 - comRectRealWidth/2 + comOldRealCenter;
        double comICoord = (comIHeight * ((double)y/pixHeight)) - comIHeight/2 - comRectIHeight/2 + comOldICenter;

        this.realStart = comRealCoord;
        this.realEnd = comRealCoord + comRectRealWidth;
        this.iStart = comICoord;
        this.iEnd = comICoord + comRectIHeight;
        this.zoomNum = zoomNum + 1;


        System.out.println(realStart-realEnd);

    }
}


    //zooms the set's bounds towards the screen pixels x and y, by the ratio specified NEEDS TO BE UPDATED TO BIGDECIMAL
    /*public void zoom(int x, int y,double ratio){
        double comRealWidth = this.realEnd - this.realStart;
        double comIHeight = this.iEnd - this.iStart;
        System.out.println((double)x/pixWidth);
        
        double comOldRealCenter = this.realStart + comRealWidth/2;
        double comOldICenter = this.iStart + comIHeight/2;
        double comRectRealWidth = comRealWidth*ratio;
        double comRectIHeight = comIHeight*ratio;
        double comRealCoord = (comRealWidth * ((double)x/pixWidth)) - comRealWidth/2 - comRectRealWidth/2 + comOldRealCenter;
        double comICoord = (comIHeight * ((double)y/pixHeight)) - comIHeight/2 - comRectIHeight/2 + comOldICenter;
        
        this.realStart = comRealCoord;
        this.realEnd = comRealCoord + comRectRealWidth;
        this.iStart = comICoord;
        this.iEnd = comICoord + comRectIHeight;
        this.zoomNum = zoomNum + 1;
        System.out.println(zoomNum);
        
    }*/


/*class juliaSet:

    def __init__(self, xwidth, xheight, xrealStart, xrealEnd, xiStart, xiEnd, xc):
        self.width = xwidth
        self.height = xheight
        self.realStart = xrealStart
        self.realEnd = xrealEnd
        self.iStart = xiStart
        self.iEnd = xiEnd
        self.c = xc

    def scaleCoords(self, x, y):
        return(complex(self.realStart + (x / self.width) * (self.realEnd - self.realStart), self.iStart + (y / self.height) * (self.iEnd - self.iStart)))

    def calcPixel(self, x, y, maxiterations):
        z = self.scaleCoords(x, y)
        iteration = 0
        while(abs(z) < 3 and iteration < maxiterations):
            z = z**2 + self.c
            iteration += 1
        if(iteration == maxiterations):
            return 0
        else:
            return 255 - (float(255) / (iteration + 1))
    
    def zoom(self, mousePos, width, height):
        comRealWidth = self.realEnd - self.realStart
        comIheight = self.iEnd - self.iStart

        comOldRealCenter = self.realStart + comRealWidth/2 
        comOldICenter = self.iStart + comIheight/2
        comRectRealWidth = comRealWidth/8
        comRectIheight = comIheight/8
        comRealCoord = (comRealWidth * (mousePos[0]/width)) - comRealWidth/2 - comRectRealWidth/2 + comOldRealCenter

        comICoord = (comIheight * (mousePos[1]/height)) - comIheight/2 - comRectIheight/2 + comOldICenter
        self.realStart = comRealCoord
        self.realEnd = comRealCoord + comRectRealWidth
        self.iStart = comICoord
        self.iEnd = comICoord + comRectIheight
*/
