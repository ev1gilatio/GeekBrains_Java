import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setTitle("Calculator");
        setSize(215+16, 311+39); //x+17 y+40
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }
}