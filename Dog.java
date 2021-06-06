

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void run(int dist) {
        if (dist > 500) {
            System.out.printf("%s не справится с поставленной задачей%n", getName());
        } else if (dist > 0 && dist <= 500) {
            System.out.printf("%s пробежал %s м%n", getName(), dist);
        } else {
            wrong();
        }
    }

    @Override
    public void swim(int dist) {
        if (dist > 10) {
            System.out.printf("%s утонет%n", getName());
        } else if (dist > 0 && dist <= 10) {
            System.out.printf("%s проплыл %s м%n", getName(), dist);
        } else {
            wrong();
        }
    }
}