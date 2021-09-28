

public class Main {
    public static void main(String[] args) {
        Box box = new Box();
        Box anotherBox = new Box();

        box.addFruit(new Apple());
        box.addFruit(new Orange());
        box.addFruit(new Apple());

        anotherBox.addFruit(new Orange());
        anotherBox.addFruit(new Orange());
        anotherBox.addFruit(new Apple());

        System.out.println("Вес 1 = " + box.getWeight());
        System.out.println("Вес 2 = " + anotherBox.getWeight());

        System.out.println("Сравнение = " + box.compare(anotherBox));

        box.move(anotherBox);

        System.out.println("Кол-во фруктов в 1 = " + box.getContent().size() + " шт.");
        System.out.println("Кол-во фруктов во 2 = " + anotherBox.getContent().size() + " шт.");
    }
}