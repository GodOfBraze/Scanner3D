package Scanner;

import java.io.IOException;

public final class Scanner3D {
    protected Board board;
    protected LaserDistanceSensor sensor;
    private Logger logger;
    protected StepperMotors motors;
    protected PointsArrays points;
    protected double height;
    private boolean isFirst = true;
    private double angleA = 0.0, angleB = 0.0;

    public Scanner3D(String port, int speed, int width, int height) throws IOException, InterruptedException {
        board = new Board(port, speed);
        sensor = new LaserDistanceSensor(board);
        points = new PointsArrays(width);
        logger = new Logger(points);
        motors = new StepperMotors(board);
        this.height = height;
        board.connect();
    }

    public final void start() throws IOException{
        points.start();
        logger.start();

        for (int i = 0; i < Math.round(height/2); i++) {
            for (int j = 0; j < points.line1.length; j++) {
                if (isFirst)
                    points.setLine1Coord(j, sensor.getCoords("F", angleA, angleB));
                else{
                    points.switchLine1toLine2(j);
                    points.setLine2Coord(j, sensor.getCoords("F", angleA, angleB));
                }
                motors.makeStep("right");
                angleB += 1.8;
            }

            if (!isFirst)
                logger.writeLog();

            motors.makeStep("up");
            angleA += 1.8;
            if (((i != Math.round(height/2) - 1) && (height%2 == 1)) || (height%2 == 0)) {
                for (int j = 0; j < points.line1.length; j++) {
                    if (isFirst)
                        points.setLine2Coord(j, sensor.getCoords("F", angleA, angleB));
                    else {
                        points.switchLine1toLine2(j);
                        points.setLine2Coord(j, sensor.getCoords("F", angleA, angleB));
                    }
                    motors.makeStep("left");
                    angleB -= 1.8;
                }

                logger.writeLog();
                motors.makeStep("up");
                angleA += 1.8;
                isFirst = false;
            }
        }

        logger.finish();
        board.disconnect();
    }
}
