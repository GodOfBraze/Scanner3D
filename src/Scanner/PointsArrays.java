package Scanner;

public final class PointsArrays {
    protected double[][] line1, line2;
    private int width;

    public PointsArrays(int size) {
        this.width = size;
    }

    public final void start(){
        line1 = new double[width][3];
        line2 = new double[width][3];
    }

    public final void setWidth(int n){
        this.width = n;
    }

    public final void setLine1Coord(int pos, double[] point3D){
        line1[pos][0] = point3D[0];
        line1[pos][1] = point3D[1];
        line1[pos][2] = point3D[2];
    }

    public final void setLine2Coord(int pos, double[] point3D){
        line2[pos][0] = point3D[0];
        line2[pos][1] = point3D[1];
        line2[pos][2] = point3D[2];
    }

    public final void switchLine1toLine2(int i){
        setLine1Coord(i, line2[i]);
    }

    public final void switchLine2toLine1(int i){
        setLine2Coord(i, line1[i]);
    }
}
