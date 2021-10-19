

public class ArrayProcessing {
    public ArrayProcessing() {

    }

    public static int[] transformArray(int[] arr) {
        int[] finallyReturn = {0};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                break;
            } else if (i == arr.length - 1 && arr[i] != 4) {
                throw new RuntimeException("There are no 4 in array");
            }
        }

        int iterator = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                iterator = i;
            }
        }

        if (iterator != -1) {
            int[] reArr = new int[arr.length - 1 - iterator];

            for (int i = iterator + 1; i < arr.length; i++) {
                reArr[i - iterator - 1] = arr[i];
            }

            return reArr;
        }

        return finallyReturn;
    }

    public static boolean checkArrayFor1and4(int[] arr) {
        for (int i : arr) {
            if (i == 1 || i == 4) {
                return true;
            }
        }

        return false;
    }
}