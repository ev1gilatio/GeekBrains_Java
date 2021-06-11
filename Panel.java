import javax.swing.*;
import java.awt.*;

/**
 * В этой версии я создал одну панель и расположил все элементы вручную
 * Надеюсь такой вариант засчитается...
 */

public class Panel extends JPanel {
    private JTextField input;
    private int operation;
    float num;
    float res;

    public Panel() {
        setLayout(null);

        createTextField();

        createButtons();
    }

    private void createTextField() {
        input = new JTextField();
        input.setLocation(3, 3);
        input.setSize(209, 40);
        input.setEditable(false);
        input.setFont(new Font("arial", Font.PLAIN, 32));

        this.add(input);
    }

    private void createButtons() { //создание кнопок и добавление их на панель
        Button[] btn = new Button[17];
        for (int i = 0; i < 17; i++) {
            btn[i] = new Button();
            btn[i].setFont(new Font("arial", Font.BOLD, 28));
            add(btn[i]);
        }

        for (int i = 0; i < 10; i++) { //добавление обработчика событий всем "цифровым" кнопкам
            int j = i;
            btn[i].addActionListener(e -> {
                input.setText(input.getText() + btn[j].getText());
                num = Integer.parseInt(input.getText());
            });
        }

        for (int i = 0; i < 3; i++) { //задание текста на "цифровых" кнопках и их расположения
            for (int j = 0; j < 3; j++) {
                String s = 1 + j + (i * 3) + "";
                int a = 3 + (j * 53);
                int b = 106 - (i * 53);
                int pos = j + (i * 3);

                btn[pos].changeAll(s, a, b + 46);
            }
        }
        btn[9].changeAll("0", 3, 205, 103, 50);

        btn[10].changeText("+");
        btn[11].changeText("-");
        btn[12].changeText("*");
        btn[13].changeText("/");

        for (int i = 0; i < 4; i++) { //добавление обработчика для конопок операций
            int pos = 10 + i;
            btn[pos].changeLoc(162, 46 + (53 * i));
            addListener(btn[pos], i + 1);
        }

        btn[14].changeAll("=", 109, 205, 50, 103); //описание кнопки "равно"
        btn[14].addActionListener(e -> {
            switch (operation) {
                case (1):
                    res += num;
                    input.setText(Integer.toString((int) (res)));
                    break;
                case (2):
                    res -= num;
                    input.setText(Integer.toString((int) (res)));
                    break;
                case (3):
                    res *= num;
                    input.setText(Integer.toString((int) (res)));
                    break;
                case (4):
                    res /= num;
                    if (num != 0) {
                        if (res % 1 == 0) {
                            input.setText(Integer.toString((int) res));
                        } else {
                            input.setText(Float.toString(res));
                        }
                    } else {
                        input.setText("Erroro");
                    }
                    break;
                default:
                    input.setText("");
            }
        });

        btn[15].changeAll("√", 162, 258); //описание кнопки "квадратный корень"
        btn[15].addActionListener(e -> {
            res = (float) Math.sqrt(num);

            if (res % 1 == 0) {
                input.setText(Integer.toString((int) res));
            } else {
                input.setText(Float.toString(res));
            }
        });

        btn[16].changeAll("C", 3,258, 103, 50); //описание кнопки "сбросить"
        btn[16].addActionListener(e -> {
            num = 0;
            res = 0;
            operation = 0;
            input.setText("");
        });
    }

    private void addListener(Button btn, int operation) { //добавление обработчика "цифровым" кнопкам
        btn.addActionListener(e -> {
            this.operation = operation;
            input.setText("");
            res = num;
        });
    }
}