

public class Main {
    public static void main(String[] args) {
        doTask1();
        doTask2();
    }

    static void doTask1() {
        float[] arr = new float[1000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    static void doTask2() {
        int size = 1000000;
        int half = size / 2;

        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();

        Thread th1 = new Thread(() -> {
            float[] arr_copy_1 = new float[half];

            System.arraycopy(arr, 0, arr_copy_1, 0, half);

            for (int i = 0; i < arr_copy_1.length; i++) {
                arr_copy_1[i] = (float)
                        (arr_copy_1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

            System.arraycopy(arr_copy_1, 0, arr, 0, half);
        });

        Thread th2 = new Thread(() -> {
            float[] arr_copy_2 = new float[half];

            System.arraycopy(arr, half, arr_copy_2, 0, half);

            for (int i = half; i < size; i++) {
                arr_copy_2[i - half] = (float)
                        (arr_copy_2[i - half] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

            System.arraycopy(arr_copy_2, 0, arr, half, half);
        });

        th1.start();
        th2.start();

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
