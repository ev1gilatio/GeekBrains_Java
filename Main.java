

public class Main {
    public static void main(String[] args) {
        sum10_20(10, -1);
        System.out.println();

        positiveOrNegative(-5);
        System.out.println();

        negative(-1);
        System.out.println();

        stringLoop("Привет!", 3);
        System.out.println();

        visokos(2021);
    }

    //1
    static boolean sum10_20(int a, int b) {
        if (a + b >= 10 && a + b <= 20) {
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    //2
    static void positiveOrNegative(int a) {
        if (a >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Отрицательное");
        }
    }

    //3
    static boolean negative(int a) {
        if (a < 0) {
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    //4
    static void stringLoop(String s, int a) {
        for (int i = 0; i < a; i ++) {
            System.out.println(s);
        }
    }

    //5*
    static boolean visokos(int a) {
        if ((a % 4 == 0 && a % 100 != 0) || a % 400 == 0) {
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }
}