package Scanner;

import arduino.Arduino;

public final class Board extends Arduino {
    private boolean isConnected = false;

    public Board(){
        super("COM3", 9200);
    }

    public Board(String port, int speed){
        super(port, speed);
    }

    public Board(String port){
        super(port, 9200);
    }

    public Board(int speed){
        super("COM3", speed);
    }

    public final void connect() throws InterruptedException {
        while (!Board.this.openConnection()){}
        isConnected = true;
        System.out.println("Connected!");
        Thread.sleep(2000);
    }

    public final void disconnect(){
        Board.this.closeConnection();
        isConnected = false;
    }

    public final void sendData(String data){
        if (isConnected)
            Board.this.serialWrite(data);
    }

    public final String getData(){
        if (isConnected){
            String data;

            do {
                data = Board.this.serialRead();
            }while (data.equals(""));

            return data;
        }

        return "";
    }
}
