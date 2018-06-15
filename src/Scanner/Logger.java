package Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Logger {
    private FileWriter writer;
    private PointsArrays coords;

    public Logger(PointsArrays coords) throws IOException {
        this.coords = coords;

        File file = new File("logs.stl");
        writer = new FileWriter(file);
        file.createNewFile();
    }

    public final void start() throws IOException{
        writer.write("solid\n");
    }

    public final void finish() throws IOException{
        writer.write("endsolid");
        writer.flush();
        writer.close();
    }

    public final void writeLog() throws IOException{
        for (int i = 0; i < coords.line1.length - 1; i++) {
            writer.write("\tfacet normal 0.000000e+000 0.000000e+000 0.000000e+000\n");
            writer.write("\t\touter loop\n");
            writer.write("\t\t\tvertex " + coords.line1[i][0] + "e+003 " + coords.line1[i][1] + "e+003 " + coords.line1[i][2] + "e+003\n");
            writer.write("\t\t\tvertex " + coords.line2[(coords.line1.length-1)-i][0] + "e+003 " + coords.line2[(coords.line1.length-1)-i][1] + "e+003 " + coords.line2[(coords.line1.length-1)-i][2] + "e+003\n");
            writer.write("\t\t\tvertex " + coords.line1[i+1][0] + "e+003 " + coords.line1[i+1][1] + "e+003 " + coords.line1[i+1][2] + "e+003\n");
            writer.write("\t\tendloop\n");
            writer.write("\tendfacet\n");
        }

        for (int i = 0; i < coords.line1.length - 1; i++) {
            writer.write("\tfacet normal 0.000000e+000 0.000000e+000 0.000000e+000\n");
            writer.write("\t\touter loop\n");
            writer.write("\t\t\tvertex " + coords.line2[(coords.line1.length-1)-i][0] + "e+003 " + coords.line2[(coords.line1.length-1)-i][1] + "e+003 " + coords.line2[(coords.line1.length-1)-i][2] + "e+003\n");
            writer.write("\t\t\tvertex " + coords.line2[(coords.line1.length-1)-(i+1)][0] + "e+003 " + coords.line2[(coords.line1.length-1)-(i+1)][1] + "e+003 " + coords.line2[(coords.line1.length-1)-(i+1)][2] + "e+003\n");
            writer.write("\t\t\tvertex " + coords.line1[i+1][0] + "e+003 " + coords.line1[i+1][1] + "e+003 " + coords.line1[i+1][2] + "e+003\n");
            writer.write("\t\tendloop\n");
            writer.write("\tendfacet\n");
        }
    }
}
