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
    
    private ComplexNumber scaleCoords(int x,int y){
        double real = this.realStart + ((double)x / (double)this.pixWidth) * (this.realEnd - this.realStart);
        double imag = this.iStart + ((double)y / (double)this.pixHeight) * (this.iEnd - this.iStart);
        return(new ComplexNumber(real,imag));
    }
    
    public int calcPixel(int x,int y){
        ComplexNumber z = this.scaleCoords(x, y);
        ComplexNumber z1 = z;
        z = new ComplexNumber(0,0);
        int iteration = 0;
        while(z.real()*z.real() + z.imaginary()*z.imaginary() <= 4 && iteration < maxIterations){
            z = z.multiply(z).add(z1);
            iteration++;
        }
        return iteration;
    }

    public void zoom(int x, int y,double ratio){
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
