import java.util.Objects;

public class Main {
    public static  void main(String[] args) {
        String[][] arr = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
//                arr[i][j] = "" + (int) (Math.random()*10);
                arr[i][j] = "1";
//                arr[0][2] = "'";
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        arrayCheck(arr);
    }

    static void arrayCheck(String[][] arr) {
        try {
            for (int i = 0; i < 4; i++) {
                if (arr.length != 4 || arr[i].length != 4) {
                    throw new MyArraySizeException("Invalid array size");
                }
            }
            arraySum(arr);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
    }

    static void arraySum(String[][] arr) {
        int sum = 0;
        int a = 0, b = 0;
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    a = i; b = j;
                    sum += Integer.parseInt(arr[i][j]);
                }
            }
            System.out.println("Sum is: " + sum);
        } catch (NumberFormatException e) {
            try {
                throw new MyArrayDataException("Cell (" + a + ";" + b + ") has invalid number format", e);
            } catch (MyArrayDataException ex) {
                ex.printStackTrace();
            }
        }
    }
}