

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = abs(food);
    }

    public void decreaseFood(int n) {
        food -= n;
    }

    public void addFood(int n) {
        food += n;
    }

    public void info() {
        System.out.printf("В миске %s еды%n", food);
    }

    public int getFood() {
        return food;
    }

    private int abs(int a) {
        if (a < 0) {
            return a * -1;
        } else {
            return a;
        }
    }
}