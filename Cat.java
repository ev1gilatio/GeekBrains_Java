

public class Cat {
    private final String name;
    private final int satiety;
    private boolean full;

    public Cat(String name, int satiety) {
        this.name = name;
        this.satiety = abs(satiety);
    }

    public void eat(Plate p) {

        if (p.getFood() - satiety >= 0) {
            p.decreaseFood(satiety);
            full = true;
        } else {
            System.out.println("В миске мало еды");
        }
    }

    public void isFull() {
        if (full) {
            System.out.printf("%s сыт%n", name);
        } else {
            System.out.printf("%s голоден%n", name);
        }
    }

    private int abs(int a) {
        if (a < 0) {
            return a * -1;
        } else {
            return a;
        }
    }
}