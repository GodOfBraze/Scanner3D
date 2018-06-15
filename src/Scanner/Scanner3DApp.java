package Scanner;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Scanner3DApp extends JFrame {
    private JPanel mainWin;
    private JButton OKButton;
    private JButton upButton;
    private JButton rightButton;
    private JButton downButton;
    private JTextField xInput;
    private JTextField yInput;
    private JButton confirmButton;
    private JPanel options;
    private JButton left;
    private JPanel values;
    private JPanel children;
    private JLabel xVal;
    private JLabel yVal;
    private JPanel input;
    private static int x1 = 0, y1 = 0, count = 0;
    private static Scanner3D scanner;
    private boolean isConfirm = false;

    Scanner3DApp() throws IOException, InterruptedException {
        super("3DScanner");
        scanner = new Scanner3D("COM4", 19200, 0, 0);
        setContentPane(mainWin);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        scanner.sensor.open();

        ActionListener listener2 = e -> {
            x1 = Integer.parseInt(xInput.getText());
            y1 = Integer.parseInt(yInput.getText());

            xVal.setText("x: " + String.valueOf(x1));
            yVal.setText("y: " + String.valueOf(y1));

            isConfirm = true;
        };

        ActionListener listener = e -> {
            JButton but = (JButton)e.getSource();
            String label = but.getText();

            if (count == 0)
                switch (label){
                    case "Up": scanner.motors.makeStep("up"); break;
                    case "Down": scanner.motors.makeStep("down"); break;
                    case "Right": scanner.motors.makeStep("right"); break;
                    case "Left": scanner.motors.makeStep("left");
                }

            if (label.equals("OK") && count == 1){
                if (!isConfirm) {
                    try {
                        scanner.motors.makeSteps("down", y1);
                        scanner.motors.makeSteps("left", x1);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                scanner.sensor.close();
                OKButton.setEnabled(false);
                upButton.setEnabled(false);
                downButton.setEnabled(false);
                left.setEnabled(false);
                rightButton.setEnabled(false);

                scanner.height = y1;
                scanner.points.setWidth(x1);
                try {
                    scanner.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else if (label.equals("OK"))
                count++;

            if (count == 1) {
                switch (label) {
                    case "Up":
                        scanner.motors.makeStep("up");
                        y1++;
                        break;
                    case "Down":
                        scanner.motors.makeStep("down");
                        y1--;
                        break;
                    case "Right":
                        scanner.motors.makeStep("right");
                        x1++;
                        break;
                    case "Left":
                        scanner.motors.makeStep("left");
                        x1--;
                }

                xVal.setText("x: " + String.valueOf(x1));
                yVal.setText("y: " + String.valueOf(y1));
            }
        };

        OKButton.addActionListener(listener);
        upButton.addActionListener(listener);
        downButton.addActionListener(listener);
        left.addActionListener(listener);
        rightButton.addActionListener(listener);
        confirmButton.addActionListener(listener2);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Scanner3DApp();
    }
}
