package fractals;


public class MandelbrotSimple implements FractalSet{

    int pixWidth;
    int pixHeight;
    double realStart;
    double realEnd;
    double iStart;
    double iEnd;
    int maxIterations;
    double toAdd;
    int zoomNum;

    public MandelbrotSimple(int ipixWidth, int ipixHeight, double irealStart, double irealEnd, double iiStart, double iiEnd, int maxIterations){
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

    public int getZoomNum() {
        return zoomNum;
    }

    @Override
    public int getMaxIterations() {
        return maxIterations;
    }

    public int calcPixel(int x, int y){
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