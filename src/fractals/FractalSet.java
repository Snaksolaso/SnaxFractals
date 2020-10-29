package fractals;

public interface FractalSet {

    int calcPixel(int x,int y);
    void zoom(int x, int y,double ratio);
    public int getZoomNum();
    public int getMaxIterations();
    }
