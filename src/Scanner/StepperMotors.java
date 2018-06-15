package Scanner;

public final class StepperMotors {
    private Board board;

    public StepperMotors(Board board){
        this.board = board;
    }

    public final void makeStep(String dir){
        if (dir.equals("up") || dir.equals("down") || dir.equals("left") || dir.equals("right"))
            switch (dir){
                case "up": board.sendData("y"); break;
                case "down": board.sendData("Y"); break;
                case "left": board.sendData("x"); break;
                case "right": board.sendData("X");
            }
    }

    public final void makeSteps(String dir, int count) throws InterruptedException {
        if ((dir.equals("up") || dir.equals("down") || dir.equals("left") || dir.equals("right")) && count >= 0)
            for (int i = 0; i < count; i++) {
                makeStep(dir);
                Thread.sleep(10);
            }
    }

    public final void makeCircle(String dir, int micro) throws InterruptedException {
        if ((dir.equals("up") || dir.equals("down") || dir.equals("left") || dir.equals("right")) && micro >= 0)
            makeSteps(dir, micro * 200);
    }
}
