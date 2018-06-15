package Scanner;

public final class LaserDistanceSensor {
    private Board board;

    public LaserDistanceSensor(Board board){
        this.board = board;
    }

    public final double getDistance(String mode){
        if (mode.equals("F") || mode.equals("D") || mode.equals("M")) {
            board.sendData(mode);
            return Double.parseDouble(board.getData());
        }

        return 0;
    }

    public final void open(){
        board.sendData("O");
    }

    public final void close(){
        board.sendData("C");
    }

    public final double[] getCoords(String mode, double angleA, double angleB){
        if(mode.equals("F") || mode.equals("D") || mode.equals("M")){
            double[] coords = new double[3];
            double x, y, z;
            double distance = getDistance(mode);

            x = distance * Math.cos(Math.toRadians(angleA)) * Math.sin(Math.toRadians(angleB));
            y = distance * Math.cos(Math.toRadians(angleA)) * Math.cos(Math.toRadians(angleB));
            z = distance * Math.sin(Math.toRadians(angleA));

            coords[0] = Double.parseDouble(String.valueOf(Math.round(x * 1000000.0))) / 1000000.0;
            coords[1] = Double.parseDouble(String.valueOf(Math.round(y * 1000000.0))) / 1000000.0;
            coords[2] = Double.parseDouble(String.valueOf(Math.round(z * 1000000.0))) / 1000000.0;

            return coords;
        }

        return null;
    }
}
