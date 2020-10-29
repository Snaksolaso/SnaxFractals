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
public class Mandelbrot implements FractalSet{


    int pixWidth;
    int pixHeight;
    DoubleDouble realStart;
    DoubleDouble realEnd;
    DoubleDouble iStart;
    DoubleDouble iEnd;
    int maxIterations;
    double toAdd;
    int zoomNum;

    public Mandelbrot(int ipixWidth, int ipixHeight, double irealStart, double irealEnd, double iiStart, double iiEnd, int maxIterations){
        this.pixWidth = ipixWidth;
        this.pixHeight = ipixHeight;
        this.realStart = new DoubleDouble(irealStart);
        this.realEnd = new DoubleDouble(irealEnd);
        this.iStart = new DoubleDouble(iiStart);
        this.iEnd = new DoubleDouble(iiEnd);
        this.maxIterations = maxIterations;
        zoomNum = 0;
    }

    public int getZoomNum() {
        return zoomNum;
    }

    @Override
    public int getMaxIterations() {
        return maxIterations;
    }


    public DoubleDouble scaleX(int x){
        //return this.realStart + ((double)x / (double)this.pixWidth) * (this.realEnd - this.realStart);
        return realStart.add((new DoubleDouble((double)x / pixWidth).multiply(realEnd.subtract(realStart))));
    }
    public DoubleDouble scaleY(int y){
        //return this.iStart + ((double)y / (double)this.pixHeight) * (this.iEnd - this.iStart);

        return iStart.add((new DoubleDouble((double)y / pixHeight).multiply(iEnd.subtract(iStart))));
    }

    public int calcPixel(int x,int y){
        DoubleDouble cI = scaleY(y);
        DoubleDouble cR = scaleX(x);
        DoubleDouble zI = new DoubleDouble(0);
        DoubleDouble zR = new DoubleDouble(0);
        DoubleDouble zrSqr = new DoubleDouble(0);
        DoubleDouble ziSqr = new DoubleDouble(0);
        int iteration = 0;
        while(zrSqr.add(ziSqr).compareTo(new DoubleDouble(4)) < 0 && iteration < maxIterations){

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

            /*
            zI = zR * zI;
            zI += zI;
            zI += cI;
            zR = zrSqr - ziSqr + cR;
            zrSqr = zR * zR;
            ziSqr = zI * zI;
             */

            zI = zR.multiply(zI);
            zI = zI.add(zI);
            zI = zI.add(cI);
            zR = zrSqr.subtract(ziSqr).add(cR);
            zrSqr = zR.multiply(zR);
            ziSqr = zI.multiply(zI);

            iteration++;
        }
        return iteration;
    }



    //zooms the set's boundaries based on the ratio and pixel given
    public void zoom(int x, int y,double ratio){
        DoubleDouble ratioDD = new DoubleDouble(ratio);

        DoubleDouble comRealWidth = this.realEnd.subtract(realStart);
        DoubleDouble comIHeight = this.iEnd.subtract(iStart);

        DoubleDouble two = new DoubleDouble(2);

        DoubleDouble comOldRealCenter = this.realStart.add(comRealWidth.divide(two));
        DoubleDouble comOldICenter = this.iStart.add(comIHeight.divide(two));
        DoubleDouble comRectRealWidth = comRealWidth.multiply(ratioDD);
        DoubleDouble comRectIHeight = comIHeight.multiply(ratioDD);

        DoubleDouble comRealCoord = comRealWidth.multiply(new DoubleDouble(((double)x/pixWidth))).subtract(comRealWidth.divide(two)).subtract(comRectRealWidth.divide(two)).add(comOldRealCenter);
        DoubleDouble comICoord = comIHeight.multiply(new DoubleDouble(((double)y/pixHeight))).subtract(comIHeight.divide(two)).subtract(comRectIHeight.divide(two)).add(comOldICenter);

        this.realStart = comRealCoord;
        this.realEnd = comRealCoord.add(comRectRealWidth);
        this.iStart = comICoord;
        this.iEnd = comICoord.add(comRectIHeight);
        this.zoomNum = zoomNum + 1;


        System.out.println(realStart.subtract(realEnd));

    }
}



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
