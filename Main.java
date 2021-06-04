

public class Main {
    public static void main(String[] args) {
        Plate p = new Plate(60);
        Cat[] cats = new Cat[3];

        p.addFood(1);

        cats[0] = new Cat("Barsik", 11);
        cats[1] = new Cat("Kuzya", 15);
        cats[2] = new Cat("Zdorovyak", 38);

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(p);
            cats[i].isFull();
        }

        p.info();
    }
}