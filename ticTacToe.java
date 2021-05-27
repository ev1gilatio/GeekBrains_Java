import java.util.Random;
import java.util.Scanner;

public class ticTacToe {
    public final static char EMPTY_CELL_SYMB = '~';
    public static int SIZE;

    public static void start() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        //ввод размера поля и проверка на правильность
        System.out.print("Введите размер поля 3+ >> ");
        try {
            SIZE = Integer.parseInt(sc.nextLine());
        }
        catch(Exception ex) {
            SIZE = 3;
            System.out.println("Ошибка! Установлен минимальный размер 3x3");
        }
        if (SIZE < 3) {
            SIZE = 3;
            System.out.println("Ошибка! Установлен минимальный размер 3x3");
        }

        //создание и отрисовка поля
        char[][] f = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                f[i][j] = EMPTY_CELL_SYMB;
                System.out.printf("%s ", f[i][j]);
            }
            System.out.println();
        }

        //игровой процесс
        do {
            playerTurn(sc, f);
            drawField(f);
            isWin(f);

            System.out.println();

            botTurn(rand, f);
            drawField(f);
            isWin(f);
        } while(!isDraw(f));

        System.exit(0);
    }


    //отрисовка поля
    static void drawField(char[][] f) {
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(f[i][j] + " ");
            System.out.println();
        }
    }

    //ход игрока
    static void playerTurn(Scanner sc, char[][] f) {
        int x = -1, y = -1;
        do {
            System.out.printf("Введите координаты ячейки x;y 1..%s >> ", SIZE);
            String s = sc.nextLine();
            String[] coords = s.split(";");

            try {
                x = Integer.parseInt(coords[0]) - 1;
                y = Integer.parseInt(coords[1]) - 1;
            }
            catch(Exception ex) {
                System.out.println("Ошибка! Повторите ввод");
            }
        } while(x < 0 || y < 0 || x > (SIZE - 1) || y > (SIZE - 1) || !isEmpty(f[x][y]));

        f[x][y] = 'X';
    }

    //ход бота
    static void botTurn(Random rand, char[][] f) {
        boolean b = true;

        //проверка для 0
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE - 1; j++) {
                if (b) {
                    //горизонтали
                    if (f[i][j] == '0' && f[i][j + 1] == '0') {
                        if (j + 2 < SIZE && isEmpty(f[i][j + 2])) {
                            b = botMove(f, i, (j + 2));
                            break;
                        } else if (j - 1 >= 0 && isEmpty(f[i][j - 1])) {
                            b = botMove(f, i, (j - 1));
                            break;
                        }
                    }
                    else if (j + 2 < SIZE && f[i][j] == '0' && f[i][j + 2] == '0')
                        if (isEmpty(f[i][j + 1])) {
                            b = botMove(f, i, (j + 1));
                            break;
                        }

                    //вертикали
                    if (f[j][i] == '0' && f[j + 1][i] == '0') {
                        if (j + 2 < SIZE && isEmpty(f[j + 2][i])) {
                            b = botMove(f, (j + 2), i);
                            break;
                        } else if (j - 1 >= 0 && isEmpty(f[j - 1][i])) {
                            b = botMove(f, (j - 1), i);
                            break;
                        }
                    }
                    else if (j + 2 < SIZE && f[j][i] == '0' && f[j + 2][i] == '0')
                        if (isEmpty(f[j + 1][i])) {
                            b = botMove(f, (j + 1), i);
                            break;
                        }
                }
            }
        //проверка для X
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE - 1; j++) {
                if (b) {
                    //горизонтали
                    if (f[i][j] == 'X' && f[i][j + 1] == 'X') {
                        if (j + 2 < SIZE && isEmpty(f[i][j + 2])) {
                            b = botMove(f, i, (j + 2));
                            break;
                        } else if (j - 1 >= 0 && isEmpty(f[i][j - 1])) {
                            b = botMove(f, i, (j - 1));
                            break;
                        }
                    }
                    else if (j + 2 < SIZE && f[i][j] == 'X' && f[i][j + 2] == 'X')
                        if (isEmpty(f[i][j + 1])) {
                            b = botMove(f, i, (j + 1));
                            break;
                        }

                    //вертикали
                    if (f[j][i] == 'X' && f[j + 1][i] == 'X') {
                        if (j + 2 < SIZE && isEmpty(f[j + 2][i])) {
                            b = botMove(f, (j + 2), i);
                            break;
                        } else if (j - 1 >= 0 && isEmpty(f[j - 1][i])) {
                            b = botMove(f, (j - 1), i);
                            break;
                        }
                    }
                    else if (j + 2 < SIZE && f[j][i] == 'X' && f[j + 2][i] == 'X')
                        if (isEmpty(f[j + 1][i])) {
                            b = botMove(f, (j + 1), i);
                            break;
                        }

                }
            }

        //проверка для 0
        for (int z = 0; z < SIZE - 2; z++)
            for (int i = 0; i < SIZE - z - 1; i++) {
                if (b) {
                    //основные верхние диагонали
                    if (f[i][i + z] == '0' && f[i + 1][i + z + 1] == '0') {
                        if (i + 2 < SIZE && i + z + 2 < SIZE && isEmpty(f[i + 2][i + z + 2])) {
                            b = botMove(f, (i + 2), (i + z + 2));
                            break;
                        } else if (i - 1 >= 0 && i + z - 1 >= 0 && isEmpty(f[i - 1][i + z - 1])) {
                            b = botMove(f, (i - 1), (i + z - 1));
                            break;
                        }
                    }
                    else if (i + 2 < SIZE && i + z + 2 < SIZE && f[i][i + z] == '0' && f[i + 2][i + z + 2] == '0')
                        if (isEmpty(f[i + 1][i + z + 1])) {
                            b = botMove(f, (i + 1), (i + z + 1));
                            break;
                        }

                    //основные нижние диагонали
                    if (f[i + z][i] == '0' && f[i + z + 1][i + 1] == '0') {
                        if (i + 2 < SIZE && i + z + 2 < SIZE && isEmpty(f[i + z + 2][i + 2])) {
                            b = botMove(f, (i + z + 2), (i + 2));
                            break;
                        } else if (i - 1 >= 0 && i + z - 1 >= 0 && isEmpty(f[i + z - 1][i - 1])) {
                            b = botMove(f, (i + z - 1), (i - 1));
                            break;
                        }
                    }
                    else if (i + 2 < SIZE && i + z + 2 < SIZE && f[i + z][i] == '0' && f[i + z + 2][i + 2] == '0')
                        if (isEmpty(f[i + z + 1][i + 1])) {
                            b = botMove(f, (i + z + 1), (i + 1));
                            break;
                        }

                    //вспомогательные верхние диагонали
                    if (f[i][SIZE - 1 - i - z] == '0' && f[i + 1][SIZE - 2 - i - z] == '0') {
                        if (i + 2 < SIZE && SIZE - 3 - i - z >= 0 && isEmpty(f[i + 2][SIZE - 3 - i - z])) {
                            b = botMove(f, (i + 2), (SIZE - 3 - i - z));
                            break;
                        } else if (i - 1 >= 0 && SIZE - i - z < SIZE && isEmpty(f[i - 1][SIZE - i - z])) {
                            b = botMove(f, (i - 1), (SIZE - i - z));
                            break;
                        }
                    }
                    else if (i + 2 < SIZE && SIZE - 3 - i - z >= 0 && f[i][SIZE - 1 - i - z] == '0' && f[i + 2][SIZE - 3 - i - z] == '0')
                        if (isEmpty(f[i + 1][SIZE - 2 - i - z])) {
                            b = botMove(f, (i + 1), (SIZE - 2 - i - z));
                            break;
                        }

                    //вспомогательные нижние диагонали
                    if (f[i + z][SIZE - 1 - i] == '0' && f[i + z + 1][SIZE - 2 - i] == '0') {
                        if (i + z + 2 < SIZE && SIZE - 3 - i >= 0 && isEmpty(f[i + z + 2][SIZE - 3 - i])) {
                            b = botMove(f, (i + z + 2), (SIZE - 3 - i));
                            break;
                        } else if (i + z - 1 >= 0 && SIZE - i < SIZE && isEmpty(f[i + z - 1][SIZE - i])) {
                            b = botMove(f, (i + z - 1), (SIZE - i));
                            break;
                        }
                    }
                    else if (i + z + 2 < SIZE && SIZE - 3 - i >= 0 && f[i + z][SIZE - 1 - i] == '0' && f[i + z + 2][SIZE - 3 - i] == '0')
                        if (isEmpty(f[i + z + 1][SIZE - 2 - i])) {
                            b = botMove(f, (i + z + 1), (SIZE - 2 - i));
                            break;
                        }
                }
            }
        //проверка для X
        for (int z = 0; z < SIZE - 2; z++)
            for (int i = 0; i < SIZE - z - 1; i++) {
                if (b) {
                    //основные верхние диагонали
                    if (f[i][i + z] == 'X' && f[i + 1][i + z + 1] == 'X') {
                        if (i + 2 < SIZE && i + z + 2 < SIZE && isEmpty(f[i + 2][i + z + 2])) {
                            b = botMove(f, (i + 2), (i + z + 2));
                            break;
                        } else if (i - 1 >= 0 && i + z - 1 >= 0 && isEmpty(f[i - 1][i + z - 1])) {
                            b = botMove(f, (i - 1), (i + z - 1));
                            break;
                        }
                    }
                    else if (i + 2 < SIZE && i + z + 2 < SIZE && f[i][i + z] == 'X' && f[i + 2][i + z + 2] == 'X')
                        if (isEmpty(f[i + 1][i + z + 1])) {
                            b = botMove(f, (i + 1), (i + z + 1));
                            break;
                        }

                    //основные нижние диагонали
                    if (f[i + z][i] == 'X' && f[i + z + 1][i + 1] == 'X') {
                        if (i + 2 < SIZE && i + z + 2 < SIZE && isEmpty(f[i + z + 2][i + 2])) {
                            b = botMove(f, (i + z + 2), (i + 2));
                            break;
                        } else if (i - 1 >= 0 && i + z - 1 >= 0 && isEmpty(f[i + z - 1][i - 1])) {
                            b = botMove(f, (i + z - 1), (i - 1));
                            break;
                        }
                    }
                    else if (i + 2 < SIZE && i + z + 2 < SIZE && f[i + z][i] == 'X' && f[i + z + 2][i + 2] == 'X')
                        if (isEmpty(f[i + z + 1][i + 1])) {
                            b = botMove(f, (i + z + 1), (i + 1));
                            break;
                        }

                    //вспомогательные верхние диагонали
                    if (f[i][SIZE - 1 - i - z] == 'X' && f[i + 1][SIZE - 2 - i - z] == 'X') {
                        if (i + 2 < SIZE && SIZE - 3 - i - z >= 0 && isEmpty(f[i + 2][SIZE - 3 - i - z])) {
                            b = botMove(f, (i + 2), (SIZE - 3 - i - z));
                            break;
                        } else if (i - 1 >= 0 && SIZE - i - z < SIZE && isEmpty(f[i - 1][SIZE - i - z])) {
                            b = botMove(f, (i - 1), (SIZE - i - z));
                            break;
                        }
                    }
                    else if (i + 2 < SIZE && SIZE - 3 - i - z >= 0 && f[i][SIZE - 1 - i - z] == 'X' && f[i + 2][SIZE - 3 - i - z] == 'X')
                        if (isEmpty(f[i + 1][SIZE - 2 - i - z])) {
                            b = botMove(f, (i + 1), (SIZE - 2 - i - z));
                            break;
                        }

                    //вспомогательные нижние диагонали
                    if (f[i + z][SIZE - 1 - i] == 'X' && f[i + z + 1][SIZE - 2 - i] == 'X') {
                        if (i + z + 2 < SIZE && SIZE - 3 - i >= 0 && isEmpty(f[i + z + 2][SIZE - 3 - i])) {
                            b = botMove(f, (i + z + 2), (SIZE - 3 - i));
                            break;
                        } else if (i + z - 1 >= 0 && SIZE - i < SIZE && isEmpty(f[i + z - 1][SIZE - i])) {
                            b = botMove(f, (i + z - 1), (SIZE - i));
                            break;
                        }
                    }
                    else if (i + z + 2 < SIZE && SIZE - 3 - i >= 0 && f[i + z][SIZE - 1 - i] == 'X' && f[i + z + 2][SIZE - 3 - i] == 'X')
                        if (isEmpty(f[i + z + 1][SIZE - 2 - i])) {
                            b = botMove(f, (i + z + 1), (SIZE - 2 - i));
                            break;
                        }
                }
            }

        if (b) randCords(f, rand);
    }

    //проверка наличия символа в ячейке
    static boolean isEmpty(char f) {
        return (f == EMPTY_CELL_SYMB);
    }

    static boolean isDraw(char[][] f) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (isEmpty(f[i][j])) return false;

        return true;
    }

    //проверка победы
    static void isWin(char[][] f) {
        int[] arr;
        int countX, count0;

        //горизонтали
        for (int i = 0; i < SIZE; i++) {
            countX = count0 = 0;
            for(int j = 0; j < SIZE; j++) {
                arr = winCheck(f[i][j], countX, count0);
                countX = arr[0]; count0 = arr[1];
            }
        }

        //вертикали
        for (int i = 0; i < SIZE; i++) {
            countX = count0 = 0;
            for(int j = 0; j < SIZE; j++) {
                arr = winCheck(f[j][i], countX, count0);
                countX = arr[0]; count0 = arr[1];
            }
        }

        //основные верхние диагонали
        for (int z = 0; z < SIZE - 2; z++) {
            countX = count0 = 0;
            for(int i = 0; i < SIZE - z; i++) {
                arr = winCheck(f[i][i + z], countX, count0);
                countX = arr[0]; count0 = arr[1];
            }
        }

        //основные нижние диагонали
        for (int z = 0; z < SIZE - 2; z++) {
            countX = count0 = 0;
            for(int i = 0; i < SIZE - z; i++) {
                arr = winCheck(f[i + z][i], countX, count0);
                countX = arr[0]; count0 = arr[1];
            }
        }

        //вспомогательные верхние диагонали
        for (int z = 0; z < SIZE - 2; z++) {
            countX = count0 = 0;
            for(int i = 0; i < SIZE - z; i++) {
                arr = winCheck(f[i][SIZE - 1 - i - z], countX, count0);
                countX = arr[0]; count0 = arr[1];
            }
        }

        //вспомогательные нижние диагонали
        for (int z = 0; z < SIZE - 2; z++) {
            countX = count0 = 0;
            for(int i = 0; i < SIZE - z; i++) {
                arr = winCheck(f[i + z][SIZE - 1 - i], countX, count0);
                countX = arr[0]; count0 = arr[1];
            }
        }
    }

    //проверка условия победы
    static int[] winCheck(char f, int cX, int c0) {
        int[] arr = new int[2];
        arr[0] = cX; arr[1] = c0;

        if (f == 'X') arr[0]++;
        else if (f == '0') arr[1]++;

        if (f != 'X') arr[0] = 0;
        if (f != '0') arr[1] = 0;

        if (arr[0] >= 3) win('X');
        else if (arr[1] >= 3) win('0');

        return arr;
    }

    //действие бота
    static boolean botMove(char[][] f, int i, int j) {
        f[i][j] = '0';
        return false;
    }

    //рандомизация координат
    static void randCords(char[][] f, Random rand) {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while(!isEmpty(f[x][y]));

        f[x][y] = '0';
    }

    //завершение игры
    static void win(char symb) {
        System.out.printf("%nПобедил %s", symb);
        System.exit(0);
    }
}