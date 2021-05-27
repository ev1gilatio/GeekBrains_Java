import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        changeZeroOne(rand);

        fillHundred(rand);

        less6multi2();

        fillDiagonal(rand);

        doTask5(rand);

        minMax(rand);

        int[] arr = arr(rand, 9, 2); //задание размера массива [2; 10]
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = rand.nextInt(10);
        }
        checkBalance(arr);

        int n = rand.nextInt() % 10; //вычленение последнего разряда рандомного инта
        int[] arr2 = arr(rand, 9, 2, abs(n)); //задание размера массива [2; 10+n]
        for (int i = 0; i < arr2.length; i ++) {
            arr2[i] = rand.nextInt(10);
        }
        moveArr(arr2, n); //сдвиг массива на n
    }

    //1
    static int[] changeZeroOne(Random rand) {
        int[] arr = arr(rand, 10, 1); //задание размера массива [1; 10]
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            arr[i] = rand.nextInt(2);
        }

        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }

        return arr;
    }

    //2
    static int[] fillHundred(Random rand) {
        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        return arr;
    }

    //3
    static int[] less6multi2() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        return arr;
    }

    //4
    static int[][] fillDiagonal(Random rand) {
        int bounds = rand.nextInt(9) + 2; //задание размера двумерного массива [2x2; 10x10]
        int[][] arr = new int[bounds][bounds];

        for (int i = 0; i < arr.length; i ++) {         //заполнение
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = rand.nextInt(10);
            }
        }

        for (int i = 0; i < arr.length; i ++) {         //замена диагоналей
            for (int j = 0; j < arr[i].length; j++) {
                if (j == i || j == arr[i].length - 1 - i) {
                    arr[i][j] = 1;
                }
            }
        }

        return arr;
    }

    //5
    static int[] doTask5(Random rand) {
        int len = rand.nextInt(10);
        int initialValue = rand.nextInt(10);

        int[] arr = new int[len];

        for (int i = 0; i < len; i ++) {
            arr[i] = initialValue;
        }

        return arr;
    }

    //6*
    static int[] minMax(Random rand) {
        int[] arr = arr(rand, 9, 2); //задание размера массива [2; 10]
        int len = arr.length;

        for (int i = 0; i < len; i ++) { //заполнение
            arr[i] = rand.nextInt(10);
        }

        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < len; i ++) { //нахождение мин и макс
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] arr2 = {min, max};
        return arr2;
    }

    //7**

    /**
     * Здесь я сравниваю сумму остатка элементов массива с новой суммой элементов начала массива
     * int[] arr = {1, 4, 2, 3,};
     * сумма остатка (rem) = 1 + 4 + 2 + 3 = 10
     * далее
     * сумма начала (sum) = 1
     * rem = 10 - 1
     * sum = 1 + 4
     * rem = 10 - 1 - 4
     * и так пока sum не будет равен rem, если такая точка вообще будет
     * Плюс, если сумма остатка изначалаьно нечетная, то сразу можно возвращать false
     */

    static boolean checkBalance(int[] arr) {
        int sum = 0;
        int rem = 0;
        boolean isBalance = false;
        int len = arr.length;

        for (int i = 0; i < len; i ++) {
            rem += arr[i];
        }

        if (rem % 2 != 0) { //проверка на четность
            return false;
        } else {
            for (int i = 0; i < len; i++) { //в противном случае прогон через цикл
                sum += arr[i];
                rem -= arr[i];

                if (sum == rem) { //при нахождении "баланса" сразу выходим из цикла
                    isBalance = true;
                    break;
                }
            }
            return isBalance;
        }
    }

    //8***

    /**
     * Поработав с алгоритмом "пузырек" на бумаге, я заметил, что через arr.length-1 использований
     * алгоритма я получаю нужный результат в виде перестановки элементов на 1 позицию вперед
     * Следовательно для перестановки на n позиций нужно использовать алгоритм n*(arr.length-1) раз
     *
     * Это, скорее всего, неэффективно при больших массивах, но до другого способа я не додумался
     *
     * Если n>0, то сдвиг идет вправо, иначе влево
     */

    static int[] moveArr(int[] arr, int n) {
        int c;
        int len = arr.length;

        if (n > 0) { //n положительное
            for (int i = 0; i < (n * (len - 1)); i++) { //количество смещений
                for (int j = 0; j < len - 1; j++) { //перебор массива
                    c = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = c;
                }
            }
        } else { //n отрицательное
            for (int i = 0; i < (abs(n) * (len - 1)); i++) { //количество смещений
                for (int j = len - 1; j > 0; j--) { //перебор массива
                    c = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = c;
                }
            }
        }

        return arr;
    }

    //abs
    static int abs(int a) {
        if (a < 0) {
            return a * -1;
        } else {
            return a;
        }
    }

    //создание массива
    static int[] arr(Random rand, int... a) {
        int len = a.length;

        int add = 0;
        for (int i = 1; i < len; i++) {
            add += a[i];
        }

        int[] arr = new int[rand.nextInt(a[0]) + add];
        return arr;
    }
}