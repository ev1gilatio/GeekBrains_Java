import javax.swing.*;

public class Button extends JButton {
    public Button() {
        setSize(50,50);
    }

    public void changeText(String s) {
        this.setText(s);
    }

    public void changeLoc(int x, int y) {
        this.setLocation(x, y);
    }

    public void changeSize(int w, int h) {
        this.setSize(w, h);
    }

    public void changeAll(String s, int x, int y) {
        this.setText(s);
        this.setLocation(x, y);
    }

    public void changeAll(String s, int x, int y, int w, int h) {
        this.setText(s);
        this.setLocation(x, y);
        this.setSize(w, h);
    }
}