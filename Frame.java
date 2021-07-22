import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private JTextArea area;
    private JTextField field;
    private final Font textFont = new Font("arial", Font.PLAIN, 20);
    private final Font btnFont = new Font("arial", Font.PLAIN, 20);

    public Frame() {
        frameSettings();
        createPanels();

        setVisible(true);
    }

    private void frameSettings() {
        setTitle("Evigram");
        setSize(516, 739);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
    }

    private void createPanels() {
        createPanel1();
        createPanel2();
    }

    private void createPanel1() {
        BorderLayout borderLayout = new BorderLayout();

        JPanel panel1 = new JPanel();
        panel1.setBounds(10,10,490,630);
        panel1.setLayout(borderLayout);
        add(panel1);

        area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(textFont);
        area.setMargin(new Insets(0,2,0,2));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(scroll, BorderLayout.CENTER);
    }

    private void createPanel2() {
        JPanel panel2 = new JPanel();
        panel2.setBounds(0,640,500,60);
        panel2.setLayout(null);
        add(panel2);

        field = new JTextField();
        field.setBounds(10,10,330,40);
        field.setFont(textFont);
        field.setMargin(new Insets(0,2,0,2));

        field.addActionListener(e -> printText());


        panel2.add(field);

        JButton btn = new JButton();
        btn.setBounds(350,10,140,40);
        btn.setText("Send");
        btn.setFont(btnFont);

        btn.addActionListener(e -> printText());

        panel2.add(btn);
    }

    private void printText() {
        String message = field.getText();
        area.setText(area.getText() + "- " + message + "\n");
        field.setText("");
    }
}